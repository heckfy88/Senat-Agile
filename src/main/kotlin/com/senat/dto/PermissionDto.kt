package com.senat.dto


import javax.persistence.Embeddable


@Embeddable
data class PermissionDto(

    var discussion: Boolean = false,
    var idea: Boolean = true,
    var vote: Boolean = false,
    var responsible: Boolean = false

)