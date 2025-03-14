INSERT INTO travels (id, about_us_name, about_us, documentation_name, documentation,
    sustainability_name, sustainability, certification_name, certification,
    address, phone_number, email, image
) VALUES (
    1, -- Укажите ID или уберите, если автоинкремент
    'Welcome to Kyrgyzsource Travel',
    'We offer unique experiences across Kyrgyzstan, blending culture, adventure, and nature.',
    'Travel Documents & Guidelines',
    'Ensure you have a valid passport and necessary visas for a hassle-free journey.',
    'Sustainability Practices',
    'We promote responsible travel by supporting local communities and protecting the environment.',
    'Certified Travel Agency',
    'Accredited by the International Tourism Organization.',
    '123 Main Street, Bishkek, Kyrgyzstan',
    '+996555123456',
    'kyrgyzsourcetravel@gmail.com',
    'https://example.com/image1.jpg'
    );
--
-- INSERT INTO sights (id, name_of_sight, description, title_from_video, coordinates_image)
-- VALUES (1, 'Алай өрөөнү', 'Алай өрөөнү – Кыргызстандын эң кооз жерлеринин бири. Бул өрөөн бийик тоолор менен курчалган жана таң калыштуу пейзаждары менен белгилүү.', 'Алай өрөөнүнүн кооздугу', '39.9245, 72.8663'),
--        (2, 'Сары-Челек көлү', 'Сары-Челек – Кыргызстандын жаратылыш кереметтеринин бири. Бул көл кооз тоолордун ортосунда жайгашкан жана тунук суусу менен белгилүү.', 'Сары-Челек: жомоктогудай жер', '41.8581, 71.9976'),
--        (3, 'Көл-Суу көлү', 'Көл-Суу көлү Нарын облусунда жайгашкан жана тоолор менен курчалган уникалдуу жаратылыш керемети.', 'Көл-Суу – табышмактуу көл', '40.6733, 76.4871'),
--        (4, 'Ала-Арча улуттук паркы', 'Ала-Арча – Бишкектен алыс эмес жерде жайгашкан, сейилдөөгө жана альпинизмге ылайыктуу кооз парк.', 'Ала-Арча – жаратылыштын сулуулугу', '42.5543, 74.4860'),
--        (5, 'Ош базары', 'Ош базары – Бишкектеги эң ири жана эски базарлардын бири. Бул жерде жергиликтүү даамдарды татып, кол өнөрчүлүк буюмдарын сатып алууга болот.', 'Ош базары: даамдар жана түстөр', '42.8746, 74.5921'),
--        (6, 'Сулуу-Тоо', 'Сулуу-Тоо тоосу өзүнүн кооз пейзаждары жана жаратылыш парк катары мааниси менен белгилүү.', 'Сулуу-Тоо – бейиш жер', '41.3500, 75.8900'),
--        (7, 'Таш-Рабат кербен сарайы', 'Таш-Рабат – Улуу Жибек Жолунда жайгашкан байыркы кербен сарай.', 'Таш-Рабат: тарыхтын изи', '40.8341, 75.2800'),
--        (8, 'Жети-Өгүз капчыгайы', 'Жети-Өгүз – кызыл таштуу кооз капчыгай, Ысык-Көлдүн түштүк жээгинде жайгашкан.', 'Жети-Өгүз: табигый керемет', '42.1820, 77.2020'),
--        (9, 'Шаркыратма Барскоон', 'Барскоон шаркыратмасы бийиктиктен агып, жомоктогудай көрүнүш түзөт.', 'Барскоон шаркыратмасы – таң калыштуу кооздук', '42.0895, 77.5993'),
--        (10, 'Саймалуу-Таш', 'Саймалуу-Таш – байыркы петроглифтер сакталган уникалдуу тарыхый жай.', 'Саймалуу-Таш: байыркы сүрөттөр', '41.7417, 74.9389');
--
--
-- INSERT INTO about_kyrgyzstan (id, name, video_file, type,description)
-- VALUES (1, 'Explore Kyrgyzstan', 'intro.mp4', 'CULTURE','Кыргызстан өзүнүн кооз тоолору жана маданий мурасы менен белгилүү.
--      Асман тиреген мөңгүлүү тоолор жаратылыштын кереметин тартуулайт.
--      Ысык-Көл, көлдүн бермети, дүйнө жүзүндө таанымал.
--      Анын байыркы тарыхы жана көчмөн маданияты аны өзгөчөлөндүрөт.
--      Комуздун мукам үнү элдин жан дүйнөсүн чагылдырат.
--      Кийиз үй – кыргыз элинин улуттук салтынын символу.
--      Ат чабыш жана көк бөрү оюндары элибиздин рухун даңазалайт.
--      Даамдуу кыргыз ашканасы өзүнүн меймандостугу менен суктандырат.
--      Кең пейил эли конокту урматтоо салтын сактап келет.
--      Кыргызстан – жаратылыш менен тарых бириккен керемет мекен!'),
--        (2, 'Son - Kul Lake', 'sonkul.mp4', 'TRADITIONAL','Кыргызстан өзүнүн кооз тоолору жана маданий мурасы менен белгилүү.
--      Асман тиреген мөңгүлүү тоолор жаратылыштын кереметин тартуулайт.
--      Ысык-Көл, көлдүн бермети, дүйнө жүзүндө таанымал.
--      Анын байыркы тарыхы жана көчмөн маданияты аны өзгөчөлөндүрөт.
--      Комуздун мукам үнү элдин жан дүйнөсүн чагылдырат.
--      Кийиз үй – кыргыз элинин улуттук салтынын символу.
--      Ат чабыш жана көк бөрү оюндары элибиздин рухун даңазалайт.
--      Даамдуу кыргыз ашканасы өзүнүн меймандостугу менен суктандырат.
--      Кең пейил эли конокту урматтоо салтын сактап келет.
--      Кыргызстан – жаратылыш менен тарых бириккен керемет мекен!'),
--        (3, 'Burana Tower', 'burana.mp4', 'HISTORICAL_PLACES','Кыргызстан өзүнүн кооз тоолору жана маданий мурасы менен белгилүү.
--      Асман тиреген мөңгүлүү тоолор жаратылыштын кереметин тартуулайт.
--      Ысык-Көл, көлдүн бермети, дүйнө жүзүндө таанымал.
--      Анын байыркы тарыхы жана көчмөн маданияты аны өзгөчөлөндүрөт.
--      Комуздун мукам үнү элдин жан дүйнөсүн чагылдырат.
--      Кийиз үй – кыргыз элинин улуттук салтынын символу.
--      Ат чабыш жана көк бөрү оюндары элибиздин рухун даңазалайт.
--      Даамдуу кыргыз ашканасы өзүнүн меймандостугу менен суктандырат.
--      Кең пейил эли конокту урматтоо салтын сактап келет.
--      Кыргызстан – жаратылыш менен тарых бириккен керемет мекен!');
--
INSERT INTO users (id, email, name, password, phone_number, role, travel_id)
VALUES (1, 'kyrgyzsourcetravel@gmail.com', 'Kyrgyz Source', '$2y$10$kB2mfGlpySZyo.c/Vd.SB.zKxnJiAqfyS0hE6IYmHzrfCaRQ5SxAy', '+996555123456', 'ROLE_ADMIN', 1),
       (2, 'bekbolot@gmail.com', 'Bekbolot', '$2y$10$4Y4xzBVSR5n3PcbCg9sjbur0eVXCC6U8ehHbhH.G/Yizg4oILmi0u', '+996554450820', 'ROLE_ADMIN', 1);

-- passwordAdmin: kyrgyzsourCetravel090603
-- INSERT INTO tours (id, travel_id, tour_name, about_tour, days_by_category, nights, price, pax, date_from, date_to, popular, coordinates_image)
-- VALUES (1, 1, 'Тянь-Шань экспедициясы', 'Бул саякат Кыргызстандын эң кооз тоолорун кыдырууга арналган. Саякатчылар жаратылыштын сулуулугунан ырахат ала алышат.', 7, 6, 500, '5-10 адам', '2025-06-01', '2025-06-07',  true, '42.5000, 78.5000'),
--        (2, 1, 'Ысык-Көлдүн жээги', 'Бул тур Ысык-Көлдүн кооз жээгинде эс алуу үчүн уюштурулган. Көлдүн жылуу суусунда сүзүп, жаратылыштан ырахат аласыз.', 5, 4, 300, '10-15 адам', '2025-07-10', '2025-07-15', false, '42.3400, 77.0800'),
--        (3, 1, 'Көл-Сууга саякат', 'Бул тур сизди Нарын облусунун эң кооз көлдөрүнүн бирине алып барат.', 3, 2, 250, '4-8 адам', '2025-08-01', '2025-08-03',  true, '40.6733, 76.4871'),
--        (4, 1, 'Ала-Арчада сейилдөө', 'Бул тур Ала-Арча паркында сейилдеп, таза абадан ырахат алуу үчүн.', 1, 0, 50, '5-15 адам', '2025-05-15', '2025-05-15', false, '42.5543, 74.4860'),
--        (5, 1, 'Ош базарына экскурсия', 'Ош базарын кыдырып, жергиликтүү тамактарды жана сувенирлерди сатып алууга мүмкүнчүлүк берет.', 1, 0, 30, '5-10 адам', '2025-06-10', '2025-06-10', false, '42.8746, 74.5921'),
--        (6, 1, 'Таш-Рабат тарыхый саякаты', 'Бул саякат Таш-Рабат кербен сарайына багытталган.', 2, 1, 200, '6-12 адам', '2025-07-05', '2025-07-07', true, '40.8341, 75.2800'),
--        (7, 1,'Жети-Өгүзгө жөө жүрүш', 'Жети-Өгүздүн кооз капчыгайына жөө саякат.', 2, 1, 150, '4-10 адам', '2025-07-15', '2025-07-17', true, '42.1820, 77.2020'),
--        (8, 2, 'Барскоон шаркыратмасына тур', 'Барскоон шаркыратмасына жөө саякат жана жаратылышты көрүү.', 1, 0, 40, '5-12 адам', '2025-06-20', '2025-06-20', false, '42.0895, 77.5993'),
--        (9, 2, 'Саймалуу-Ташка экспедиция', 'Саймалуу-Таштагы байыркы сүрөттөрдү көрүүгө мүмкүнчүлүк.', 3, 2, 300, '5-8 адам', '2025-08-10', '2025-08-12', true, '41.7417, 74.9389'),
--        (10, 2, 'Сулуу-Тоо саякаты', 'Сулуу-Тоо аймагындагы кооз тоолорду изилдөө.', 4, 1, 180, '6-10 адам', '2025-09-01', '2025-09-03', false, '41.3500, 75.8900');
--
-- INSERT INTO tours_details (id, tours_detail_name, day, about_tour_details, tour_id)
-- VALUES
--     (1, 'Тянь-Шань экспедициясы деталдары', '7 күн', 'Тянь-Шандын кооз тоолору боюнча экскурсия', 1),
--     (2, 'Ысык-Көлдүн жээгиндеги тур', '5 күн', 'Ысык-Көлдүн жээгинде эс алуу жана экскурсиялар', 1),
--     (3, 'Көл-Сууга саякат', '3 күн', 'Көл-Суу көлүнө унаа жана жөө саякат', 3),
--     (4, 'Ала-Арчада сейилдөө', '1 күн', 'Ала-Арча паркындагы жөө сейилдөө', 4),
--     (5, 'Ош базарына экскурсия деталдары', '1 күн', 'Ош базарына экскурсия, жергиликтүү тамактар жана сувенирлер', 5),
--     (6, 'Таш-Рабат тарыхый саякаты деталдары', '2 күн', 'Таш-Рабат кербен сарайына экскурсия', 6),
--     (7, 'Жети-Өгүзгө жөө жүрүш деталдары', '2 күн', 'Жети-Өгүз капчыгайында жөө саякат', 7),
--     (8, 'Барскоон шаркыратмасына тур деталдары', '1 күн', 'Барскоон шаркыратмасына жөө саякат жана жаратылышты көрүү', 8),
--     (9, 'Саймалуу-Ташка экспедиция деталдары', '3 күн', 'Саймалуу-Таштагы байыркы сүрөттөргө экскурсия', 9),
--     (10, 'Сулуу-Тоо саякаты деталдары', '2 күн', 'Сулуу-Тоо аймагындагы кооз тоолорду изилдөө', 10);
--
--
--
-- INSERT INTO tour_what_is_included (tour_id, what_is_included)
-- VALUES
--     (1, 'Туристтерге комфорттук жайгашуу'),
--     (1, 'Тамактануу жана ичимдиктер'),
--     (1, 'Таш-Рабат кербен сарайына экскурсия'),
--     (2, 'Азык-түлүк жана суусундуктар'),
--     (2, 'Шарттуу эс алуу зонасы'),
--     (3, 'Комфорттук транспорт'),
--     (3, 'Тамактануу жана ичимдиктер'),
--     (3, 'Туристтер үчүн гид'),
--     (4, 'Пикник өткөрүү'),
--     (4, 'Жаратылышты карап чыгуу'),
--     (5, 'Тамактануу'),
--     (5, 'Гиддин коштоосунда базарга баруу'),
--     (6, 'Экспедиция'),
--     (6, 'Гиддин коштоосунда тарыхый жерлерге баруу'),
--     (7, 'Жөө жүрүш'),
--     (7, 'Кооз пейзаждарды көрүү'),
--     (8, 'Шаркыратманы көрүү'),
--     (8, 'Таза аба жана жаратылыш'),
--     (9, 'Экспедиция'),
--     (9, 'Тарыхый сүрөттөрдү көрүү'),
--     (10, 'Гиддин коштоосунда саякат'),
--     (10, 'Туристтерге комфорттук транспорт');
--
-- INSERT INTO tour_what_is_excluded (tour_id, what_is_excluded)
-- VALUES
--     (1, 'Жабдыктарды ижарага алуу", "Азык-түлүк жана суусундуктар'),
--     (2, 'Туристтерге алдын ала каттоо талап кылынат'),
--     (3, 'Жабдыктарды ижарага алуу'),
--     (4, 'Сүйлөшүүлөр жана жеңил тамак'),
--     (5, 'Айрым сувенирлерди сатып алуу'),
--     (6, 'Тамактануу жана суусундуктар'),
--     (7, 'Туристтерге берилген эскертмелер'),
--     (8, 'Тамактануу жана суусундуктар'),
--     (9, 'Туристтерге эрте каттоо керек'),
--     (10, 'Тамактануу жана суусундуктар');
--
-- insert into about_kyrgyzstan_images (about_kyrgyzstan_id, images)
-- VALUES (1, 'image1.jpg'),
--        (1, 'image2.jpg'),
--        (1, 'image3.jpg');
--
--
-- INSERT INTO sight_images (sight_id, images)
-- VALUES (1, 'sight1.jpg'),
--        (2, 'sight2.jpg'),
--        (3, 'sight3.jpg');
--
--
-- INSERT INTO tour_details_image_tour_details (tour_details_id, image_tour_details)
-- VALUES
--     (1, 'tian_shan_1.jpg'),
--     (1, 'tian_shan_2.jpg'),
--     (2, 'issyk_kol_1.jpg'),
--     (2, 'issyk_kol_2.jpg'),
--     (3, 'kol_suu_1.jpg'),
--     (4, 'ala_archa_1.jpg');
--
-- INSERT INTO tour_images (tour_id, images)
-- VALUES (1, 'tour1_img1.jpg'),
--        (2, 'tour2_img2.jpg'),
--        (3, 'tour3_img1.jpg'),
--        (4, 'tour4_img1.jpg'),
--        (5, 'tour5_img1.jpg');