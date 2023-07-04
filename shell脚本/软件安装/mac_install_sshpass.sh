# 安装脚本
var=`brew list|grep sshpass`
if [ "$var" = "sshpass" ]
then echo "sshpass已安装"
else 
echo "开始安装sshpass"
wget https://raw.githubusercontent.com/kadwanev/bigboybrew/master/Library/Formula/sshpass.rb
brew install sshpass.rb
rm sshpass.rb
echo "sshpass安装成功"
fi