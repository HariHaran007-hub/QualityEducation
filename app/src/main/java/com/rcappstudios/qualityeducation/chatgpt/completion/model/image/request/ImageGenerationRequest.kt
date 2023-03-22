package com.zero.chatgpt_androidapp.data.completion.model.image.request


import com.google.gson.annotations.SerializedName

data class ImageGenerationRequest(
    @SerializedName("n")
    val n: Int,
    @SerializedName("prompt")
    val prompt: String,
    @SerializedName("size")
    val size: String
)