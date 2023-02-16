package com.valor_paytech.task.repository.model

import com.google.gson.annotations.SerializedName


data class UserDetailModel (

  @SerializedName("data"    ) var data    : Data?    = Data(),
  @SerializedName("support" ) var support : Support? = Support()

)