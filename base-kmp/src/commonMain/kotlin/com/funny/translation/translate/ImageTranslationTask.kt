package com.funny.translation.translate

import com.funny.translation.kmp.base.strings.ResStrings
import kotlinx.serialization.SerialName

@kotlinx.serialization.Serializable
data class ImageTranslationPart(
    val source: String,
    var target: String,
    val x: Int = 0,
    val y: Int = 0,
    val width: Int = 100,
    val height: Int = 100
)

@kotlinx.serialization.Serializable
data class ImageTranslationResult(
    @SerialName("erased_img")
    val erasedImgBase64: String? = null,
    val source: String = "",
    val target: String = "",
    val content: List<ImageTranslationPart> = arrayListOf()
)

abstract class ImageTranslationTask(
    var sourceImg: ByteArray = byteArrayOf(),
) : CoreTranslationTask() {
    var result = ImageTranslationResult()

    @Throws(TranslationException::class)
    open suspend fun translate(){
        if (!supportLanguages.contains(sourceLanguage) || !supportLanguages.contains(targetLanguage)){
            throw TranslationException(ResStrings.unsupported_language)
        }
        if (sourceLanguage == targetLanguage) return
    }

    abstract val isOffline: Boolean
}