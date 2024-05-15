/*
 * Copyright 2023 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.p3.offices.data

import com.p3.offices.data.model.Place

val PLACES = listOf(
    Place(
        0,
        "Stuttgart",
        "Germany: Stuttgart Office",
        48.7791797,
        9.0948154
    ),
    Place(
        1,
        "Munich",
        "Germany: Munich Office",
        48.1548813,
        11.459436
    ),
    Place(
        2,
        "Wolfsburg",
        "Germany: Wolfsburg Office",
        52.4054329,
        10.6210193
    ),
    Place(
        3,
        "Düsseldorf",
        "Germany: Düsseldorf Office",
        51.238339,
        6.6495467
    ),
    Place(
        4,
        "Hamburg",
        "Germany: Hamburg Office",
        53.5581666,
        9.6160293
    ),
    Place(
        5,
        "Berlin",
        "Germany: Berlin Office",
        52.5067296,
        13.2599285
    ),
    Place(
        6,
        "Osnabrück",
        "Germany: Osnabrück Office",
        52.2779579,
        7.97303
    ),
    Place(
        7,
        "Paris",
        "France: Paris Office",
        48.8588255,
        2.2646352
    ),
    Place(
        8,
        "Toulouse",
        "France: Toulouse Office",
        43.6006737,
        1.3504425
    ),
    Place(
        9,
        "Copenhagen",
        "Denmark: Copenhagen Office",
        55.6712398,
        12.5114245
    ),
    Place(
        10,
        "Belgrade",
        "Serbia: Belgrade Office",
        44.8148211,
        20.1107931
    ),
    Place(
        11,
        "Belgrade",
        "Serbia: Subotica Office",
        46.0936338,
        19.5917212
    ),
    Place(
        12,
        "Cluj-Napoca",
        "Romania: Cluj-Napoca Office",
        46.7826589,
        23.6052046
    ),
    Place(
        13,
        "Bucharest",
        "Romania: Bucharest Office",
        44.4377197,
        25.9387521
    ),
    Place(
        14,
        "Athens",
        "Greece: Athens Office",
        37.99083,
        23.6971399
    ),
    Place(
        15,
        "Sofia",
        "Bulgaria: Sofia Office",
        42.6954026,
        23.2415467
    ),
    Place(
        16,
        "Dobrovo",
        "Bulgaria: Dobrovo Office",
        42.1730791,
        22.9788129
    ),
    Place(
        16,
        "Prague",
        "Czech Republic: Prague Office",
        50.059553,
        14.3008201
    ),
)

class PlacesRepository {
    fun getPlaces(): List<Place> {
        return PLACES
    }

    fun getPlace(placeId: Int): Place? {
        return PLACES.find { it.id == placeId }
    }
}