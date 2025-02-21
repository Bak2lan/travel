insert into travels (id, about_us, contact, documentation, sustainability)
values (1, 'Welcome to our travel agency!', 'contact@travel.com', 'All documents are verified.',
        'We prioritize sustainability in tourism.'),
       (2, 'Welcome to our travel agency!', 'contact@travel.com', 'All documents are verified.',
        'We prioritize sustainability in tourism.');

INSERT INTO categories (id, day, day_tour, travel_id, image)
VALUES (1, 1, 'Day tour example 1', 1, 'image'),
       (2, 2, 'Day tour example 2', 1, 'image'),
       (3, 3, 'Day tour example 3', 1, 'image'),
       (4, 4, 'Day tour example 4', 1, 'image'),
       (5, 5, 'Day tour example 5', 1, 'image');
INSERT INTO sights (id, name_of_sight, description, title_from_video, coordinates_image)
VALUES (1, 'Алай өрөөнү', 'Алай өрөөнү – Кыргызстандын эң кооз жерлеринин бири. Бул өрөөн бийик тоолор менен курчалган жана таң калыштуу пейзаждары менен белгилүү.', 'Алай өрөөнүнүн кооздугу', '39.9245, 72.8663'),
       (2, 'Сары-Челек көлү', 'Сары-Челек – Кыргызстандын жаратылыш кереметтеринин бири. Бул көл кооз тоолордун ортосунда жайгашкан жана тунук суусу менен белгилүү.', 'Сары-Челек: жомоктогудай жер', '41.8581, 71.9976'),
       (3, 'Көл-Суу көлү', 'Көл-Суу көлү Нарын облусунда жайгашкан жана тоолор менен курчалган уникалдуу жаратылыш керемети.', 'Көл-Суу – табышмактуу көл', '40.6733, 76.4871'),
       (4, 'Ала-Арча улуттук паркы', 'Ала-Арча – Бишкектен алыс эмес жерде жайгашкан, сейилдөөгө жана альпинизмге ылайыктуу кооз парк.', 'Ала-Арча – жаратылыштын сулуулугу', '42.5543, 74.4860'),
       (5, 'Ош базары', 'Ош базары – Бишкектеги эң ири жана эски базарлардын бири. Бул жерде жергиликтүү даамдарды татып, кол өнөрчүлүк буюмдарын сатып алууга болот.', 'Ош базары: даамдар жана түстөр', '42.8746, 74.5921'),
       (6, 'Сулуу-Тоо', 'Сулуу-Тоо тоосу өзүнүн кооз пейзаждары жана жаратылыш парк катары мааниси менен белгилүү.', 'Сулуу-Тоо – бейиш жер', '41.3500, 75.8900'),
       (7, 'Таш-Рабат кербен сарайы', 'Таш-Рабат – Улуу Жибек Жолунда жайгашкан байыркы кербен сарай.', 'Таш-Рабат: тарыхтын изи', '40.8341, 75.2800'),
       (8, 'Жети-Өгүз капчыгайы', 'Жети-Өгүз – кызыл таштуу кооз капчыгай, Ысык-Көлдүн түштүк жээгинде жайгашкан.', 'Жети-Өгүз: табигый керемет', '42.1820, 77.2020'),
       (9, 'Шаркыратма Барскоон', 'Барскоон шаркыратмасы бийиктиктен агып, жомоктогудай көрүнүш түзөт.', 'Барскоон шаркыратмасы – таң калыштуу кооздук', '42.0895, 77.5993'),
       (10, 'Саймалуу-Таш', 'Саймалуу-Таш – байыркы петроглифтер сакталган уникалдуу тарыхый жай.', 'Саймалуу-Таш: байыркы сүрөттөр', '41.7417, 74.9389');


INSERT INTO about_kyrgyzstan (id, name, video_file, type,description)
VALUES (1, 'Explore Kyrgyzstan', 'intro.mp4', 'CULTURE','Кыргызстан өзүнүн кооз тоолору жана маданий мурасы менен белгилүү.
     Асман тиреген мөңгүлүү тоолор жаратылыштын кереметин тартуулайт.
     Ысык-Көл, көлдүн бермети, дүйнө жүзүндө таанымал.
     Анын байыркы тарыхы жана көчмөн маданияты аны өзгөчөлөндүрөт.
     Комуздун мукам үнү элдин жан дүйнөсүн чагылдырат.
     Кийиз үй – кыргыз элинин улуттук салтынын символу.
     Ат чабыш жана көк бөрү оюндары элибиздин рухун даңазалайт.
     Даамдуу кыргыз ашканасы өзүнүн меймандостугу менен суктандырат.
     Кең пейил эли конокту урматтоо салтын сактап келет.
     Кыргызстан – жаратылыш менен тарых бириккен керемет мекен!'),
       (2, 'Son - Kul Lake', 'sonkul.mp4', 'TRADITIONAL','Кыргызстан өзүнүн кооз тоолору жана маданий мурасы менен белгилүү.
     Асман тиреген мөңгүлүү тоолор жаратылыштын кереметин тартуулайт.
     Ысык-Көл, көлдүн бермети, дүйнө жүзүндө таанымал.
     Анын байыркы тарыхы жана көчмөн маданияты аны өзгөчөлөндүрөт.
     Комуздун мукам үнү элдин жан дүйнөсүн чагылдырат.
     Кийиз үй – кыргыз элинин улуттук салтынын символу.
     Ат чабыш жана көк бөрү оюндары элибиздин рухун даңазалайт.
     Даамдуу кыргыз ашканасы өзүнүн меймандостугу менен суктандырат.
     Кең пейил эли конокту урматтоо салтын сактап келет.
     Кыргызстан – жаратылыш менен тарых бириккен керемет мекен!'),
       (3, 'Burana Tower', 'burana.mp4', 'HISTORICAL_PLACES','Кыргызстан өзүнүн кооз тоолору жана маданий мурасы менен белгилүү.
     Асман тиреген мөңгүлүү тоолор жаратылыштын кереметин тартуулайт.
     Ысык-Көл, көлдүн бермети, дүйнө жүзүндө таанымал.
     Анын байыркы тарыхы жана көчмөн маданияты аны өзгөчөлөндүрөт.
     Комуздун мукам үнү элдин жан дүйнөсүн чагылдырат.
     Кийиз үй – кыргыз элинин улуттук салтынын символу.
     Ат чабыш жана көк бөрү оюндары элибиздин рухун даңазалайт.
     Даамдуу кыргыз ашканасы өзүнүн меймандостугу менен суктандырат.
     Кең пейил эли конокту урматтоо салтын сактап келет.
     Кыргызстан – жаратылыш менен тарых бириккен керемет мекен!');

INSERT INTO users (id, email, name, password, phone_number, role, travel_id)
VALUES (1, 'asan@gmail.com', 'Asan', '$2a$12$dyHbyL5DkJFtoGTgk0jaouZAhpmu6mfZOg7tgrXCae2VmHOIsVvqS', '+996555123456', 'ROLE_ADMIN', 1),
       (2, 'bekbolot@gmail.com', 'Bekbolot', '$2y$10$4Y4xzBVSR5n3PcbCg9sjbur0eVXCC6U8ehHbhH.G/Yizg4oILmi0u', '+996554450820', 'ROLE_ADMIN', 2);

INSERT INTO tours (id, tour_name, about_tour, days, nights, price, pax, date_from, date_to, value_category, popular, coordinates_image)
VALUES (1, 'Тянь-Шань экспедициясы', 'Бул саякат Кыргызстандын эң кооз тоолорун кыдырууга арналган. Саякатчылар жаратылыштын сулуулугунан ырахат ала алышат.', 7, 6, 500, '5-10 адам', '2025-06-01', '2025-06-07', 'Тоолор', true, '42.5000, 78.5000'),
       (2, 'Ысык-Көлдүн жээги', 'Бул тур Ысык-Көлдүн кооз жээгинде эс алуу үчүн уюштурулган. Көлдүн жылуу суусунда сүзүп, жаратылыштан ырахат аласыз.', 5, 4, 300, '10-15 адам', '2025-07-10', '2025-07-15', 'Көл', false, '42.3400, 77.0800'),
       (3, 'Көл-Сууга саякат', 'Бул тур сизди Нарын облусунун эң кооз көлдөрүнүн бирине алып барат.', 3, 2, 250, '4-8 адам', '2025-08-01', '2025-08-03', 'Тоолор', true, '40.6733, 76.4871'),
       (4, 'Ала-Арчада сейилдөө', 'Бул тур Ала-Арча паркында сейилдеп, таза абадан ырахат алуу үчүн.', 1, 0, 50, '5-15 адам', '2025-05-15', '2025-05-15', 'Жаратылыш', false, '42.5543, 74.4860'),
       (5, 'Ош базарына экскурсия', 'Ош базарын кыдырып, жергиликтүү тамактарды жана сувенирлерди сатып алууга мүмкүнчүлүк берет.', 1, 0, 30, '5-10 адам', '2025-06-10', '2025-06-10', 'Шаар', false, '42.8746, 74.5921'),
       (6, 'Таш-Рабат тарыхый саякаты', 'Бул саякат Таш-Рабат кербен сарайына багытталган.', 2, 1, 200, '6-12 адам', '2025-07-05', '2025-07-07', 'Тарых', true, '40.8341, 75.2800'),
       (7, 'Жети-Өгүзгө жөө жүрүш', 'Жети-Өгүздүн кооз капчыгайына жөө саякат.', 2, 1, 150, '4-10 адам', '2025-07-15', '2025-07-17', 'Жаратылыш', true, '42.1820, 77.2020'),
       (8, 'Барскоон шаркыратмасына тур', 'Барскоон шаркыратмасына жөө саякат жана жаратылышты көрүү.', 1, 0, 40, '5-12 адам', '2025-06-20', '2025-06-20', 'Шаркыратма', false, '42.0895, 77.5993'),
       (9, 'Саймалуу-Ташка экспедиция', 'Саймалуу-Таштагы байыркы сүрөттөрдү көрүүгө мүмкүнчүлүк.', 3, 2, 300, '5-8 адам', '2025-08-10', '2025-08-12', 'Тарых', true, '41.7417, 74.9389'),
       (10, 'Сулуу-Тоо саякаты', 'Сулуу-Тоо аймагындагы кооз тоолорду изилдөө.', 2, 1, 180, '6-10 адам', '2025-09-01', '2025-09-03', 'Жаратылыш', false, '41.3500, 75.8900');

insert into about_kyrgyzstan_images (about_kyrgyzstan_id, images)
VALUES (1, 'image1.jpg'),
       (1, 'image2.jpg'),
       (1, 'image3.jpg');


INSERT INTO sight_images (sight_id, images)
VALUES (1, 'sight1.jpg'),
       (2, 'sight2.jpg'),
       (3, 'sight3.jpg');

INSERT INTO tour_details_of_tour (tour_id, details_of_tour)
VALUES
    (1, '1 day Comfortable accommodation'),
    (1, '2 day Meals included'),
    (1, '3 day Experienced guide'),
    (2, '1 day Beach activities'),
    (2, '2 day Guided tour of the area'),
    (2, '3 day Snorkeling equipment included'),
    (3, '1 day Hiking tour'),
    (3, '2 day Fishing equipment included'),
    (3, '3 day Expert guide'),
    (4, '1 day Nature walk'),
    (4, '2 day Picnic included'),
    (4, '3 day Group guide'),
    (5, '1 day Local food tasting'),
    (5, '2 day Cultural exploration'),
    (5, '3 day Shopping tour'),
    (6, '1 day Historical site visit'),
    (6, '2 day Traditional food tasting'),
    (6, '3 day Guided historical tour'),
    (7, '1 day Nature walk'),
    (7, '2 day Photography opportunities'),
    (7, '3 day Experienced guide'),
    (8, '1 day Waterfall hike'),
    (8, '2 day Nature photography opportunities'),
    (8, '3 day Expert guide'),
    (9, '1 day Historical site visit'),
    (9, '2 day Cultural exploration'),
    (9, '3 day Traditional food tasting'),
    (10, '1 day Mountain hike'),
    (10, '2 day Local food tasting'),
    (10, '3 day Photography opportunities');

INSERT INTO tour_images (tour_id, images)
VALUES (1, 'tour1_img1.jpg'),
       (2, 'tour2_img2.jpg'),
       (3, 'tour3_img1.jpg'),
       (4, 'tour4_img1.jpg'),
       (5, 'tour5_img1.jpg');