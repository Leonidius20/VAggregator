package io.github.leonidius20.vaggregator.data.videos

enum class VideoCategory(


    val titleString: String, // TODO: replace with Int res id
    val youtubeId: Int?,
    val dailymotionCategory: String?,
    val vimeoCategory: String?,



) {

    PETS_AND_ANIMALS("Pets and animals", 15, "animals", null),
    MUSIC("Music", 10, "music", "music"),
    SPORTS("Sports", 17, "sport", "sports"),
    COMEDY("Comedy", 23, "fun", "comedy"),
    TRAVEL("Travel", 19, "travel", "travel"),
    AUTO("Autos & Vehicles", 2, "auto", null),
    SHORT_FILMS("Short films", 18, "shortfilms", null),
    GAMING("Gaming", 20, "videogames", null),
    SCIENCE_AND_TECH("Science and tech", 28, "tech", null),
    NEWS_AND_POLITICS("News and politics", 25, "news", null),
    TV_SHOWS("TV Shows", 43, "tv", null),
    PEOPLE_AND_BLOGS("People", 22, "people", null),
    VIDEOBLOGGING("Videoblogging", 21, "webcam", null),
    SCHOOL_AND_EDUCATION("School and education", 27, "school", null),
    HOW_TO_AND_LIFESTYLE("How-to, style & lifestyle", 26, "lifestyle", null),
    ANIMATION("Animation", 31, null, "animation"),
    DOCUMENTARY("Documentary", 35, null, "documentary"),
    TRAILERS("Movie trailers", 44, null, null);

    override fun toString() = titleString





    // https://gist.github.com/dgp/1b24bf2961521bd75d6c
    // https://developer.dailymotion.com/api/#channel

// youtube
    /*

                                    2 - Autos & Vehicles
1 -  Film & Animation
                                    (10 - Music)
                                   (15 - Pets & Animals)
                                    17 - Sports
                                    18 - Short Movies
                                    19 - Travel & Events
                                    20 - Gaming
                                    21 - Videoblogging
                                    22 - People & Blogs
                                    23 - Comedy
24 - Entertainment
                                    25 - News & Politics
                                    26 - Howto & Style
                                    27 - Education
                                        28 - Science & Technology
29 - Nonprofits & Activism
30 - Movies
                                            31 - Anime/Animation
32 - Action/Adventure
33 - Classics
34 - Comedy
                                              35 - Documentary
36 - Drama
37 - Family
38 - Foreign
39 - Horror
40 - Sci-Fi/Fantasy
41 - Thriller
42 - Shorts
                                          43 - Shows
44 - Trailers
     */


// dailymotion:
                                                // (['animals', )
                                                // 'auto',
                                                // 'people',
                                                // 'fun',
// 'creation',
// 'school',
                                                // 'videogames',
// 'kids',
                                                // 'lifestyle',
                                                // 'shortfilms',
                                                // ('music',)
                                                // 'news',
                                                    // 'sport',
                                                // 'tech',
                                                // 'travel',
                                                // 'tv',
                                                // 'webcam']


// vimeo

    //['/categories/adsandcommercials',
                                                            // '/categories/animation',
// '/categories/brandedcontent',
                                                            // '/categories/comedy',
                                                            // '/categories/documentary'
// , '/categories/experimental',
//                                                           ('/categories/music',)
// '/categories/narrative',
//                                                           ('/categories/sports'),
                                                            // '/categories/travel']

// ['Ads and Commercials',
// 'Animation',
// 'Branded Content',
// 'Comedy',
// 'Documentary',
// 'Experimental',
// 'Music',
// 'Narrative',
// 'Sports',
// 'Travel']

    // https://api.dailymotion.com/channels

}

