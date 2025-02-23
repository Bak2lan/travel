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

INSERT INTO tours (id, travel_id, tour_name, about_tour, days, nights, price, pax, date_from, date_to, value_category, popular, coordinates_image)
VALUES (1, 1,'Тянь-Шань экспедициясы', 'Бул саякат Кыргызстандын эң кооз тоолорун кыдырууга арналган. Саякатчылар жаратылыштын сулуулугунан ырахат ала алышат.', 7, 6, 500, '5-10 адам', '2025-06-01', '2025-06-07', 'Тоолор', true, '42.5000, 78.5000'),
       (2, 1,'Ысык-Көлдүн жээги', 'Бул тур Ысык-Көлдүн кооз жээгинде эс алуу үчүн уюштурулган. Көлдүн жылуу суусунда сүзүп, жаратылыштан ырахат аласыз.', 5, 4, 300, '10-15 адам', '2025-07-10', '2025-07-15', 'Көл', false, '42.3400, 77.0800'),
       (3, 1,'Көл-Сууга саякат', 'Бул тур сизди Нарын облусунун эң кооз көлдөрүнүн бирине алып барат.', 3, 2, 250, '4-8 адам', '2025-08-01', '2025-08-03', 'Тоолор', true, '40.6733, 76.4871'),
       (4, 1,'Ала-Арчада сейилдөө', 'Бул тур Ала-Арча паркында сейилдеп, таза абадан ырахат алуу үчүн.', 1, 0, 50, '5-15 адам', '2025-05-15', '2025-05-15', 'Жаратылыш', false, '42.5543, 74.4860'),
       (5, 1,'Ош базарына экскурсия', 'Ош базарын кыдырып, жергиликтүү тамактарды жана сувенирлерди сатып алууга мүмкүнчүлүк берет.', 1, 0, 30, '5-10 адам', '2025-06-10', '2025-06-10', 'Шаар', false, '42.8746, 74.5921'),
       (6, 1,'Таш-Рабат тарыхый саякаты', 'Бул саякат Таш-Рабат кербен сарайына багытталган.', 2, 1, 200, '6-12 адам', '2025-07-05', '2025-07-07', 'Тарых', true, '40.8341, 75.2800'),
       (7, 1,'Жети-Өгүзгө жөө жүрүш', 'Жети-Өгүздүн кооз капчыгайына жөө саякат.', 2, 1, 150, '4-10 адам', '2025-07-15', '2025-07-17', 'Жаратылыш', true, '42.1820, 77.2020'),
       (8, 2,'Барскоон шаркыратмасына тур', 'Барскоон шаркыратмасына жөө саякат жана жаратылышты көрүү.', 1, 0, 40, '5-12 адам', '2025-06-20', '2025-06-20', 'Шаркыратма', false, '42.0895, 77.5993'),
       (9, 2,'Саймалуу-Ташка экспедиция', 'Саймалуу-Таштагы байыркы сүрөттөрдү көрүүгө мүмкүнчүлүк.', 3, 2, 300, '5-8 адам', '2025-08-10', '2025-08-12', 'Тарых', true, '41.7417, 74.9389'),
       (10, 2,'Сулуу-Тоо саякаты', 'Сулуу-Тоо аймагындагы кооз тоолорду изилдөө.', 2, 1, 180, '6-10 адам', '2025-09-01', '2025-09-03', 'Жаратылыш', false, '41.3500, 75.8900');

INSERT INTO tour_what_is_included (tour_id, what_is_included)
VALUES
    (1, 'Туристтерге комфорттук жайгашуу'),
    (1, 'Тамактануу жана ичимдиктер'),
    (1, 'Таш-Рабат кербен сарайына экскурсия'),
    (2, 'Азык-түлүк жана суусундуктар'),
    (2, 'Шарттуу эс алуу зонасы'),
    (3, 'Комфорттук транспорт'),
    (3, 'Тамактануу жана ичимдиктер'),
    (3, 'Туристтер үчүн гид'),
    (4, 'Пикник өткөрүү'),
    (4, 'Жаратылышты карап чыгуу'),
    (5, 'Тамактануу'),
    (5, 'Гиддин коштоосунда базарга баруу'),
    (6, 'Экспедиция'),
    (6, 'Гиддин коштоосунда тарыхый жерлерге баруу'),
    (7, 'Жөө жүрүш'),
    (7, 'Кооз пейзаждарды көрүү'),
    (8, 'Шаркыратманы көрүү'),
    (8, 'Таза аба жана жаратылыш'),
    (9, 'Экспедиция'),
    (9, 'Тарыхый сүрөттөрдү көрүү'),
    (10, 'Гиддин коштоосунда саякат'),
    (10, 'Туристтерге комфорттук транспорт');

INSERT INTO tour_what_is_excluded (tour_id, what_is_excluded)
VALUES
    (1, 'Жабдыктарды ижарага алуу", "Азык-түлүк жана суусундуктар'),
    (2, 'Туристтерге алдын ала каттоо талап кылынат'),
    (3, 'Жабдыктарды ижарага алуу'),
    (4, 'Сүйлөшүүлөр жана жеңил тамак'),
    (5, 'Айрым сувенирлерди сатып алуу'),
    (6, 'Тамактануу жана суусундуктар'),
    (7, 'Туристтерге берилген эскертмелер'),
    (8, 'Тамактануу жана суусундуктар'),
    (9, 'Туристтерге эрте каттоо керек'),
    (10, 'Тамактануу жана суусундуктар');

insert into about_kyrgyzstan_images (about_kyrgyzstan_id, images)
VALUES (1, 'image1.jpg'),
       (1, 'image2.jpg'),
       (1, 'image3.jpg');


INSERT INTO sight_images (sight_id, images)
VALUES (1, 'sight1.jpg'),
       (2, 'sight2.jpg'),
       (3, 'sight3.jpg');

INSERT INTO tour_details_of_tour (tour_id, details_of_tour, details_of_tour_key)
VALUES
    (1, 'Жашоого ыңгайлуу шарттар', '1 күн бир'),
    (1, 'Тамак-аш камтылган', '2 күн эки'),
    (1, 'Тажрыйбалуу жетекчи', '3 күн үч'),
    (1, 'Жергиликтүү маданият менен таанышуу', '4 күн төрт'),
    (1, 'Арнайы фото сессия', '5 күн беш'),
    (1, 'Табигый көркөм жерлерге саякат', '6 күн алты'),
    (1, 'Сувенирлерди сатып алуу мүмкүнчүлүгү', '7 күн жети'),
    (1, 'Көптөгөн кызыктуу иш-чаралар', '8 күн сегиз'),
    (1, 'Культуралык тапшырмалар', '9 күн тогуз'),
    (1, 'Кечки тамактар камтылган', '10 күн он'),
    (2, 'Унаа менен камсыздоо', '1 күн бир'),
    (2, 'Жергиликтүү гидтер менен саякат', '2 күн эки'),
    (2, 'Музейлерге экскурсия', '3 күн үч'),
    (2, 'Кичи группалар менен саякат', '4 күн төрт'),
    (2, 'Пикник аймактары менен таанышуу', '5 күн беш'),
    (2, 'Кошумча турлар', '6 күн алты'),
    (2, 'Көркөм искусствого арналган экскурсиялар', '7 күн жети'),
    (2, 'Арнайы майрамдар', '8 күн сегиз'),
    (2, 'Көшөдөгү соода базарларында жүрүү', '9 күн тогуз'),
    (2, 'Табигый жерлерге саякат', '10 күн он'),
    (3, 'Экскурсиялар менен саякат', '1 күн бир'),
    (3, 'Жергіліктүү тамактарды колдонуу', '2 күн эки'),
    (3, 'Көптөгөн кайрылуулар', '3 күн үч'),
    (3, 'Сафарыларды уюштуруу', '4 күн төрт'),
    (3, 'Натыйжалуу коомдук транспортту колдонуу', '5 күн беш'),
    (3, 'Туура жолдорго өтүү', '6 күн алты'),
    (3, 'Пейзаждык жерлерге саякат', '7 күн жети'),
    (3, 'Ата мекендик маданиятты таанытуу', '8 күн сегиз'),
    (3, 'Эсте сактоо мүмкүнчүлүктөрү', '9 күн тогуз'),
    (3, 'Гостиницалар менен ыңгайлуу шарттар', '10 күн он'),
    (4, 'Романтикалык аттракциондар', '1 күн бир'),
    (4, 'Биригүү жана сактоо иш-чаралары', '2 күн эки'),
    (4, 'Базарларда сауда жасоо', '3 күн үч'),
    (4, 'Туризм жана экскурсия', '4 күн төрт'),
    (4, 'Арнайы жадыбарлар менен сурот тартуу', '5 күн беш'),
    (4, 'Өзүнчө уюштурулган экскурсиялар', '6 күн алты'),
    (4, 'Тамак-аш өндүрүү жана аны жасоо', '7 күн жети'),
    (4, 'Биринчи эшиктерден сунуштар', '8 күн сегиз'),
    (4, 'Эстеликтерди берүү', '9 күн тогуз'),
    (4, 'Кооз жерлерге жакындык', '10 күн он'),
    (5, 'Жашоого ыңгайлуу шарттар', '1 күн бир'),
    (5, 'Тамак-аш камтылган', '2 күн эки'),
    (5, 'Тажрыйбалуу жетекчи', '3 күн үч'),
    (5, 'Жергиликтүү маданият менен таанышуу', '4 күн төрт'),
    (5, 'Арнайы фото сессия', '5 күн беш'),
    (5, 'Табигый көркөм жерлерге саякат', '6 күн алты'),
    (5, 'Сувенирлерди сатып алуу мүмкүнчүлүгү', '7 күн жети'),
    (5, 'Көптөгөн кызыктуу иш-чаралар', '8 күн сегиз'),
    (5, 'Культуралык тапшырмалар', '9 күн тогуз'),
    (5, 'Кечки тамактар камтылган', '10 күн он'),
    (6, 'Жашоого ыңгайлуу шарттар', '1 күн бир'),
    (6, 'Тамак-аш камтылган', '2 күн эки'),
    (6, 'Тажрыйбалуу жетекчи', '3 күн үч'),
    (6, 'Жергиликтүү маданият менен таанышуу', '4 күн төрт'),
    (6, 'Арнайы фото сессия', '5 күн беш'),
    (6, 'Табигый көркөм жерлерге саякат', '6 күн алты'),
    (6, 'Сувенирлерди сатып алуу мүмкүнчүлүгү', '7 күн жети'),
    (6, 'Көптөгөн кызыктуу иш-чаралар', '8 күн сегиз'),
    (6, 'Культуралык тапшырмалар', '9 күн тогуз'),
    (6, 'Кечки тамактар камтылган', '10 күн он'),
    (7, 'Унаа менен камсыздоо', '1 күн бир'),
    (7, 'Жергиликтүү гидтер менен саякат', '2 күн эки'),
    (7, 'Музейлерге экскурсия', '3 күн үч'),
    (7, 'Кичи группалар менен саякат', '4 күн төрт'),
    (7, 'Пикник аймактары менен таанышуу', '5 күн беш'),
    (7, 'Кошумча турлар', '6 күн алты'),
    (7, 'Көркөм искусствого арналган экскурсиялар', '7 күн жети'),
    (7, 'Арнайы майрамдар', '8 күн сегиз'),
    (7, 'Көшөдөгү соода базарларында жүрүү', '9 күн тогуз'),
    (7, 'Табигый жерлерге саякат', '10 күн он'),
    (8, 'Экскурсиялар менен саякат', '1 күн бир'),
    (8, 'Жергіліктүү тамактарды колдонуу', '2 күн эки'),
    (8, 'Көптөгөн кайрылуулар', '3 күн үч'),
    (8, 'Сафарыларды уюштуруу', '4 күн төрт'),
    (8, 'Натыйжалуу коомдук транспортту колдонуу', '5 күн беш'),
    (8, 'Туура жолдорго өтүү', '6 күн алты'),
    (8, 'Пейзаждык жерлерге саякат', '7 күн жети'),
    (8, 'Ата мекендик маданиятты таанытуу', '8 күн сегиз'),
    (8, 'Эсте сактоо мүмкүнчүлүктөрү', '9 күн тогуз'),
    (8, 'Гостиницалар менен ыңгайлуу шарттар', '10 күн он'),
    (9, 'Романтикалык аттракциондар', '1 күн бир'),
    (9, 'Биригүү жана сактоо иш-чаралары', '2 күн эки'),
    (9, 'Базарларда сауда жасоо', '3 күн үч'),
    (9, 'Туризм жана экскурсия', '4 күн төрт'),
    (9, 'Арнайы жадыбарлар менен сурот тартуу', '5 күн беш'),
    (9, 'Өзүнчө уюштурулган экскурсиялар', '6 күн алты'),
    (9, 'Тамак-аш өндүрүү жана аны жасоо', '7 күн жети'),
    (9, 'Биринчи эшиктерден сунуштар', '8 күн сегиз'),
    (9, 'Эстеликтерди берүү', '9 күн тогуз'),
    (9, 'Кооз жерлерге жакындык', '10 күн он'),
    (10, 'Жашоого ыңгайлуу шарттар', '1 күн бир'),
    (10, 'Тамак-аш камтылган', '2 күн эки'),
    (10, 'Тажрыйбалуу жетекчи', '3 күн үч'),
    (10, 'Жергиликтүү маданият менен таанышуу', '4 күн төрт'),
    (10, 'Арнайы фото сессия', '5 күн беш'),
    (10, 'Табигый көркөм жерлерге саякат', '6 күн алты'),
    (10, 'Сувенирлерди сатып алуу мүмкүнчүлүгү', '7 күн жети'),
    (10, 'Көптөгөн кызыктуу иш-чаралар', '8 күн сегиз'),
    (10, 'Культуралык тапшырмалар', '9 күн тогуз'),
    (10, 'Кечки тамактар камтылган', '10 күн он');

INSERT INTO tour_images (tour_id, images)
VALUES (1, 'tour1_img1.jpg'),
       (2, 'tour2_img2.jpg'),
       (3, 'tour3_img1.jpg'),
       (4, 'tour4_img1.jpg'),
       (5, 'tour5_img1.jpg');