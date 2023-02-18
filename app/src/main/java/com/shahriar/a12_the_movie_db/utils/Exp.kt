package com.shahriar.a12_the_movie_db.utils

import java.io.IOException

class NoInternetException(message: String) : IOException(message)

class FileNotFound(message: String) : IOException(message)
