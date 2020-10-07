package com.awra.stud.flickrapp.tools

enum class SizeSuffixPhoto(val maxSize: Int = 0, val key: String = "o") {
    S(75, "s"),          //  small square 75x75
    Q(150, "q"),        //  large square 150x150
    T(100, "t"),         //  thumbnail, 100 on longest side
    M(240, "m"),        //  small, 240 on longest side
    N(320, "n"),         //  small, 320 on longest side
    MEDIUM(500, "-"), //  medium, 500 on longest side
    Z(640, "z"),          //  medium 640, 640 on longest side
    C(800, "c"),          //  medium 800, 800 on longest side†
    B(1024, "b"),         //  large, 1024 on longest side*
    H(1600, "h"),         //  large 1600, 1600 on longest side†
    K(2048, "k"),          //  large 2048, 2048 on longest side†
    O();                                         //  original image, either a jpg, gif or png, depending on source format

    companion object {
        fun suffix(maxSize: Int) = values().find { it.maxSize >= maxSize } ?: O
    }
}