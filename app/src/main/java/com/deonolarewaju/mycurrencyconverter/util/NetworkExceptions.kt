package com.deonolarewaju.mycurrencyconverter.util

import java.io.IOException

class NoInternetException(message: String): IOException(message)

class ApiException(message: String): IOException(message)