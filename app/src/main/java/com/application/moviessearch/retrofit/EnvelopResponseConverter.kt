package com.application.moviestest.retrofit

import com.google.gson.reflect.TypeToken
import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.reflect.Type
import com.application.moviestest.retrofit.Qualifiers.Normal
import com.application.moviestest.retrofit.Qualifiers.Envelope


class EnvelopResponseConverter : Converter.Factory() {
    private val gsonConverterFactory: Converter.Factory = GsonConverterFactory.create()
    override fun responseBodyConverter(
        type: Type,
        annotations: Array<Annotation>,
        retrofit: Retrofit
    ): Converter<ResponseBody, *>? {
        for (annotation in annotations) {
            if (annotation.annotationClass.java.name.equals(Normal::class.java.name)) {
                gsonConverterFactory.responseBodyConverter(type, annotations, retrofit)
            }
            if (annotation.annotationClass.java.name.equals(Envelope::class.java.name)) {
                val dataType =
                    TypeToken.getParameterized(Data::class.java, type).type
                val delegate: Converter<ResponseBody, *> =
                    retrofit.nextResponseBodyConverter<Any>(this, dataType, annotations)
                return Converter<ResponseBody, Any> { body ->
                    val data: Data<*>? = delegate.convert(body) as Data<*>?
                    data?.data
                }
            }
        }
        return null
    }
}



