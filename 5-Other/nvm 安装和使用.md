# 介绍

我们可能同时在进行 2 个项目，而 2 个不同的项目所使用的 node 版本又是不一样的，或者是要用更新的 node 版本进行试验和学习。这种情况下，对于维护多个版本的 node 将会是一件非常麻烦的事情，而 nvm 就是为解决这个问题而产生的，他可以方便的在同一台设备上进行多个 node 版本之间切换，而这个正是 nvm 的价值所在，详情可以查看官网 [NVM 官网](https://links.jianshu.com/go?to=https%3A%2F%2Fgithub.com%2Fcreationix%2Fnvm)。

# 安装

## HomeBrew安装

**推荐使用HomeBrew安装**

`brew install nvm`

安装成功之后，还不能直接使用 nvm 命令，需要进行以下配置，将以下命令复制到终端执行：

`echo "source $(brew --prefix nvm)/nvm.sh" >> .bash_profile`

修改之后，需要重新定向来源，复制以下命令并执行

`. ~/.bash_profile`

此时在终端输入：

`nvm list`

即可查看当前电脑中安装的 node 版本，如果未曾安装 node，则显示为空。

接下来就可以使用 nvm 来安装和管理 node 版本了。

## 终端安装

使用终端安装：

`curl -o-https：//raw.githubusercontent.com/nvm-sh/nvm/v0.34.0/install.sh`

# 操作命令

查看已经安装的版本

`nvm list`

查看已经安装的版本

`nvm list installed`

查看网络可以安装的版本

`nvm list available`

安装最新版本nvm

`nvm install stable`

获取远程 node 的所有版本列表

`nvm ls-remote`

切换版本

`nvm use 版本号`