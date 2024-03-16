package com.example.demo.utils

import org.springframework.core.io.ByteArrayResource
import java.nio.file.Files
import java.nio.file.Paths

class ByteArrayImageResourceManager {

    companion object{
        fun getImageByteArrayResource(imageName: String): ByteArrayResource{
            val imagePath = Paths.get(Constants.imagesPath + imageName)
            val imageBytes = Files.readAllBytes(imagePath)
            return ByteArrayResource(imageBytes)
        }


    }
}