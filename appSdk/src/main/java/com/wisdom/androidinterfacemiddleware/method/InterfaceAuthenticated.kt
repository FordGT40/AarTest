package com.wisdom.androidinterfacemiddleware.method

import android.content.Context
import android.content.pm.PackageManager

/**
 * @ProjectName project： AndroidInterfaceMiddleware
 * @class package：com.wisdom.androidinterfacemiddleware.method
 * @class describe：接口鉴权类
 * @author HanXueFeng
 * @time 2019/1/10 13:27
 * @change
 */
public class InterfaceAuthenticated(context: Context) {


    //    meta data 配置样例
//    <meta-data
//    android:name="appid"
//    android:value="AIzaSyBhBFOgVQclaa8p1JJeqaZHiCo2nfiyBBo" />
    init {

    }

    companion object {
        lateinit var packageName: String
        lateinit var appid: String
        //配置加密用的key 和 appid参数（以下参数需要在集成项目之前配置到aar本地）
        private const val secretKeyLocal: String = "vPban9T6tE10F8gx"//加密用的钥匙
//        private const val packageNameLocal: String = "io.dcloud.H52DD71BF"// 当前主App包名
        private const val packageNameLocal: String = "com.wisdom.nhzwt"// 当前主App包名
//        private const val packageNameLocal: String = "com.wisdom.aartest"// 当前主App包名

        private const val appidLocal: String =
            "AIzaSyBhBFOgVQclaa8p1JJeqaZHiCo2nfiyBBo"//manifest中配置的meta-data ，用来验证当前App、是否合法
        private const val encryptIv: String = "4111591c67e98da6"//加密用的，代码生成的偏移量，保持和ios端的值一样即可

        /**
         *  @describe 通过验证，获得访问接口的key
         *  @return
         *  @author HanXueFeng
         *  @time 2019/1/10  14:03
         */
        fun getAccessKey(context: Context, appkey: String, str: String): String {

            packageName = context.packageName
            val appInfo = context.packageManager.getApplicationInfo(
                packageName,
                PackageManager.GET_META_DATA
            )
            appid = appInfo.metaData.getString("appid")

            val result: String
            if (packageName != packageNameLocal || appid != appidLocal) {
                result = ""
//                Toast.makeText(context, "包名或appid有误，请重试", Toast.LENGTH_SHORT).show()
                println("huaze:包名或appid有误，请重试")
                println("huaze:当前项目包名:$packageName")
                println("huaze:当前项目manifest中AppId:$appid")
            } else {
                //初步验证通过，进行品参数返回加密串
                result = getCodeStr(appkey + str)

            }
            return result
        }


        /**
         *  @describe 将传入的字符串加密后返回
         *  @return
         *  @author HanXueFeng
         *  @time 2019/1/10  13:57
         */
        private fun getCodeStr(str: String): String {
            var strCode = ""
            try {
                val _crypt = CryptLib()
                val key = CryptLib.SHA256(secretKeyLocal, 32) //32 bytes = 256 bit
                val iv = CryptLib.generateRandomIV(16) //16 bytes = 128 bit
                println("*****iv****:$iv")
                //以下方法第三个参数是由上面一行代码随机生成，现在将iv值写死，
                // 目的是为了和iOS端生成密文保持一致
                strCode = _crypt.encrypt(str, key, encryptIv) //encrypt
//            output = _crypt.decrypt(output, key, "4111591c67e98da6") //decrypt
            } catch (e: Exception) {
                e.printStackTrace()
            }
            return strCode
        }
    }
}