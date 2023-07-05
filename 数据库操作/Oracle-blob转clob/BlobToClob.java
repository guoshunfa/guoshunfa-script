import org.junit.Test;

import java.io.*;
import java.sql.*;

public class BlobToClob {

    public static void main(String[] args) throws Exception {
        // 连接数据库
        Connection connection = connectionOracle();
        Statement statement = connection.createStatement();
        // 查询出blob
        ResultSet rs = statement.executeQuery("SELECT * FROM panda_test");
        System.out.println("连接成功" + rs.toString());
    }

    /**
     * blobToClob
     *
     * @throws Exception
     */
    @Test
    public void blobToClob() throws Exception {
        // 连接数据库
        Connection conn = connectionOracle();
        Statement statement = conn.createStatement();
        ResultSet result = statement.executeQuery("select B_BLOB from PANDA_TEST");

        while (result.next()) {
            String sql = "update PANDA_TEST set C_CLOB = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            InputStream b_blob = result.getBinaryStream("B_BLOB");
            ps.setCharacterStream(1, new InputStreamReader(b_blob), 129509);
            ps.executeUpdate();
        }
        conn.close();
    }

    /**
     * 测试转换之后的clob是否正常
     *
     * @throws Exception
     */
    @Test
    public void testClob() throws Exception {
        // 连接数据库
        Connection conn = connectionOracle();
        Statement statement = conn.createStatement();
        ResultSet result = statement.executeQuery("select C_CLOB from PANDA_TEST");
        while (result.next()) {
            Reader c_clob = result.getCharacterStream("C_CLOB");
            File file = new File("/Users/guoshunfa/workspace/my/my-main-git-project/panda-script/Java脚本/数据库操作/Oracle/blob转clob/panda2.jpeg");
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(readerToStringBuilder(c_clob).toString().getBytes("UTF-8"));
            fos.flush();
            fos.close();
        }
        conn.close();
    }

    @Test
    public void addBlobData() throws Exception {
        // 连接数据库
        Connection conn = connectionOracle();
        String sql = "insert into PANDA_TEST(B_BLOB) values (?)";
        PreparedStatement ps = conn.prepareStatement(sql);
        File file = new File("/Users/guoshunfa/workspace/my/my-main-git-project/panda-script/Java脚本/数据库操作/Oracle/blob转clob/panda.jpeg");
        InputStream in = new BufferedInputStream(new FileInputStream(file));
        ps.setBinaryStream(1, in, (int) file.length());
        ps.executeUpdate();
        in.close();
        conn.close();
    }

    public static StringBuilder readerToStringBuilder(Reader reader) throws IOException {
        StringBuilder sb = new StringBuilder();
        char[] content = new char[10];
        int len = -1;
        while ((len = reader.read(content)) != -1) {
            sb.append(content, 0, len);
        }
        reader.close();
        return sb;
    }

    public static String inputStreamToString(InputStream is) throws IOException {
        //将从服务器获得的流is转换为字符串
        //初始值，起标志位作用
        int len = -1;
        //缓冲区
        byte buf[] = new byte[128];
        //捕获内存缓冲区的数据转换为字节数组
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        //循环读取内容,将输入流的内容放进缓冲区中
        while ((len = is.read(buf)) != -1) {
            //将缓冲区内容写进输出流，0是从起始偏移量，len是指定的字符个数
            baos.write(buf, 0, len);
        }
        //最终结果，将字节数组转换成字符
        return new String(baos.toByteArray());
    }

    /**
     * 连接oracle
     *
     * @return
     * @throws Exception
     */
    static Connection connectionOracle() throws Exception {
        Class.forName("oracle.jdbc.driver.OracleDriver");
        String dbURL = "jdbc:oracle:thin:@localhost:1521:helowin";
        return DriverManager.getConnection(dbURL, "system", "helowin");
    }

    /**
     * 把Blob类型转换为byte数组类型
     *
     * @param blob
     * @return
     */
    static byte[] blobToBytes(Blob blob) {
        BufferedInputStream is = null;
        try {
            is = new BufferedInputStream(blob.getBinaryStream());
            byte[] bytes = new byte[(int) blob.length()];
            int len = bytes.length;
            int offset = 0;
            int read = 0;
            while (offset < len && (read = is.read(bytes, offset, len)) >= 0) {
                offset += read;
            }
            return bytes;
        } catch (Exception e) {
            return null;
        } finally {
            try {
                is.close();
                is = null;
            } catch (IOException e) {
                return null;
            }
        }
    }
}