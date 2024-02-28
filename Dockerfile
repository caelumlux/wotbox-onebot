FROM ubuntu:22.04
MAINTAINER caelumlux@outlook.com

# 切换镜像源 安装 jdk17 安装 Chrome
RUN sed -i s@/archive.ubuntu.com/@/mirrors.ustc.edu.cn/@g /etc/apt/sources.list \
&& apt-get update && apt-get install -y wget \
&& apt-get install -y openjdk-17-jdk \
&& wget -q https://dl.google.com/linux/direct/google-chrome-stable_current_amd64.deb \
&& apt-get install -y ./google-chrome-stable_current_amd64.deb
# 设置工作目录
WORKDIR /app/wotbox
#上传jar包
COPY ./wotbox-onebot-1.0-SNAPSHOT.jar /app/wotbox
#配置中文环境
RUN apt-get install language-pack-zh-hans -y \
&& apt-get install fonts-wqy-zenhei -y \
&& fc-cache -f -v
#环境变量
ENV  LANG=zh_CN.UTF-8
#指定需要暴露端口
EXPOSE 5555
#启动容器命令
CMD ["java", "-jar", "wotbox-onebot-1.0-SNAPSHOT.jar", "--shiro.chrome.path=/opt/google/chrome/chrome"]