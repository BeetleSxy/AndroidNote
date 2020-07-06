# 介绍

Homebrew 是一款 macOS 平台下的软件包管理器，可以让你快捷的安装卸载管理你的软件；

来安装 [你需要的东西](https://link.jianshu.com/?t=https%3A%2F%2Fgithub.com%2FHomebrew%2Fhomebrew-core%2Ftree%2Fmaster%2FFormula) 

# 前置条件

- Xcode 命令行工具 $ xcode-select --install
- 支持 shell（sh 或者 bash）

# 安装和卸载

## 安装

`/usr/bin/ruby -e "$(curl -fsSL https://raw.githubusercontent.com/Homebrew/install/master/install)"`

## 卸载

`ruby -e "$(curl -fsSL https://raw.githubusercontent.com/Homebrew/install/master/uninstall)"`

也可下载 [卸载脚本](https://raw.githubusercontent.com/Homebrew/install/master/uninstall) 并运行 ./uninstall —help 以查看更多卸载选项

# 

安装命令

`brew install <packageName>`

卸载命令

`brew uninstall <packageName>`

查询可用包

`brew search <packageName>`

查看已安装包列表

`brew list`

查看任意包信息

`brew info <packageName>`

更新 Homebrew

`brew update`

查看 Homebrew 版本

`brew -v`

查看 Homebrew 帮助信息

`brew -h`