# AarTest
构建限制接口访问的aar包用的项目
该项目是用来生成aar包用的项目
aar主要功能  通过aar包中的本地值与被植入项目的 包名以及meta-data中appid 进行匹配 判断该项目是否有权限调用接口；
若匹配通过，则对传进方法中的参数进行加密处理，并返回加密完毕的字符串
appsdk（aar）生成之前需要在项目中配置以下本地值，以便对被植入项目进行匹配判断


/配置加密用的key 和 appid参数（以下参数需要在集成项目之前配置到aar本地）
    val secretKeyLocal: String = "secretKey"//加密用的钥匙
    val packageNameLocal: String = "com.wisdom.aartest"// 当前主App包名
    val appidLocal: String = "AIzaSyBhBFOgVQclaa8p1JJeqaZHiCo2nfiyBBo"//manifest中配置的meta-data ，用来验证当前App、是否合法
    val encryptIv: String = "4111591c67e98da6"//加密用的，代码生成的偏移量，保持和ios端的值一样即可
    
    
    该aar进行调用举例如下（Kotlin）：
    InterfaceAuthenticated.getAccessKey(this,"被加密字符串")
