package com.paularolim.grocerylist.api.common.presentation.protocols

import com.paularolim.grocerylist.api.utils.Response

interface Controller {
    fun handle(request: Any): Response
}