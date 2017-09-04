给我自己的忠告：


1、picasso的使用，If you are using ProGuard you might need to add the following option:
（如果您使用的是ProGuard，您可能需要添加以下选项:）
ProGuard：
ProGuard是一个压缩、优化和混淆Java字节码文件的免费的工具，它可以删除无用的类、字段、方法和属性。
可以删除没用的注释，最大限度地优化字节码文件。它还可以使用简短的无意义的名称来重命名已经存在的类、
字段、方法和属性。常常用于Android开发用于混淆最终的项目，增加项目被反编译的难度。
但是我没有使用到ProGuard，所以目前还没有加入上面需要到权限，当时好如果要上传则参考github上到解析

2、不要随便该改变build.gradle里面到东西，因为一旦改变就可能会造成其他到错误
