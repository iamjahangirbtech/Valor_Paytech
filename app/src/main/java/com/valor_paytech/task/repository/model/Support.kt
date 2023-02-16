package com.valor_paytech.task.repository.model

import com.google.gson.annotations.SerializedName


data class Support (

  @SerializedName("url"  ) var url  : String? = null,
  @SerializedName("text" ) var text : String? = null

)