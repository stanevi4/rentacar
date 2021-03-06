PGDMP                         t           rentacar    9.5.2    9.5.2 �    �           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                       false            �           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                       false                        2615    18909    public    SCHEMA        CREATE SCHEMA public;
    DROP SCHEMA public;
             postgres    false            �            1259    19040    booking    TABLE     �  CREATE TABLE booking (
    id integer NOT NULL,
    created timestamp without time zone NOT NULL,
    client_id integer NOT NULL,
    car_id integer NOT NULL,
    location_from_id integer NOT NULL,
    location_to_id integer NOT NULL,
    date_from timestamp without time zone NOT NULL,
    date_to timestamp without time zone NOT NULL,
    summ numeric NOT NULL,
    reason_id integer,
    damage character varying,
    note character varying,
    order_status integer NOT NULL
);
    DROP TABLE public.booking;
       public         postgres    false    7            �            1259    19032    booking_car_id_seq    SEQUENCE     t   CREATE SEQUENCE booking_car_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 )   DROP SEQUENCE public.booking_car_id_seq;
       public       postgres    false    214    7            �           0    0    booking_car_id_seq    SEQUENCE OWNED BY     ;   ALTER SEQUENCE booking_car_id_seq OWNED BY booking.car_id;
            public       postgres    false    210            �            1259    19030    booking_client_id_seq    SEQUENCE     w   CREATE SEQUENCE booking_client_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 ,   DROP SEQUENCE public.booking_client_id_seq;
       public       postgres    false    7    214            �           0    0    booking_client_id_seq    SEQUENCE OWNED BY     A   ALTER SEQUENCE booking_client_id_seq OWNED BY booking.client_id;
            public       postgres    false    209            �            1259    19028    booking_id_seq    SEQUENCE     p   CREATE SEQUENCE booking_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 %   DROP SEQUENCE public.booking_id_seq;
       public       postgres    false    7    214            �           0    0    booking_id_seq    SEQUENCE OWNED BY     3   ALTER SEQUENCE booking_id_seq OWNED BY booking.id;
            public       postgres    false    208            �            1259    19034    booking_location_from_id_seq    SEQUENCE     ~   CREATE SEQUENCE booking_location_from_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 3   DROP SEQUENCE public.booking_location_from_id_seq;
       public       postgres    false    7    214            �           0    0    booking_location_from_id_seq    SEQUENCE OWNED BY     O   ALTER SEQUENCE booking_location_from_id_seq OWNED BY booking.location_from_id;
            public       postgres    false    211            �            1259    19036    booking_location_to_id_seq    SEQUENCE     |   CREATE SEQUENCE booking_location_to_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 1   DROP SEQUENCE public.booking_location_to_id_seq;
       public       postgres    false    214    7            �           0    0    booking_location_to_id_seq    SEQUENCE OWNED BY     K   ALTER SEQUENCE booking_location_to_id_seq OWNED BY booking.location_to_id;
            public       postgres    false    212            �            1259    19038    booking_reason_id_seq    SEQUENCE     w   CREATE SEQUENCE booking_reason_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 ,   DROP SEQUENCE public.booking_reason_id_seq;
       public       postgres    false    214    7            �           0    0    booking_reason_id_seq    SEQUENCE OWNED BY     A   ALTER SEQUENCE booking_reason_id_seq OWNED BY booking.reason_id;
            public       postgres    false    213            �            1259    18936    car    TABLE     l  CREATE TABLE car (
    id integer NOT NULL,
    name character varying(100) NOT NULL,
    type_id integer NOT NULL,
    location_id integer NOT NULL,
    car_status integer NOT NULL,
    status_changed timestamp without time zone,
    reg_number character varying(20),
    year_prodaction integer,
    description character varying,
    image character varying
);
    DROP TABLE public.car;
       public         postgres    false    7            �            1259    18930 
   car_id_seq    SEQUENCE     l   CREATE SEQUENCE car_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 !   DROP SEQUENCE public.car_id_seq;
       public       postgres    false    7    189            �           0    0 
   car_id_seq    SEQUENCE OWNED BY     +   ALTER SEQUENCE car_id_seq OWNED BY car.id;
            public       postgres    false    186            �            1259    18934    car_location_id_seq    SEQUENCE     u   CREATE SEQUENCE car_location_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 *   DROP SEQUENCE public.car_location_id_seq;
       public       postgres    false    7    189            �           0    0    car_location_id_seq    SEQUENCE OWNED BY     =   ALTER SEQUENCE car_location_id_seq OWNED BY car.location_id;
            public       postgres    false    188            �            1259    18932    car_type_id_seq    SEQUENCE     q   CREATE SEQUENCE car_type_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 &   DROP SEQUENCE public.car_type_id_seq;
       public       postgres    false    189    7            �           0    0    car_type_id_seq    SEQUENCE OWNED BY     5   ALTER SEQUENCE car_type_id_seq OWNED BY car.type_id;
            public       postgres    false    187            �            1259    18977    invoice    TABLE     �   CREATE TABLE invoice (
    id integer NOT NULL,
    created timestamp without time zone NOT NULL,
    summ numeric NOT NULL,
    note character varying,
    order_id integer NOT NULL
);
    DROP TABLE public.invoice;
       public         postgres    false    7            �            1259    18973    invoice_id_seq    SEQUENCE     p   CREATE SEQUENCE invoice_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 %   DROP SEQUENCE public.invoice_id_seq;
       public       postgres    false    7    199            �           0    0    invoice_id_seq    SEQUENCE OWNED BY     3   ALTER SEQUENCE invoice_id_seq OWNED BY invoice.id;
            public       postgres    false    197            �            1259    18975    invoice_order_id_seq    SEQUENCE     v   CREATE SEQUENCE invoice_order_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 +   DROP SEQUENCE public.invoice_order_id_seq;
       public       postgres    false    7    199            �           0    0    invoice_order_id_seq    SEQUENCE OWNED BY     ?   ALTER SEQUENCE invoice_order_id_seq OWNED BY invoice.order_id;
            public       postgres    false    198            �            1259    18997    location    TABLE     �   CREATE TABLE location (
    id integer NOT NULL,
    name character varying(100) NOT NULL,
    lat numeric NOT NULL,
    lng numeric NOT NULL
);
    DROP TABLE public.location;
       public         postgres    false    7            �            1259    18995    location_id_seq    SEQUENCE     q   CREATE SEQUENCE location_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 &   DROP SEQUENCE public.location_id_seq;
       public       postgres    false    203    7            �           0    0    location_id_seq    SEQUENCE OWNED BY     5   ALTER SEQUENCE location_id_seq OWNED BY location.id;
            public       postgres    false    202            �            1259    18959    order    TABLE     �  CREATE TABLE "order" (
    id integer NOT NULL,
    created timestamp without time zone NOT NULL,
    client_id integer NOT NULL,
    car_id integer NOT NULL,
    date_from timestamp without time zone NOT NULL,
    date_to timestamp without time zone NOT NULL,
    summ numeric NOT NULL,
    location_from integer NOT NULL,
    location_to integer NOT NULL,
    reason_id integer NOT NULL,
    damage character varying,
    note character varying,
    order_status integer NOT NULL
);
    DROP TABLE public."order";
       public         postgres    false    7            �            1259    18951    order_car_id_seq    SEQUENCE     r   CREATE SEQUENCE order_car_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 '   DROP SEQUENCE public.order_car_id_seq;
       public       postgres    false    7    196            �           0    0    order_car_id_seq    SEQUENCE OWNED BY     9   ALTER SEQUENCE order_car_id_seq OWNED BY "order".car_id;
            public       postgres    false    192            �            1259    18949    order_client_id_seq    SEQUENCE     u   CREATE SEQUENCE order_client_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 *   DROP SEQUENCE public.order_client_id_seq;
       public       postgres    false    196    7            �           0    0    order_client_id_seq    SEQUENCE OWNED BY     ?   ALTER SEQUENCE order_client_id_seq OWNED BY "order".client_id;
            public       postgres    false    191            �            1259    18947    order_id_seq    SEQUENCE     n   CREATE SEQUENCE order_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 #   DROP SEQUENCE public.order_id_seq;
       public       postgres    false    196    7            �           0    0    order_id_seq    SEQUENCE OWNED BY     1   ALTER SEQUENCE order_id_seq OWNED BY "order".id;
            public       postgres    false    190            �            1259    18953    order_location_from_seq    SEQUENCE     y   CREATE SEQUENCE order_location_from_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 .   DROP SEQUENCE public.order_location_from_seq;
       public       postgres    false    7    196            �           0    0    order_location_from_seq    SEQUENCE OWNED BY     G   ALTER SEQUENCE order_location_from_seq OWNED BY "order".location_from;
            public       postgres    false    193            �            1259    18955    order_location_to_seq    SEQUENCE     w   CREATE SEQUENCE order_location_to_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 ,   DROP SEQUENCE public.order_location_to_seq;
       public       postgres    false    7    196            �           0    0    order_location_to_seq    SEQUENCE OWNED BY     C   ALTER SEQUENCE order_location_to_seq OWNED BY "order".location_to;
            public       postgres    false    194            �            1259    18957    order_reason_id_seq    SEQUENCE     u   CREATE SEQUENCE order_reason_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 *   DROP SEQUENCE public.order_reason_id_seq;
       public       postgres    false    7    196            �           0    0    order_reason_id_seq    SEQUENCE OWNED BY     ?   ALTER SEQUENCE order_reason_id_seq OWNED BY "order".reason_id;
            public       postgres    false    195            �            1259    18989    reason    TABLE     [   CREATE TABLE reason (
    id integer NOT NULL,
    name character varying(100) NOT NULL
);
    DROP TABLE public.reason;
       public         postgres    false    7            �            1259    18987    reason_id_seq    SEQUENCE     o   CREATE SEQUENCE reason_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 $   DROP SEQUENCE public.reason_id_seq;
       public       postgres    false    201    7            �           0    0    reason_id_seq    SEQUENCE OWNED BY     1   ALTER SEQUENCE reason_id_seq OWNED BY reason.id;
            public       postgres    false    200            �            1259    19019    setting    TABLE       CREATE TABLE setting (
    id integer NOT NULL,
    min_booking_length integer DEFAULT 0 NOT NULL,
    car_while_pending integer DEFAULT 0 NOT NULL,
    deposit_payment integer DEFAULT 10 NOT NULL,
    currency integer NOT NULL,
    car_between_pending integer NOT NULL
);
    DROP TABLE public.setting;
       public         postgres    false    7            �            1259    19017    setting_id_seq    SEQUENCE     p   CREATE SEQUENCE setting_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 %   DROP SEQUENCE public.setting_id_seq;
       public       postgres    false    207    7            �           0    0    setting_id_seq    SEQUENCE OWNED BY     3   ALTER SEQUENCE setting_id_seq OWNED BY setting.id;
            public       postgres    false    206            �            1259    19008    type    TABLE     
  CREATE TABLE type (
    id integer NOT NULL,
    name character varying(100) NOT NULL,
    num_passengers integer NOT NULL,
    num_bags integer NOT NULL,
    num_doors integer NOT NULL,
    transmission_type integer NOT NULL,
    price_per_hour numeric NOT NULL
);
    DROP TABLE public.type;
       public         postgres    false    7            �            1259    19006    type_id_seq    SEQUENCE     m   CREATE SEQUENCE type_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 "   DROP SEQUENCE public.type_id_seq;
       public       postgres    false    205    7            �           0    0    type_id_seq    SEQUENCE OWNED BY     -   ALTER SEQUENCE type_id_seq OWNED BY type.id;
            public       postgres    false    204            �            1259    18924    user_credentials    TABLE     �   CREATE TABLE user_credentials (
    id integer NOT NULL,
    email character varying(100) NOT NULL,
    password character varying(100) NOT NULL,
    role integer NOT NULL
);
 $   DROP TABLE public.user_credentials;
       public         postgres    false    7            �            1259    18922    user_credentials_id_seq    SEQUENCE     y   CREATE SEQUENCE user_credentials_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 .   DROP SEQUENCE public.user_credentials_id_seq;
       public       postgres    false    185    7            �           0    0    user_credentials_id_seq    SEQUENCE OWNED BY     E   ALTER SEQUENCE user_credentials_id_seq OWNED BY user_credentials.id;
            public       postgres    false    184            �            1259    18913    user_profile    TABLE     �  CREATE TABLE user_profile (
    id integer NOT NULL,
    first_name character varying(100) NOT NULL,
    last_name character varying(100) NOT NULL,
    created timestamp without time zone NOT NULL,
    license_number character varying(20),
    birth_day date,
    address character varying(100),
    phone_number character varying(100) NOT NULL,
    city character varying(100),
    region character varying(100),
    zip_code character varying(100)
);
     DROP TABLE public.user_profile;
       public         postgres    false    7            �            1259    18911    user_profile_id_seq    SEQUENCE     u   CREATE SEQUENCE user_profile_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 *   DROP SEQUENCE public.user_profile_id_seq;
       public       postgres    false    183    7            �           0    0    user_profile_id_seq    SEQUENCE OWNED BY     =   ALTER SEQUENCE user_profile_id_seq OWNED BY user_profile.id;
            public       postgres    false    182            )           2604    19043    id    DEFAULT     Z   ALTER TABLE ONLY booking ALTER COLUMN id SET DEFAULT nextval('booking_id_seq'::regclass);
 9   ALTER TABLE public.booking ALTER COLUMN id DROP DEFAULT;
       public       postgres    false    214    208    214            *           2604    19044 	   client_id    DEFAULT     h   ALTER TABLE ONLY booking ALTER COLUMN client_id SET DEFAULT nextval('booking_client_id_seq'::regclass);
 @   ALTER TABLE public.booking ALTER COLUMN client_id DROP DEFAULT;
       public       postgres    false    214    209    214            +           2604    19045    car_id    DEFAULT     b   ALTER TABLE ONLY booking ALTER COLUMN car_id SET DEFAULT nextval('booking_car_id_seq'::regclass);
 =   ALTER TABLE public.booking ALTER COLUMN car_id DROP DEFAULT;
       public       postgres    false    210    214    214            ,           2604    19046    location_from_id    DEFAULT     v   ALTER TABLE ONLY booking ALTER COLUMN location_from_id SET DEFAULT nextval('booking_location_from_id_seq'::regclass);
 G   ALTER TABLE public.booking ALTER COLUMN location_from_id DROP DEFAULT;
       public       postgres    false    211    214    214            -           2604    19047    location_to_id    DEFAULT     r   ALTER TABLE ONLY booking ALTER COLUMN location_to_id SET DEFAULT nextval('booking_location_to_id_seq'::regclass);
 E   ALTER TABLE public.booking ALTER COLUMN location_to_id DROP DEFAULT;
       public       postgres    false    214    212    214            .           2604    19048 	   reason_id    DEFAULT     h   ALTER TABLE ONLY booking ALTER COLUMN reason_id SET DEFAULT nextval('booking_reason_id_seq'::regclass);
 @   ALTER TABLE public.booking ALTER COLUMN reason_id DROP DEFAULT;
       public       postgres    false    214    213    214                       2604    18939    id    DEFAULT     R   ALTER TABLE ONLY car ALTER COLUMN id SET DEFAULT nextval('car_id_seq'::regclass);
 5   ALTER TABLE public.car ALTER COLUMN id DROP DEFAULT;
       public       postgres    false    186    189    189                       2604    18940    type_id    DEFAULT     \   ALTER TABLE ONLY car ALTER COLUMN type_id SET DEFAULT nextval('car_type_id_seq'::regclass);
 :   ALTER TABLE public.car ALTER COLUMN type_id DROP DEFAULT;
       public       postgres    false    189    187    189                       2604    18941    location_id    DEFAULT     d   ALTER TABLE ONLY car ALTER COLUMN location_id SET DEFAULT nextval('car_location_id_seq'::regclass);
 >   ALTER TABLE public.car ALTER COLUMN location_id DROP DEFAULT;
       public       postgres    false    189    188    189                        2604    18980    id    DEFAULT     Z   ALTER TABLE ONLY invoice ALTER COLUMN id SET DEFAULT nextval('invoice_id_seq'::regclass);
 9   ALTER TABLE public.invoice ALTER COLUMN id DROP DEFAULT;
       public       postgres    false    197    199    199            !           2604    18981    order_id    DEFAULT     f   ALTER TABLE ONLY invoice ALTER COLUMN order_id SET DEFAULT nextval('invoice_order_id_seq'::regclass);
 ?   ALTER TABLE public.invoice ALTER COLUMN order_id DROP DEFAULT;
       public       postgres    false    198    199    199            #           2604    19000    id    DEFAULT     \   ALTER TABLE ONLY location ALTER COLUMN id SET DEFAULT nextval('location_id_seq'::regclass);
 :   ALTER TABLE public.location ALTER COLUMN id DROP DEFAULT;
       public       postgres    false    202    203    203                       2604    18962    id    DEFAULT     X   ALTER TABLE ONLY "order" ALTER COLUMN id SET DEFAULT nextval('order_id_seq'::regclass);
 9   ALTER TABLE public."order" ALTER COLUMN id DROP DEFAULT;
       public       postgres    false    196    190    196                       2604    18963 	   client_id    DEFAULT     f   ALTER TABLE ONLY "order" ALTER COLUMN client_id SET DEFAULT nextval('order_client_id_seq'::regclass);
 @   ALTER TABLE public."order" ALTER COLUMN client_id DROP DEFAULT;
       public       postgres    false    196    191    196                       2604    18964    car_id    DEFAULT     `   ALTER TABLE ONLY "order" ALTER COLUMN car_id SET DEFAULT nextval('order_car_id_seq'::regclass);
 =   ALTER TABLE public."order" ALTER COLUMN car_id DROP DEFAULT;
       public       postgres    false    196    192    196                       2604    18965    location_from    DEFAULT     n   ALTER TABLE ONLY "order" ALTER COLUMN location_from SET DEFAULT nextval('order_location_from_seq'::regclass);
 D   ALTER TABLE public."order" ALTER COLUMN location_from DROP DEFAULT;
       public       postgres    false    193    196    196                       2604    18966    location_to    DEFAULT     j   ALTER TABLE ONLY "order" ALTER COLUMN location_to SET DEFAULT nextval('order_location_to_seq'::regclass);
 B   ALTER TABLE public."order" ALTER COLUMN location_to DROP DEFAULT;
       public       postgres    false    196    194    196                       2604    18967 	   reason_id    DEFAULT     f   ALTER TABLE ONLY "order" ALTER COLUMN reason_id SET DEFAULT nextval('order_reason_id_seq'::regclass);
 @   ALTER TABLE public."order" ALTER COLUMN reason_id DROP DEFAULT;
       public       postgres    false    195    196    196            "           2604    18992    id    DEFAULT     X   ALTER TABLE ONLY reason ALTER COLUMN id SET DEFAULT nextval('reason_id_seq'::regclass);
 8   ALTER TABLE public.reason ALTER COLUMN id DROP DEFAULT;
       public       postgres    false    201    200    201            %           2604    19022    id    DEFAULT     Z   ALTER TABLE ONLY setting ALTER COLUMN id SET DEFAULT nextval('setting_id_seq'::regclass);
 9   ALTER TABLE public.setting ALTER COLUMN id DROP DEFAULT;
       public       postgres    false    207    206    207            $           2604    19011    id    DEFAULT     T   ALTER TABLE ONLY type ALTER COLUMN id SET DEFAULT nextval('type_id_seq'::regclass);
 6   ALTER TABLE public.type ALTER COLUMN id DROP DEFAULT;
       public       postgres    false    204    205    205                       2604    18927    id    DEFAULT     l   ALTER TABLE ONLY user_credentials ALTER COLUMN id SET DEFAULT nextval('user_credentials_id_seq'::regclass);
 B   ALTER TABLE public.user_credentials ALTER COLUMN id DROP DEFAULT;
       public       postgres    false    184    185    185                       2604    18916    id    DEFAULT     d   ALTER TABLE ONLY user_profile ALTER COLUMN id SET DEFAULT nextval('user_profile_id_seq'::regclass);
 >   ALTER TABLE public.user_profile ALTER COLUMN id DROP DEFAULT;
       public       postgres    false    183    182    183            �          0    19040    booking 
   TABLE DATA                     public       postgres    false    214   ڑ       �           0    0    booking_car_id_seq    SEQUENCE SET     :   SELECT pg_catalog.setval('booking_car_id_seq', 1, false);
            public       postgres    false    210             	           0    0    booking_client_id_seq    SEQUENCE SET     =   SELECT pg_catalog.setval('booking_client_id_seq', 1, false);
            public       postgres    false    209            	           0    0    booking_id_seq    SEQUENCE SET     5   SELECT pg_catalog.setval('booking_id_seq', 5, true);
            public       postgres    false    208            	           0    0    booking_location_from_id_seq    SEQUENCE SET     D   SELECT pg_catalog.setval('booking_location_from_id_seq', 1, false);
            public       postgres    false    211            	           0    0    booking_location_to_id_seq    SEQUENCE SET     B   SELECT pg_catalog.setval('booking_location_to_id_seq', 1, false);
            public       postgres    false    212            	           0    0    booking_reason_id_seq    SEQUENCE SET     =   SELECT pg_catalog.setval('booking_reason_id_seq', 1, false);
            public       postgres    false    213            �          0    18936    car 
   TABLE DATA                     public       postgres    false    189   ��       	           0    0 
   car_id_seq    SEQUENCE SET     1   SELECT pg_catalog.setval('car_id_seq', 6, true);
            public       postgres    false    186            	           0    0    car_location_id_seq    SEQUENCE SET     ;   SELECT pg_catalog.setval('car_location_id_seq', 1, false);
            public       postgres    false    188            	           0    0    car_type_id_seq    SEQUENCE SET     7   SELECT pg_catalog.setval('car_type_id_seq', 1, false);
            public       postgres    false    187            �          0    18977    invoice 
   TABLE DATA                     public       postgres    false    199   ��       	           0    0    invoice_id_seq    SEQUENCE SET     6   SELECT pg_catalog.setval('invoice_id_seq', 1, false);
            public       postgres    false    197            		           0    0    invoice_order_id_seq    SEQUENCE SET     <   SELECT pg_catalog.setval('invoice_order_id_seq', 1, false);
            public       postgres    false    198            �          0    18997    location 
   TABLE DATA                     public       postgres    false    203   Д       
	           0    0    location_id_seq    SEQUENCE SET     6   SELECT pg_catalog.setval('location_id_seq', 3, true);
            public       postgres    false    202            �          0    18959    order 
   TABLE DATA                     public       postgres    false    196   g�       	           0    0    order_car_id_seq    SEQUENCE SET     8   SELECT pg_catalog.setval('order_car_id_seq', 1, false);
            public       postgres    false    192            	           0    0    order_client_id_seq    SEQUENCE SET     ;   SELECT pg_catalog.setval('order_client_id_seq', 1, false);
            public       postgres    false    191            	           0    0    order_id_seq    SEQUENCE SET     4   SELECT pg_catalog.setval('order_id_seq', 1, false);
            public       postgres    false    190            	           0    0    order_location_from_seq    SEQUENCE SET     ?   SELECT pg_catalog.setval('order_location_from_seq', 1, false);
            public       postgres    false    193            	           0    0    order_location_to_seq    SEQUENCE SET     =   SELECT pg_catalog.setval('order_location_to_seq', 1, false);
            public       postgres    false    194            	           0    0    order_reason_id_seq    SEQUENCE SET     ;   SELECT pg_catalog.setval('order_reason_id_seq', 1, false);
            public       postgres    false    195            �          0    18989    reason 
   TABLE DATA                     public       postgres    false    201   ��       	           0    0    reason_id_seq    SEQUENCE SET     4   SELECT pg_catalog.setval('reason_id_seq', 3, true);
            public       postgres    false    200            �          0    19019    setting 
   TABLE DATA                     public       postgres    false    207   �       	           0    0    setting_id_seq    SEQUENCE SET     5   SELECT pg_catalog.setval('setting_id_seq', 1, true);
            public       postgres    false    206            �          0    19008    type 
   TABLE DATA                     public       postgres    false    205   |�       	           0    0    type_id_seq    SEQUENCE SET     2   SELECT pg_catalog.setval('type_id_seq', 2, true);
            public       postgres    false    204            �          0    18924    user_credentials 
   TABLE DATA                     public       postgres    false    185   -�       	           0    0    user_credentials_id_seq    SEQUENCE SET     >   SELECT pg_catalog.setval('user_credentials_id_seq', 5, true);
            public       postgres    false    184            �          0    18913    user_profile 
   TABLE DATA                     public       postgres    false    183   ٗ       	           0    0    user_profile_id_seq    SEQUENCE SET     ;   SELECT pg_catalog.setval('user_profile_id_seq', 1, false);
            public       postgres    false    182            B           2606    19053 
   booking_pk 
   CONSTRAINT     I   ALTER TABLE ONLY booking
    ADD CONSTRAINT booking_pk PRIMARY KEY (id);
 <   ALTER TABLE ONLY public.booking DROP CONSTRAINT booking_pk;
       public         postgres    false    214    214            4           2606    18946    car_pk 
   CONSTRAINT     A   ALTER TABLE ONLY car
    ADD CONSTRAINT car_pk PRIMARY KEY (id);
 4   ALTER TABLE ONLY public.car DROP CONSTRAINT car_pk;
       public         postgres    false    189    189            8           2606    18986 
   invoice_pk 
   CONSTRAINT     I   ALTER TABLE ONLY invoice
    ADD CONSTRAINT invoice_pk PRIMARY KEY (id);
 <   ALTER TABLE ONLY public.invoice DROP CONSTRAINT invoice_pk;
       public         postgres    false    199    199            <           2606    19005    location_pk 
   CONSTRAINT     K   ALTER TABLE ONLY location
    ADD CONSTRAINT location_pk PRIMARY KEY (id);
 >   ALTER TABLE ONLY public.location DROP CONSTRAINT location_pk;
       public         postgres    false    203    203            6           2606    18972    order_pk 
   CONSTRAINT     G   ALTER TABLE ONLY "order"
    ADD CONSTRAINT order_pk PRIMARY KEY (id);
 :   ALTER TABLE ONLY public."order" DROP CONSTRAINT order_pk;
       public         postgres    false    196    196            :           2606    18994 	   reason_pk 
   CONSTRAINT     G   ALTER TABLE ONLY reason
    ADD CONSTRAINT reason_pk PRIMARY KEY (id);
 :   ALTER TABLE ONLY public.reason DROP CONSTRAINT reason_pk;
       public         postgres    false    201    201            @           2606    19027 
   setting_pk 
   CONSTRAINT     I   ALTER TABLE ONLY setting
    ADD CONSTRAINT setting_pk PRIMARY KEY (id);
 <   ALTER TABLE ONLY public.setting DROP CONSTRAINT setting_pk;
       public         postgres    false    207    207            >           2606    19016    type_pk 
   CONSTRAINT     C   ALTER TABLE ONLY type
    ADD CONSTRAINT type_pk PRIMARY KEY (id);
 6   ALTER TABLE ONLY public.type DROP CONSTRAINT type_pk;
       public         postgres    false    205    205            2           2606    18929    user_credentials_pk 
   CONSTRAINT     [   ALTER TABLE ONLY user_credentials
    ADD CONSTRAINT user_credentials_pk PRIMARY KEY (id);
 N   ALTER TABLE ONLY public.user_credentials DROP CONSTRAINT user_credentials_pk;
       public         postgres    false    185    185            0           2606    18921    user_profile_pk 
   CONSTRAINT     S   ALTER TABLE ONLY user_profile
    ADD CONSTRAINT user_profile_pk PRIMARY KEY (id);
 F   ALTER TABLE ONLY public.user_profile DROP CONSTRAINT user_profile_pk;
       public         postgres    false    183    183            L           2606    19099    booking_fk0    FK CONSTRAINT     m   ALTER TABLE ONLY booking
    ADD CONSTRAINT booking_fk0 FOREIGN KEY (client_id) REFERENCES user_profile(id);
 =   ALTER TABLE ONLY public.booking DROP CONSTRAINT booking_fk0;
       public       postgres    false    2096    214    183            M           2606    19104    booking_fk1    FK CONSTRAINT     a   ALTER TABLE ONLY booking
    ADD CONSTRAINT booking_fk1 FOREIGN KEY (car_id) REFERENCES car(id);
 =   ALTER TABLE ONLY public.booking DROP CONSTRAINT booking_fk1;
       public       postgres    false    214    189    2100            N           2606    19109    booking_fk2    FK CONSTRAINT     p   ALTER TABLE ONLY booking
    ADD CONSTRAINT booking_fk2 FOREIGN KEY (location_from_id) REFERENCES location(id);
 =   ALTER TABLE ONLY public.booking DROP CONSTRAINT booking_fk2;
       public       postgres    false    214    2108    203            O           2606    19114    booking_fk3    FK CONSTRAINT     n   ALTER TABLE ONLY booking
    ADD CONSTRAINT booking_fk3 FOREIGN KEY (location_to_id) REFERENCES location(id);
 =   ALTER TABLE ONLY public.booking DROP CONSTRAINT booking_fk3;
       public       postgres    false    2108    214    203            P           2606    19119    booking_fk4    FK CONSTRAINT     g   ALTER TABLE ONLY booking
    ADD CONSTRAINT booking_fk4 FOREIGN KEY (reason_id) REFERENCES reason(id);
 =   ALTER TABLE ONLY public.booking DROP CONSTRAINT booking_fk4;
       public       postgres    false    214    2106    201            D           2606    19059    car_fk0    FK CONSTRAINT     [   ALTER TABLE ONLY car
    ADD CONSTRAINT car_fk0 FOREIGN KEY (type_id) REFERENCES type(id);
 5   ALTER TABLE ONLY public.car DROP CONSTRAINT car_fk0;
       public       postgres    false    205    189    2110            E           2606    19064    car_fk1    FK CONSTRAINT     c   ALTER TABLE ONLY car
    ADD CONSTRAINT car_fk1 FOREIGN KEY (location_id) REFERENCES location(id);
 5   ALTER TABLE ONLY public.car DROP CONSTRAINT car_fk1;
       public       postgres    false    2108    189    203            K           2606    19094    invoice_fk0    FK CONSTRAINT     g   ALTER TABLE ONLY invoice
    ADD CONSTRAINT invoice_fk0 FOREIGN KEY (order_id) REFERENCES "order"(id);
 =   ALTER TABLE ONLY public.invoice DROP CONSTRAINT invoice_fk0;
       public       postgres    false    196    199    2102            F           2606    19069 	   order_fk0    FK CONSTRAINT     k   ALTER TABLE ONLY "order"
    ADD CONSTRAINT order_fk0 FOREIGN KEY (client_id) REFERENCES user_profile(id);
 ;   ALTER TABLE ONLY public."order" DROP CONSTRAINT order_fk0;
       public       postgres    false    183    2096    196            G           2606    19074 	   order_fk1    FK CONSTRAINT     _   ALTER TABLE ONLY "order"
    ADD CONSTRAINT order_fk1 FOREIGN KEY (car_id) REFERENCES car(id);
 ;   ALTER TABLE ONLY public."order" DROP CONSTRAINT order_fk1;
       public       postgres    false    2100    189    196            H           2606    19079 	   order_fk2    FK CONSTRAINT     k   ALTER TABLE ONLY "order"
    ADD CONSTRAINT order_fk2 FOREIGN KEY (location_from) REFERENCES location(id);
 ;   ALTER TABLE ONLY public."order" DROP CONSTRAINT order_fk2;
       public       postgres    false    2108    196    203            I           2606    19084 	   order_fk3    FK CONSTRAINT     i   ALTER TABLE ONLY "order"
    ADD CONSTRAINT order_fk3 FOREIGN KEY (location_to) REFERENCES location(id);
 ;   ALTER TABLE ONLY public."order" DROP CONSTRAINT order_fk3;
       public       postgres    false    203    2108    196            J           2606    19089 	   order_fk4    FK CONSTRAINT     e   ALTER TABLE ONLY "order"
    ADD CONSTRAINT order_fk4 FOREIGN KEY (reason_id) REFERENCES reason(id);
 ;   ALTER TABLE ONLY public."order" DROP CONSTRAINT order_fk4;
       public       postgres    false    196    201    2106            C           2606    19054    user_profile_fk0    FK CONSTRAINT     t   ALTER TABLE ONLY user_profile
    ADD CONSTRAINT user_profile_fk0 FOREIGN KEY (id) REFERENCES user_credentials(id);
 G   ALTER TABLE ONLY public.user_profile DROP CONSTRAINT user_profile_fk0;
       public       postgres    false    2098    183    185            �   
   x���          �   �  x�͔Ms�0���{#��;|jOOЦ��H�0k{1jd�#�P�뻒�i;C�,����y��ݯn�k�ݯ�C�.E�"pǚ6~(u�Nh���]c#h�7�UI3TnTSed"8'�F���
��u;�tO����
.{tdr�$��z�DЏ����?��_N����G���?�/ g0�b�,h#J�,8��Ȱ 䆂�[p;��0�AM�h��@��@ DC�:R�is��*�*��w���9���MEU@�9&ܡj���$��j礠���$�ѩ���.;W�.fg�G;n
�L�3�<�,g��1j�����$�Wm
�Jd�X�S���Z>#dF�IS�y5�	�:�'��(����S�s�ғ�[k�%���N��zSî5Z˻�|�Ulř8/7�?�|-��'�o��������-2�\����E�y�J���7��~
<�O�W2:�� �$p+d�+�w��7��
[��,�m$d�,d��b���(w<BaĞ7	X��o� J���x��� k�:�!3ؒᶊ+��s���u��-C>�����n��j�/ǝ`����6M��d�Ci꽼k�%Q"'.��Ʉ��n��i�bۗ�L'~��i2h���7�-��'��:��3��q��d���ߨg�7%�S��i��~�I���Xk����7��]�      �   
   x���          �   �   x��ν
�0��=Wq6B�|I:t� % 
�v����O���]λ=�tի�m�k��c@�G�ඉcu{���<�w�!/9��7�!��g?L%6�C�A$��PJ�,��^�噖I�Z���tV)������T�~��I�      �   
   x���          �   X   x���v
Q���W(JM,��S��L�Q�K�M�Ts�	uV�0�QP��*��'e�d��kZsye��#�L0F�`�n ��;�      �   �   x�5�1�0�=��F����:8�B�]5���%�+�o�ox﫛�ztP7�6V�2A��ꅆ�y��e�a���_�"����6��ce�l>)���O�;��}	���Y�PX�3Bn{Bȱ����.v      �   �   x�����0Ew�� e�>�N��
��`��p90�����JWG��S�]u�n��O���Q�U��I�B@���g�+�m�s�rfe���r� ��Pzd�p�p;6ת���v��	��YG����͛��m~H�?���陑�B�(�}��Dײ�BI��]L      �   �   x���v
Q���W(-N-�O.JMI�+�L�)V��L�QH�M���Q(H,..�/
��j*�9���+h�(��$�W8��T�%���� �2д���#����y��E%��%�Q�c���9�@-��m1��-&p[�hh�)�cܶpq va��      �   �  x�͓KO�0����D�:~�vfŌ*�)#�C�&r�xH��y�ί;�@�º�e�s#�|:N�����˛+з��[ku���.�m��ܨ ��,���r~�մ*o��J� ������� Ȳ��m��L��Q�ΙV��i�Wo��ܝ_�.2p`�ii+��^v�Q�WF�Ƴ�('��{�2��� �~l��߾�u�Vj�E%As�|�3��
�bJ|��1�
a#>=�6I��rӝ7�ӝ�3dc!	B!�ѧ��hefP]�$w����H�q1c<v���1���P��C`BYH�[��k�h��}!�lNƠ��̸����[ǈ����jYꍶ~�_}��dPBx��[l��ȍ,� �H@�����]!c_�i��<�+J(q);BB�M�X����~u�M0Q4Ҹ�@���Gf�н�k�uV�>��L(�R=?�4��L1J=���b��     