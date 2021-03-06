/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.androiddevchallenge.ui.icons

inline class WeatherIcon(val value: String)

val RAINDROP = WeatherIcon("\uF078")
val LEAF = WeatherIcon("\uD83C\uDF42")
val SNOWFLAKE = WeatherIcon("\uF076")
val CLOUD = WeatherIcon("\uF041")
val CLOUDY = WeatherIcon("\uF013")

val RAIN_NEUTRAL = WeatherIcon("\uF019")
val RAIN_DAY = WeatherIcon("\uF008")
val RAIN_NIGHT = WeatherIcon("\uF028")

val CLOUDS_NEUTRAL = WeatherIcon("\uF013")
val CLOUDS_DAY = WeatherIcon("\uF002")
val CLOUDS_NIGHT = WeatherIcon("\uF086")

val SNOW_NEUTRAL = WeatherIcon("\uF064")
val SNOW_DAY = WeatherIcon("\uF065")
val SNOW_NIGHT = WeatherIcon("\uF067")

val WIND_NEUTRAL = WeatherIcon("\uF012")
val WIND_DAY = WeatherIcon("\uF085")
val WIND_NIGHT = WeatherIcon("\uF023")

val HEAVY_RAIN_NEUTRAL = WeatherIcon("\uF01A")
val HEAVY_RAIN_DAY = WeatherIcon("\uF009")
val HEAVY_RAIN_NIGHT = WeatherIcon("\uF029")

val OWM_WEATHER_ICONS = mapOf(
    "wi_owm_200" to WeatherIcon("\uF01E"),
    "wi_owm_201" to WeatherIcon("\uF01E"),
    "wi_owm_202" to WeatherIcon("\uF01E"),
    "wi_owm_210" to WeatherIcon("\uF016"),
    "wi_owm_211" to WeatherIcon("\uF016"),
    "wi_owm_212" to WeatherIcon("\uF016"),
    "wi_owm_221" to WeatherIcon("\uF016"),
    "wi_owm_230" to WeatherIcon("\uF01E"),
    "wi_owm_231" to WeatherIcon("\uF01E"),
    "wi_owm_232" to WeatherIcon("\uF01E"),
    "wi_owm_300" to WeatherIcon("\uF01C"),
    "wi_owm_301" to WeatherIcon("\uF01C"),
    "wi_owm_302" to WeatherIcon("\uF019"),
    "wi_owm_310" to WeatherIcon("\uF017"),
    "wi_owm_311" to WeatherIcon("\uF019"),
    "wi_owm_312" to WeatherIcon("\uF019"),
    "wi_owm_313" to WeatherIcon("\uF01A"),
    "wi_owm_314" to WeatherIcon("\uF019"),
    "wi_owm_321" to WeatherIcon("\uF01C"),
    "wi_owm_500" to WeatherIcon("\uF01C"),
    "wi_owm_501" to WeatherIcon("\uF019"),
    "wi_owm_502" to WeatherIcon("\uF019"),
    "wi_owm_503" to WeatherIcon("\uF019"),
    "wi_owm_504" to WeatherIcon("\uF019"),
    "wi_owm_511" to WeatherIcon("\uF017"),
    "wi_owm_520" to WeatherIcon("\uF01A"),
    "wi_owm_521" to WeatherIcon("\uF01A"),
    "wi_owm_522" to WeatherIcon("\uF01A"),
    "wi_owm_531" to WeatherIcon("\uF01D"),
    "wi_owm_600" to WeatherIcon("\uF01B"),
    "wi_owm_601" to WeatherIcon("\uF01B"),
    "wi_owm_602" to WeatherIcon("\uF0B5"),
    "wi_owm_611" to WeatherIcon("\uF017"),
    "wi_owm_612" to WeatherIcon("\uF017"),
    "wi_owm_615" to WeatherIcon("\uF017"),
    "wi_owm_616" to WeatherIcon("\uF017"),
    "wi_owm_620" to WeatherIcon("\uF017"),
    "wi_owm_621" to WeatherIcon("\uF01B"),
    "wi_owm_622" to WeatherIcon("\uF01B"),
    "wi_owm_701" to WeatherIcon("\uF01A"),
    "wi_owm_711" to WeatherIcon("\uF062"),
    "wi_owm_721" to WeatherIcon("\uF0B6"),
    "wi_owm_731" to WeatherIcon("\uF063"),
    "wi_owm_741" to WeatherIcon("\uF014"),
    "wi_owm_761" to WeatherIcon("\uF063"),
    "wi_owm_762" to WeatherIcon("\uF063"),
    "wi_owm_771" to WeatherIcon("\uF011"),
    "wi_owm_781" to WeatherIcon("\uF056"),
    "wi_owm_800" to WeatherIcon("\uF00D"),
    "wi_owm_801" to WeatherIcon("\uF011"),
    "wi_owm_802" to WeatherIcon("\uF011"),
    "wi_owm_803" to WeatherIcon("\uF012"),
    "wi_owm_804" to WeatherIcon("\uF013"),
    "wi_owm_900" to WeatherIcon("\uF056"),
    "wi_owm_901" to WeatherIcon("\uF01D"),
    "wi_owm_902" to WeatherIcon("\uF073"),
    "wi_owm_903" to WeatherIcon("\uF076"),
    "wi_owm_904" to WeatherIcon("\uF072"),
    "wi_owm_905" to WeatherIcon("\uF021"),
    "wi_owm_906" to WeatherIcon("\uF015"),
    "wi_owm_957" to WeatherIcon("\uF050"),
    "wi_owm_day_200" to WeatherIcon("\uF010"),
    "wi_owm_day_201" to WeatherIcon("\uF010"),
    "wi_owm_day_202" to WeatherIcon("\uF010"),
    "wi_owm_day_210" to WeatherIcon("\uF005"),
    "wi_owm_day_211" to WeatherIcon("\uF005"),
    "wi_owm_day_212" to WeatherIcon("\uF005"),
    "wi_owm_day_221" to WeatherIcon("\uF005"),
    "wi_owm_day_230" to WeatherIcon("\uF010"),
    "wi_owm_day_231" to WeatherIcon("\uF010"),
    "wi_owm_day_232" to WeatherIcon("\uF010"),
    "wi_owm_day_300" to WeatherIcon("\uF00B"),
    "wi_owm_day_301" to WeatherIcon("\uF00B"),
    "wi_owm_day_302" to WeatherIcon("\uF008"),
    "wi_owm_day_310" to WeatherIcon("\uF008"),
    "wi_owm_day_311" to WeatherIcon("\uF008"),
    "wi_owm_day_312" to WeatherIcon("\uF008"),
    "wi_owm_day_313" to WeatherIcon("\uF008"),
    "wi_owm_day_314" to WeatherIcon("\uF008"),
    "wi_owm_day_321" to WeatherIcon("\uF00B"),
    "wi_owm_day_500" to WeatherIcon("\uF00B"),
    "wi_owm_day_501" to WeatherIcon("\uF008"),
    "wi_owm_day_502" to WeatherIcon("\uF008"),
    "wi_owm_day_503" to WeatherIcon("\uF008"),
    "wi_owm_day_504" to WeatherIcon("\uF008"),
    "wi_owm_day_511" to WeatherIcon("\uF006"),
    "wi_owm_day_520" to WeatherIcon("\uF009"),
    "wi_owm_day_521" to WeatherIcon("\uF009"),
    "wi_owm_day_522" to WeatherIcon("\uF009"),
    "wi_owm_day_531" to WeatherIcon("\uF00E"),
    "wi_owm_day_600" to WeatherIcon("\uF00A"),
    "wi_owm_day_601" to WeatherIcon("\uF0B2"),
    "wi_owm_day_602" to WeatherIcon("\uF00A"),
    "wi_owm_day_611" to WeatherIcon("\uF006"),
    "wi_owm_day_612" to WeatherIcon("\uF006"),
    "wi_owm_day_615" to WeatherIcon("\uF006"),
    "wi_owm_day_616" to WeatherIcon("\uF006"),
    "wi_owm_day_620" to WeatherIcon("\uF006"),
    "wi_owm_day_621" to WeatherIcon("\uF00A"),
    "wi_owm_day_622" to WeatherIcon("\uF00A"),
    "wi_owm_day_701" to WeatherIcon("\uF009"),
    "wi_owm_day_711" to WeatherIcon("\uF062"),
    "wi_owm_day_721" to WeatherIcon("\uF0B6"),
    "wi_owm_day_731" to WeatherIcon("\uF063"),
    "wi_owm_day_741" to WeatherIcon("\uF003"),
    "wi_owm_day_761" to WeatherIcon("\uF063"),
    "wi_owm_day_762" to WeatherIcon("\uF063"),
    "wi_owm_day_781" to WeatherIcon("\uF056"),
    "wi_owm_day_800" to WeatherIcon("\uF00D"),
    "wi_owm_day_801" to WeatherIcon("\uF000"),
    "wi_owm_day_802" to WeatherIcon("\uF000"),
    "wi_owm_day_803" to WeatherIcon("\uF000"),
    "wi_owm_day_804" to WeatherIcon("\uF00C"),
    "wi_owm_day_900" to WeatherIcon("\uF056"),
    "wi_owm_day_902" to WeatherIcon("\uF073"),
    "wi_owm_day_903" to WeatherIcon("\uF076"),
    "wi_owm_day_904" to WeatherIcon("\uF072"),
    "wi_owm_day_906" to WeatherIcon("\uF004"),
    "wi_owm_day_957" to WeatherIcon("\uF050"),
    "wi_owm_night_200" to WeatherIcon("\uF02D"),
    "wi_owm_night_201" to WeatherIcon("\uF02D"),
    "wi_owm_night_202" to WeatherIcon("\uF02D"),
    "wi_owm_night_210" to WeatherIcon("\uF025"),
    "wi_owm_night_211" to WeatherIcon("\uF025"),
    "wi_owm_night_212" to WeatherIcon("\uF025"),
    "wi_owm_night_221" to WeatherIcon("\uF025"),
    "wi_owm_night_230" to WeatherIcon("\uF02D"),
    "wi_owm_night_231" to WeatherIcon("\uF02D"),
    "wi_owm_night_232" to WeatherIcon("\uF02D"),
    "wi_owm_night_300" to WeatherIcon("\uF02B"),
    "wi_owm_night_301" to WeatherIcon("\uF02B"),
    "wi_owm_night_302" to WeatherIcon("\uF028"),
    "wi_owm_night_310" to WeatherIcon("\uF028"),
    "wi_owm_night_311" to WeatherIcon("\uF028"),
    "wi_owm_night_312" to WeatherIcon("\uF028"),
    "wi_owm_night_313" to WeatherIcon("\uF028"),
    "wi_owm_night_314" to WeatherIcon("\uF028"),
    "wi_owm_night_321" to WeatherIcon("\uF02B"),
    "wi_owm_night_500" to WeatherIcon("\uF02B"),
    "wi_owm_night_501" to WeatherIcon("\uF028"),
    "wi_owm_night_502" to WeatherIcon("\uF028"),
    "wi_owm_night_503" to WeatherIcon("\uF028"),
    "wi_owm_night_504" to WeatherIcon("\uF028"),
    "wi_owm_night_511" to WeatherIcon("\uF026"),
    "wi_owm_night_520" to WeatherIcon("\uF029"),
    "wi_owm_night_521" to WeatherIcon("\uF029"),
    "wi_owm_night_522" to WeatherIcon("\uF029"),
    "wi_owm_night_531" to WeatherIcon("\uF02C"),
    "wi_owm_night_600" to WeatherIcon("\uF02A"),
    "wi_owm_night_601" to WeatherIcon("\uF0B4"),
    "wi_owm_night_602" to WeatherIcon("\uF02A"),
    "wi_owm_night_611" to WeatherIcon("\uF026"),
    "wi_owm_night_612" to WeatherIcon("\uF026"),
    "wi_owm_night_615" to WeatherIcon("\uF026"),
    "wi_owm_night_616" to WeatherIcon("\uF026"),
    "wi_owm_night_620" to WeatherIcon("\uF026"),
    "wi_owm_night_621" to WeatherIcon("\uF02A"),
    "wi_owm_night_622" to WeatherIcon("\uF02A"),
    "wi_owm_night_701" to WeatherIcon("\uF029"),
    "wi_owm_night_711" to WeatherIcon("\uF062"),
    "wi_owm_night_721" to WeatherIcon("\uF0B6"),
    "wi_owm_night_731" to WeatherIcon("\uF063"),
    "wi_owm_night_741" to WeatherIcon("\uF04A"),
    "wi_owm_night_761" to WeatherIcon("\uF063"),
    "wi_owm_night_762" to WeatherIcon("\uF063"),
    "wi_owm_night_781" to WeatherIcon("\uF056"),
    "wi_owm_night_800" to WeatherIcon("\uF02E"),
    "wi_owm_night_801" to WeatherIcon("\uF022"),
    "wi_owm_night_802" to WeatherIcon("\uF022"),
    "wi_owm_night_803" to WeatherIcon("\uF022"),
    "wi_owm_night_804" to WeatherIcon("\uF086"),
    "wi_owm_night_900" to WeatherIcon("\uF056"),
    "wi_owm_night_902" to WeatherIcon("\uF073"),
    "wi_owm_night_903" to WeatherIcon("\uF076"),
    "wi_owm_night_904" to WeatherIcon("\uF072"),
    "wi_owm_night_906" to WeatherIcon("\uF024"),
    "wi_owm_night_957" to WeatherIcon("\uF050")
)
