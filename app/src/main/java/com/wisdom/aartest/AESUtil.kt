package com.wisdom.aartest

import java.io.UnsupportedEncodingException
import java.security.InvalidAlgorithmParameterException
import java.security.InvalidKeyException
import java.security.NoSuchAlgorithmException
import java.util.*
import javax.crypto.BadPaddingException
import javax.crypto.Cipher
import javax.crypto.IllegalBlockSizeException
import javax.crypto.NoSuchPaddingException
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec

/**
 * @ProjectName project： AarTest
 * @class package：com.wisdom.aartest
 * @class describe：
 * @author HanXueFeng
 * @time 2019/1/9 15:33
 * @change
 */
class AESUtil {
    /**
     * 加密
     * @param content 需要加密的内容
     * @param password  加密密码
     * @return
     */
    private val key = "key"

    @Throws(NullPointerException::class)
    fun encode(stringToEncode: String): String {

        try {
            val skeySpec = getKey(key)
            val clearText = stringToEncode.toByteArray(charset("UTF8"))
            val iv = ByteArray(16)
            Arrays.fill(iv, 0x00.toByte())
            val ivParameterSpec = IvParameterSpec(iv)
            val cipher = Cipher.getInstance("AES/CBC/PKCS7Padding")
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec, ivParameterSpec)
            return jodd.util.Base64.encodeToString(cipher.doFinal(clearText), true)

        } catch (e: InvalidKeyException) {
            e.printStackTrace()
        } catch (e: UnsupportedEncodingException) {
            e.printStackTrace()
        } catch (e: NoSuchAlgorithmException) {
            e.printStackTrace()
        } catch (e: BadPaddingException) {
            e.printStackTrace()
        } catch (e: NoSuchPaddingException) {
            e.printStackTrace()
        } catch (e: IllegalBlockSizeException) {
            e.printStackTrace()
        } catch (e: InvalidAlgorithmParameterException) {
            e.printStackTrace()
        }

        return ""
    }

    @Throws(UnsupportedEncodingException::class)
    private fun getKey(password: String): SecretKeySpec {
        val keyLength = 256
        val keyBytes = ByteArray(keyLength / 8)
        Arrays.fill(keyBytes, 0x0.toByte())
        val passwordBytes = password.toByteArray(charset("UTF-8"))
        val length = if (passwordBytes.size < keyBytes.size) passwordBytes.size else keyBytes.size
        System.arraycopy(passwordBytes, 0, keyBytes, 0, length)
        return SecretKeySpec(keyBytes, "AES")
    }
}