PGDMP         .                t            rentacar    9.5.2    9.5.2 �    9	           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                       false            :	           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                       false                        2615    20206    public    SCHEMA        CREATE SCHEMA public;
    DROP SCHEMA public;
             postgres    false            �            1259    20209    booking    TABLE     �  CREATE TABLE booking (
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
       public         postgres    false    7            �            1259    20215    booking_car_id_seq    SEQUENCE     t   CREATE SEQUENCE booking_car_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 )   DROP SEQUENCE public.booking_car_id_seq;
       public       postgres    false    182    7            ;	           0    0    booking_car_id_seq    SEQUENCE OWNED BY     ;   ALTER SEQUENCE booking_car_id_seq OWNED BY booking.car_id;
            public       postgres    false    183            �            1259    20217    booking_client_id_seq    SEQUENCE     w   CREATE SEQUENCE booking_client_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 ,   DROP SEQUENCE public.booking_client_id_seq;
       public       postgres    false    7    182            <	           0    0    booking_client_id_seq    SEQUENCE OWNED BY     A   ALTER SEQUENCE booking_client_id_seq OWNED BY booking.client_id;
            public       postgres    false    184            �            1259    20219    booking_id_seq    SEQUENCE     p   CREATE SEQUENCE booking_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 %   DROP SEQUENCE public.booking_id_seq;
       public       postgres    false    7    182            =	           0    0    booking_id_seq    SEQUENCE OWNED BY     3   ALTER SEQUENCE booking_id_seq OWNED BY booking.id;
            public       postgres    false    185            �            1259    20221    booking_location_from_id_seq    SEQUENCE     ~   CREATE SEQUENCE booking_location_from_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 3   DROP SEQUENCE public.booking_location_from_id_seq;
       public       postgres    false    7    182            >	           0    0    booking_location_from_id_seq    SEQUENCE OWNED BY     O   ALTER SEQUENCE booking_location_from_id_seq OWNED BY booking.location_from_id;
            public       postgres    false    186            �            1259    20223    booking_location_to_id_seq    SEQUENCE     |   CREATE SEQUENCE booking_location_to_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 1   DROP SEQUENCE public.booking_location_to_id_seq;
       public       postgres    false    7    182            ?	           0    0    booking_location_to_id_seq    SEQUENCE OWNED BY     K   ALTER SEQUENCE booking_location_to_id_seq OWNED BY booking.location_to_id;
            public       postgres    false    187            �            1259    20225    booking_reason_id_seq    SEQUENCE     w   CREATE SEQUENCE booking_reason_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 ,   DROP SEQUENCE public.booking_reason_id_seq;
       public       postgres    false    182    7            @	           0    0    booking_reason_id_seq    SEQUENCE OWNED BY     A   ALTER SEQUENCE booking_reason_id_seq OWNED BY booking.reason_id;
            public       postgres    false    188            �            1259    20227    car    TABLE     l  CREATE TABLE car (
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
       public         postgres    false    7            �            1259    20233 
   car_id_seq    SEQUENCE     l   CREATE SEQUENCE car_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 !   DROP SEQUENCE public.car_id_seq;
       public       postgres    false    189    7            A	           0    0 
   car_id_seq    SEQUENCE OWNED BY     +   ALTER SEQUENCE car_id_seq OWNED BY car.id;
            public       postgres    false    190            �            1259    20235    car_location_id_seq    SEQUENCE     u   CREATE SEQUENCE car_location_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 *   DROP SEQUENCE public.car_location_id_seq;
       public       postgres    false    7    189            B	           0    0    car_location_id_seq    SEQUENCE OWNED BY     =   ALTER SEQUENCE car_location_id_seq OWNED BY car.location_id;
            public       postgres    false    191            �            1259    20237    car_type_id_seq    SEQUENCE     q   CREATE SEQUENCE car_type_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 &   DROP SEQUENCE public.car_type_id_seq;
       public       postgres    false    7    189            C	           0    0    car_type_id_seq    SEQUENCE OWNED BY     5   ALTER SEQUENCE car_type_id_seq OWNED BY car.type_id;
            public       postgres    false    192            �            1259    20239    invoice    TABLE     �   CREATE TABLE invoice (
    id integer NOT NULL,
    created timestamp without time zone NOT NULL,
    summ numeric NOT NULL,
    note character varying,
    order_id integer NOT NULL
);
    DROP TABLE public.invoice;
       public         postgres    false    7            �            1259    20245    invoice_id_seq    SEQUENCE     p   CREATE SEQUENCE invoice_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 %   DROP SEQUENCE public.invoice_id_seq;
       public       postgres    false    7    193            D	           0    0    invoice_id_seq    SEQUENCE OWNED BY     3   ALTER SEQUENCE invoice_id_seq OWNED BY invoice.id;
            public       postgres    false    194            �            1259    20247    invoice_order_id_seq    SEQUENCE     v   CREATE SEQUENCE invoice_order_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 +   DROP SEQUENCE public.invoice_order_id_seq;
       public       postgres    false    7    193            E	           0    0    invoice_order_id_seq    SEQUENCE OWNED BY     ?   ALTER SEQUENCE invoice_order_id_seq OWNED BY invoice.order_id;
            public       postgres    false    195            �            1259    20249    location    TABLE     �   CREATE TABLE location (
    id integer NOT NULL,
    name character varying(100) NOT NULL,
    lat numeric NOT NULL,
    lng numeric NOT NULL
);
    DROP TABLE public.location;
       public         postgres    false    7            �            1259    20255    location_id_seq    SEQUENCE     q   CREATE SEQUENCE location_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 &   DROP SEQUENCE public.location_id_seq;
       public       postgres    false    7    196            F	           0    0    location_id_seq    SEQUENCE OWNED BY     5   ALTER SEQUENCE location_id_seq OWNED BY location.id;
            public       postgres    false    197            �            1259    20257    order    TABLE     �  CREATE TABLE "order" (
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
       public         postgres    false    7            �            1259    20263    order_car_id_seq    SEQUENCE     r   CREATE SEQUENCE order_car_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 '   DROP SEQUENCE public.order_car_id_seq;
       public       postgres    false    198    7            G	           0    0    order_car_id_seq    SEQUENCE OWNED BY     9   ALTER SEQUENCE order_car_id_seq OWNED BY "order".car_id;
            public       postgres    false    199            �            1259    20265    order_client_id_seq    SEQUENCE     u   CREATE SEQUENCE order_client_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 *   DROP SEQUENCE public.order_client_id_seq;
       public       postgres    false    198    7            H	           0    0    order_client_id_seq    SEQUENCE OWNED BY     ?   ALTER SEQUENCE order_client_id_seq OWNED BY "order".client_id;
            public       postgres    false    200            �            1259    20267    order_id_seq    SEQUENCE     n   CREATE SEQUENCE order_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 #   DROP SEQUENCE public.order_id_seq;
       public       postgres    false    7    198            I	           0    0    order_id_seq    SEQUENCE OWNED BY     1   ALTER SEQUENCE order_id_seq OWNED BY "order".id;
            public       postgres    false    201            �            1259    20269    order_location_from_seq    SEQUENCE     y   CREATE SEQUENCE order_location_from_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 .   DROP SEQUENCE public.order_location_from_seq;
       public       postgres    false    7    198            J	           0    0    order_location_from_seq    SEQUENCE OWNED BY     G   ALTER SEQUENCE order_location_from_seq OWNED BY "order".location_from;
            public       postgres    false    202            �            1259    20271    order_location_to_seq    SEQUENCE     w   CREATE SEQUENCE order_location_to_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 ,   DROP SEQUENCE public.order_location_to_seq;
       public       postgres    false    198    7            K	           0    0    order_location_to_seq    SEQUENCE OWNED BY     C   ALTER SEQUENCE order_location_to_seq OWNED BY "order".location_to;
            public       postgres    false    203            �            1259    20273    order_reason_id_seq    SEQUENCE     u   CREATE SEQUENCE order_reason_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 *   DROP SEQUENCE public.order_reason_id_seq;
       public       postgres    false    7    198            L	           0    0    order_reason_id_seq    SEQUENCE OWNED BY     ?   ALTER SEQUENCE order_reason_id_seq OWNED BY "order".reason_id;
            public       postgres    false    204            �            1259    20275    reason    TABLE     [   CREATE TABLE reason (
    id integer NOT NULL,
    name character varying(100) NOT NULL
);
    DROP TABLE public.reason;
       public         postgres    false    7            �            1259    20278    reason_id_seq    SEQUENCE     o   CREATE SEQUENCE reason_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 $   DROP SEQUENCE public.reason_id_seq;
       public       postgres    false    205    7            M	           0    0    reason_id_seq    SEQUENCE OWNED BY     1   ALTER SEQUENCE reason_id_seq OWNED BY reason.id;
            public       postgres    false    206            �            1259    20280    setting    TABLE       CREATE TABLE setting (
    id integer NOT NULL,
    min_booking_length integer DEFAULT 0 NOT NULL,
    car_while_pending integer DEFAULT 0 NOT NULL,
    deposit_payment integer DEFAULT 10 NOT NULL,
    currency integer NOT NULL,
    car_between_pending integer NOT NULL
);
    DROP TABLE public.setting;
       public         postgres    false    7            �            1259    20286    setting_id_seq    SEQUENCE     p   CREATE SEQUENCE setting_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 %   DROP SEQUENCE public.setting_id_seq;
       public       postgres    false    7    207            N	           0    0    setting_id_seq    SEQUENCE OWNED BY     3   ALTER SEQUENCE setting_id_seq OWNED BY setting.id;
            public       postgres    false    208            �            1259    20288    type    TABLE     
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
       public         postgres    false    7            �            1259    20294    type_id_seq    SEQUENCE     m   CREATE SEQUENCE type_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 "   DROP SEQUENCE public.type_id_seq;
       public       postgres    false    209    7            O	           0    0    type_id_seq    SEQUENCE OWNED BY     -   ALTER SEQUENCE type_id_seq OWNED BY type.id;
            public       postgres    false    210            �            1259    20296    user_credentials    TABLE     �   CREATE TABLE user_credentials (
    id integer NOT NULL,
    email character varying(100) NOT NULL,
    password character varying(100) NOT NULL,
    role integer NOT NULL
);
 $   DROP TABLE public.user_credentials;
       public         postgres    false    7            �            1259    20299    user_credentials_id_seq    SEQUENCE     y   CREATE SEQUENCE user_credentials_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 .   DROP SEQUENCE public.user_credentials_id_seq;
       public       postgres    false    7    211            P	           0    0    user_credentials_id_seq    SEQUENCE OWNED BY     E   ALTER SEQUENCE user_credentials_id_seq OWNED BY user_credentials.id;
            public       postgres    false    212            �            1259    20301    user_profile    TABLE     �  CREATE TABLE user_profile (
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
       public         postgres    false    7            �            1259    20307    user_profile_id_seq    SEQUENCE     u   CREATE SEQUENCE user_profile_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 *   DROP SEQUENCE public.user_profile_id_seq;
       public       postgres    false    213    7            Q	           0    0    user_profile_id_seq    SEQUENCE OWNED BY     =   ALTER SEQUENCE user_profile_id_seq OWNED BY user_profile.id;
            public       postgres    false    214            h           2604    20309    id    DEFAULT     Z   ALTER TABLE ONLY booking ALTER COLUMN id SET DEFAULT nextval('booking_id_seq'::regclass);
 9   ALTER TABLE public.booking ALTER COLUMN id DROP DEFAULT;
       public       postgres    false    185    182            i           2604    20310 	   client_id    DEFAULT     h   ALTER TABLE ONLY booking ALTER COLUMN client_id SET DEFAULT nextval('booking_client_id_seq'::regclass);
 @   ALTER TABLE public.booking ALTER COLUMN client_id DROP DEFAULT;
       public       postgres    false    184    182            j           2604    20311    car_id    DEFAULT     b   ALTER TABLE ONLY booking ALTER COLUMN car_id SET DEFAULT nextval('booking_car_id_seq'::regclass);
 =   ALTER TABLE public.booking ALTER COLUMN car_id DROP DEFAULT;
       public       postgres    false    183    182            k           2604    20312    location_from_id    DEFAULT     v   ALTER TABLE ONLY booking ALTER COLUMN location_from_id SET DEFAULT nextval('booking_location_from_id_seq'::regclass);
 G   ALTER TABLE public.booking ALTER COLUMN location_from_id DROP DEFAULT;
       public       postgres    false    186    182            l           2604    20313    location_to_id    DEFAULT     r   ALTER TABLE ONLY booking ALTER COLUMN location_to_id SET DEFAULT nextval('booking_location_to_id_seq'::regclass);
 E   ALTER TABLE public.booking ALTER COLUMN location_to_id DROP DEFAULT;
       public       postgres    false    187    182            m           2604    20314 	   reason_id    DEFAULT     h   ALTER TABLE ONLY booking ALTER COLUMN reason_id SET DEFAULT nextval('booking_reason_id_seq'::regclass);
 @   ALTER TABLE public.booking ALTER COLUMN reason_id DROP DEFAULT;
       public       postgres    false    188    182            n           2604    20315    id    DEFAULT     R   ALTER TABLE ONLY car ALTER COLUMN id SET DEFAULT nextval('car_id_seq'::regclass);
 5   ALTER TABLE public.car ALTER COLUMN id DROP DEFAULT;
       public       postgres    false    190    189            o           2604    20316    type_id    DEFAULT     \   ALTER TABLE ONLY car ALTER COLUMN type_id SET DEFAULT nextval('car_type_id_seq'::regclass);
 :   ALTER TABLE public.car ALTER COLUMN type_id DROP DEFAULT;
       public       postgres    false    192    189            p           2604    20317    location_id    DEFAULT     d   ALTER TABLE ONLY car ALTER COLUMN location_id SET DEFAULT nextval('car_location_id_seq'::regclass);
 >   ALTER TABLE public.car ALTER COLUMN location_id DROP DEFAULT;
       public       postgres    false    191    189            q           2604    20318    id    DEFAULT     Z   ALTER TABLE ONLY invoice ALTER COLUMN id SET DEFAULT nextval('invoice_id_seq'::regclass);
 9   ALTER TABLE public.invoice ALTER COLUMN id DROP DEFAULT;
       public       postgres    false    194    193            r           2604    20319    order_id    DEFAULT     f   ALTER TABLE ONLY invoice ALTER COLUMN order_id SET DEFAULT nextval('invoice_order_id_seq'::regclass);
 ?   ALTER TABLE public.invoice ALTER COLUMN order_id DROP DEFAULT;
       public       postgres    false    195    193            s           2604    20320    id    DEFAULT     \   ALTER TABLE ONLY location ALTER COLUMN id SET DEFAULT nextval('location_id_seq'::regclass);
 :   ALTER TABLE public.location ALTER COLUMN id DROP DEFAULT;
       public       postgres    false    197    196            t           2604    20321    id    DEFAULT     X   ALTER TABLE ONLY "order" ALTER COLUMN id SET DEFAULT nextval('order_id_seq'::regclass);
 9   ALTER TABLE public."order" ALTER COLUMN id DROP DEFAULT;
       public       postgres    false    201    198            u           2604    20322 	   client_id    DEFAULT     f   ALTER TABLE ONLY "order" ALTER COLUMN client_id SET DEFAULT nextval('order_client_id_seq'::regclass);
 @   ALTER TABLE public."order" ALTER COLUMN client_id DROP DEFAULT;
       public       postgres    false    200    198            v           2604    20323    car_id    DEFAULT     `   ALTER TABLE ONLY "order" ALTER COLUMN car_id SET DEFAULT nextval('order_car_id_seq'::regclass);
 =   ALTER TABLE public."order" ALTER COLUMN car_id DROP DEFAULT;
       public       postgres    false    199    198            w           2604    20324    location_from    DEFAULT     n   ALTER TABLE ONLY "order" ALTER COLUMN location_from SET DEFAULT nextval('order_location_from_seq'::regclass);
 D   ALTER TABLE public."order" ALTER COLUMN location_from DROP DEFAULT;
       public       postgres    false    202    198            x           2604    20325    location_to    DEFAULT     j   ALTER TABLE ONLY "order" ALTER COLUMN location_to SET DEFAULT nextval('order_location_to_seq'::regclass);
 B   ALTER TABLE public."order" ALTER COLUMN location_to DROP DEFAULT;
       public       postgres    false    203    198            y           2604    20326 	   reason_id    DEFAULT     f   ALTER TABLE ONLY "order" ALTER COLUMN reason_id SET DEFAULT nextval('order_reason_id_seq'::regclass);
 @   ALTER TABLE public."order" ALTER COLUMN reason_id DROP DEFAULT;
       public       postgres    false    204    198            z           2604    20327    id    DEFAULT     X   ALTER TABLE ONLY reason ALTER COLUMN id SET DEFAULT nextval('reason_id_seq'::regclass);
 8   ALTER TABLE public.reason ALTER COLUMN id DROP DEFAULT;
       public       postgres    false    206    205            ~           2604    20328    id    DEFAULT     Z   ALTER TABLE ONLY setting ALTER COLUMN id SET DEFAULT nextval('setting_id_seq'::regclass);
 9   ALTER TABLE public.setting ALTER COLUMN id DROP DEFAULT;
       public       postgres    false    208    207                       2604    20329    id    DEFAULT     T   ALTER TABLE ONLY type ALTER COLUMN id SET DEFAULT nextval('type_id_seq'::regclass);
 6   ALTER TABLE public.type ALTER COLUMN id DROP DEFAULT;
       public       postgres    false    210    209            �           2604    20330    id    DEFAULT     l   ALTER TABLE ONLY user_credentials ALTER COLUMN id SET DEFAULT nextval('user_credentials_id_seq'::regclass);
 B   ALTER TABLE public.user_credentials ALTER COLUMN id DROP DEFAULT;
       public       postgres    false    212    211            �           2604    20331    id    DEFAULT     d   ALTER TABLE ONLY user_profile ALTER COLUMN id SET DEFAULT nextval('user_profile_id_seq'::regclass);
 >   ALTER TABLE public.user_profile ALTER COLUMN id DROP DEFAULT;
       public       postgres    false    214    213            	          0    20209    booking 
   TABLE DATA                     public       postgres    false    182   !�       R	           0    0    booking_car_id_seq    SEQUENCE SET     :   SELECT pg_catalog.setval('booking_car_id_seq', 1, false);
            public       postgres    false    183            S	           0    0    booking_client_id_seq    SEQUENCE SET     =   SELECT pg_catalog.setval('booking_client_id_seq', 1, false);
            public       postgres    false    184            T	           0    0    booking_id_seq    SEQUENCE SET     5   SELECT pg_catalog.setval('booking_id_seq', 9, true);
            public       postgres    false    185            U	           0    0    booking_location_from_id_seq    SEQUENCE SET     D   SELECT pg_catalog.setval('booking_location_from_id_seq', 1, false);
            public       postgres    false    186            V	           0    0    booking_location_to_id_seq    SEQUENCE SET     B   SELECT pg_catalog.setval('booking_location_to_id_seq', 1, false);
            public       postgres    false    187            W	           0    0    booking_reason_id_seq    SEQUENCE SET     <   SELECT pg_catalog.setval('booking_reason_id_seq', 1, true);
            public       postgres    false    188            	          0    20227    car 
   TABLE DATA                     public       postgres    false    189   ��       X	           0    0 
   car_id_seq    SEQUENCE SET     1   SELECT pg_catalog.setval('car_id_seq', 5, true);
            public       postgres    false    190            Y	           0    0    car_location_id_seq    SEQUENCE SET     ;   SELECT pg_catalog.setval('car_location_id_seq', 1, false);
            public       postgres    false    191            Z	           0    0    car_type_id_seq    SEQUENCE SET     7   SELECT pg_catalog.setval('car_type_id_seq', 1, false);
            public       postgres    false    192            !	          0    20239    invoice 
   TABLE DATA                     public       postgres    false    193   !�       [	           0    0    invoice_id_seq    SEQUENCE SET     6   SELECT pg_catalog.setval('invoice_id_seq', 1, false);
            public       postgres    false    194            \	           0    0    invoice_order_id_seq    SEQUENCE SET     <   SELECT pg_catalog.setval('invoice_order_id_seq', 1, false);
            public       postgres    false    195            $	          0    20249    location 
   TABLE DATA                     public       postgres    false    196   ;�       ]	           0    0    location_id_seq    SEQUENCE SET     6   SELECT pg_catalog.setval('location_id_seq', 2, true);
            public       postgres    false    197            &	          0    20257    order 
   TABLE DATA                     public       postgres    false    198          ^	           0    0    order_car_id_seq    SEQUENCE SET     8   SELECT pg_catalog.setval('order_car_id_seq', 1, false);
            public       postgres    false    199            _	           0    0    order_client_id_seq    SEQUENCE SET     ;   SELECT pg_catalog.setval('order_client_id_seq', 1, false);
            public       postgres    false    200            `	           0    0    order_id_seq    SEQUENCE SET     4   SELECT pg_catalog.setval('order_id_seq', 1, false);
            public       postgres    false    201            a	           0    0    order_location_from_seq    SEQUENCE SET     ?   SELECT pg_catalog.setval('order_location_from_seq', 1, false);
            public       postgres    false    202            b	           0    0    order_location_to_seq    SEQUENCE SET     =   SELECT pg_catalog.setval('order_location_to_seq', 1, false);
            public       postgres    false    203            c	           0    0    order_reason_id_seq    SEQUENCE SET     ;   SELECT pg_catalog.setval('order_reason_id_seq', 1, false);
            public       postgres    false    204            -	          0    20275    reason 
   TABLE DATA                     public       postgres    false    205   ܔ       d	           0    0    reason_id_seq    SEQUENCE SET     4   SELECT pg_catalog.setval('reason_id_seq', 1, true);
            public       postgres    false    206            /	          0    20280    setting 
   TABLE DATA                     public       postgres    false    207   (�       e	           0    0    setting_id_seq    SEQUENCE SET     5   SELECT pg_catalog.setval('setting_id_seq', 1, true);
            public       postgres    false    208            1	          0    20288    type 
   TABLE DATA                     public       postgres    false    209   ��       f	           0    0    type_id_seq    SEQUENCE SET     2   SELECT pg_catalog.setval('type_id_seq', 2, true);
            public       postgres    false    210            3	          0    20296    user_credentials 
   TABLE DATA                     public       postgres    false    211   k�       g	           0    0    user_credentials_id_seq    SEQUENCE SET     >   SELECT pg_catalog.setval('user_credentials_id_seq', 2, true);
            public       postgres    false    212            5	          0    20301    user_profile 
   TABLE DATA                     public       postgres    false    213    �       h	           0    0    user_profile_id_seq    SEQUENCE SET     ;   SELECT pg_catalog.setval('user_profile_id_seq', 1, false);
            public       postgres    false    214            �           2606    20333 
   booking_pk 
   CONSTRAINT     I   ALTER TABLE ONLY booking
    ADD CONSTRAINT booking_pk PRIMARY KEY (id);
 <   ALTER TABLE ONLY public.booking DROP CONSTRAINT booking_pk;
       public         postgres    false    182    182            �           2606    20335    car_pk 
   CONSTRAINT     A   ALTER TABLE ONLY car
    ADD CONSTRAINT car_pk PRIMARY KEY (id);
 4   ALTER TABLE ONLY public.car DROP CONSTRAINT car_pk;
       public         postgres    false    189    189            �           2606    20337 
   invoice_pk 
   CONSTRAINT     I   ALTER TABLE ONLY invoice
    ADD CONSTRAINT invoice_pk PRIMARY KEY (id);
 <   ALTER TABLE ONLY public.invoice DROP CONSTRAINT invoice_pk;
       public         postgres    false    193    193            �           2606    20339    location_pk 
   CONSTRAINT     K   ALTER TABLE ONLY location
    ADD CONSTRAINT location_pk PRIMARY KEY (id);
 >   ALTER TABLE ONLY public.location DROP CONSTRAINT location_pk;
       public         postgres    false    196    196            �           2606    20341    order_pk 
   CONSTRAINT     G   ALTER TABLE ONLY "order"
    ADD CONSTRAINT order_pk PRIMARY KEY (id);
 :   ALTER TABLE ONLY public."order" DROP CONSTRAINT order_pk;
       public         postgres    false    198    198            �           2606    20343 	   reason_pk 
   CONSTRAINT     G   ALTER TABLE ONLY reason
    ADD CONSTRAINT reason_pk PRIMARY KEY (id);
 :   ALTER TABLE ONLY public.reason DROP CONSTRAINT reason_pk;
       public         postgres    false    205    205            �           2606    20345 
   setting_pk 
   CONSTRAINT     I   ALTER TABLE ONLY setting
    ADD CONSTRAINT setting_pk PRIMARY KEY (id);
 <   ALTER TABLE ONLY public.setting DROP CONSTRAINT setting_pk;
       public         postgres    false    207    207            �           2606    20347    type_pk 
   CONSTRAINT     C   ALTER TABLE ONLY type
    ADD CONSTRAINT type_pk PRIMARY KEY (id);
 6   ALTER TABLE ONLY public.type DROP CONSTRAINT type_pk;
       public         postgres    false    209    209            �           2606    20349    user_credentials_pk 
   CONSTRAINT     [   ALTER TABLE ONLY user_credentials
    ADD CONSTRAINT user_credentials_pk PRIMARY KEY (id);
 N   ALTER TABLE ONLY public.user_credentials DROP CONSTRAINT user_credentials_pk;
       public         postgres    false    211    211            �           2606    20351    user_profile_pk 
   CONSTRAINT     S   ALTER TABLE ONLY user_profile
    ADD CONSTRAINT user_profile_pk PRIMARY KEY (id);
 F   ALTER TABLE ONLY public.user_profile DROP CONSTRAINT user_profile_pk;
       public         postgres    false    213    213            �           2606    20352    booking_fk0    FK CONSTRAINT     m   ALTER TABLE ONLY booking
    ADD CONSTRAINT booking_fk0 FOREIGN KEY (client_id) REFERENCES user_profile(id);
 =   ALTER TABLE ONLY public.booking DROP CONSTRAINT booking_fk0;
       public       postgres    false    213    182    2197            �           2606    20357    booking_fk1    FK CONSTRAINT     a   ALTER TABLE ONLY booking
    ADD CONSTRAINT booking_fk1 FOREIGN KEY (car_id) REFERENCES car(id);
 =   ALTER TABLE ONLY public.booking DROP CONSTRAINT booking_fk1;
       public       postgres    false    2181    182    189            �           2606    20362    booking_fk2    FK CONSTRAINT     p   ALTER TABLE ONLY booking
    ADD CONSTRAINT booking_fk2 FOREIGN KEY (location_from_id) REFERENCES location(id);
 =   ALTER TABLE ONLY public.booking DROP CONSTRAINT booking_fk2;
       public       postgres    false    196    182    2185            �           2606    20367    booking_fk3    FK CONSTRAINT     n   ALTER TABLE ONLY booking
    ADD CONSTRAINT booking_fk3 FOREIGN KEY (location_to_id) REFERENCES location(id);
 =   ALTER TABLE ONLY public.booking DROP CONSTRAINT booking_fk3;
       public       postgres    false    2185    196    182            �           2606    20372    booking_fk4    FK CONSTRAINT     g   ALTER TABLE ONLY booking
    ADD CONSTRAINT booking_fk4 FOREIGN KEY (reason_id) REFERENCES reason(id);
 =   ALTER TABLE ONLY public.booking DROP CONSTRAINT booking_fk4;
       public       postgres    false    2189    182    205            �           2606    20377    car_fk0    FK CONSTRAINT     [   ALTER TABLE ONLY car
    ADD CONSTRAINT car_fk0 FOREIGN KEY (type_id) REFERENCES type(id);
 5   ALTER TABLE ONLY public.car DROP CONSTRAINT car_fk0;
       public       postgres    false    209    189    2193            �           2606    20382    car_fk1    FK CONSTRAINT     c   ALTER TABLE ONLY car
    ADD CONSTRAINT car_fk1 FOREIGN KEY (location_id) REFERENCES location(id);
 5   ALTER TABLE ONLY public.car DROP CONSTRAINT car_fk1;
       public       postgres    false    189    196    2185            �           2606    20387    invoice_fk0    FK CONSTRAINT     g   ALTER TABLE ONLY invoice
    ADD CONSTRAINT invoice_fk0 FOREIGN KEY (order_id) REFERENCES "order"(id);
 =   ALTER TABLE ONLY public.invoice DROP CONSTRAINT invoice_fk0;
       public       postgres    false    198    2187    193            �           2606    20392 	   order_fk0    FK CONSTRAINT     k   ALTER TABLE ONLY "order"
    ADD CONSTRAINT order_fk0 FOREIGN KEY (client_id) REFERENCES user_profile(id);
 ;   ALTER TABLE ONLY public."order" DROP CONSTRAINT order_fk0;
       public       postgres    false    2197    198    213            �           2606    20397 	   order_fk1    FK CONSTRAINT     _   ALTER TABLE ONLY "order"
    ADD CONSTRAINT order_fk1 FOREIGN KEY (car_id) REFERENCES car(id);
 ;   ALTER TABLE ONLY public."order" DROP CONSTRAINT order_fk1;
       public       postgres    false    198    189    2181            �           2606    20402 	   order_fk2    FK CONSTRAINT     k   ALTER TABLE ONLY "order"
    ADD CONSTRAINT order_fk2 FOREIGN KEY (location_from) REFERENCES location(id);
 ;   ALTER TABLE ONLY public."order" DROP CONSTRAINT order_fk2;
       public       postgres    false    2185    198    196            �           2606    20407 	   order_fk3    FK CONSTRAINT     i   ALTER TABLE ONLY "order"
    ADD CONSTRAINT order_fk3 FOREIGN KEY (location_to) REFERENCES location(id);
 ;   ALTER TABLE ONLY public."order" DROP CONSTRAINT order_fk3;
       public       postgres    false    198    196    2185            �           2606    20412 	   order_fk4    FK CONSTRAINT     e   ALTER TABLE ONLY "order"
    ADD CONSTRAINT order_fk4 FOREIGN KEY (reason_id) REFERENCES reason(id);
 ;   ALTER TABLE ONLY public."order" DROP CONSTRAINT order_fk4;
       public       postgres    false    2189    198    205            �           2606    20417    user_profile_fk0    FK CONSTRAINT     t   ALTER TABLE ONLY user_profile
    ADD CONSTRAINT user_profile_fk0 FOREIGN KEY (id) REFERENCES user_credentials(id);
 G   ALTER TABLE ONLY public.user_profile DROP CONSTRAINT user_profile_fk0;
       public       postgres    false    2195    213    211            	   �  x���Oo�0𻟢74A��-mmw����q��]SfȔ&P��_A6���#K��+I����uC��y7�3I�d�|�������)�S�+�(+?Of�Ĥ��̜��5�������:��'���N�es7w:G��'��no�C��r�K>!o���|M��'SS
A�Ԡ����Z�Zc4�׉�*F��v�l���i���"a}MH��j�@I�PD�cm���@��wYP3��7��,��^F��@5U��n	˱����1CV�������Cޣ"�V��
`�,�&��K@��E�_X���Z<p�b�B�!�X5.�e��,����u4w�«ja�f�6X�1�G,l ,�aA�-2`���c��k5n�~��#3s/3*���gֻ��F_~��T      	   U  x�͒�n�0E�|���U�e�+hQ��ⵍ&���8�__;���YY�Ό|�f���b���f�[+A��R�.�L�,j����A��Dw�����M�	�\�Z+���T�.�46m-��YBjު�*T����q�:,v08k��>U���.:��O�#��V+ӱz���E��z�vv%�Md,G[�?|4�3y-��8Ǻ��gcW��Zc�������p�^H8b�����I��z�`6����Ҝ�A��¨q6�K�r�MD�q�\07���f�L��!�r������\װ6_}�8�0�AH�:���Ia����,
h�~�]�ҧ��;}Q�      !	   
   x���          $	   w   x���v
Q���W��ON,���S��L�Q�K�M�Q�I,y�
a�>���
�:
��ii�ɩ
�
�E�)y��:
f�zf&@�L��DӚ˓dc��!�56�36�Q05�361��� � 0�      &	   
   x���          -	   <   x���v
Q���W(JM,��S��L�Q�K�M�Ts�	uV�0�QP���kZsqq ��A      /	   �   x�5�1�0�=��F����:8�B�]5���%�+�o�ox﫛�ztP7�6V�2A��ꅆ�y��e�a���_�"����6��ce�l>)���O�;��}	���Y�PX�3Bn{Bȱ����.v      1	   �   x��α
�@�O��-�`�NN
R�V��lC=h�\;����� ������M[];����+ dvP���t01���g��;ޯ���E�1Z���AA`ۣ�����~<ݪ�BAza$�L�GL���cQ懤�#h+���4��y�^��;Q	'I�&u]�      3	   �   x���v
Q���W(-N-�O.JMI�+�L�)V��L�QH�M���Q(H,..�/
��j*�9���+h�(�����g����MQ
�� icMk.OJ-1�W\\��_�R�������,�-\\ �h?�      5	     x��P�N�0}�W�m��F�^W� M��4$6x���[#�dJR&�z�J�Ȳ|�s٧Xo�/[(��gh-��l�^	&�f��ƺR�18�+�	G�)K�jO;2vҸ���'Q׆�epn��UT�u���Ԋ��<���i
o����&��F
Ӑ{�HQk��y��x28 �1��b��U�`EQ<��{Ї�6�!�F@��i��&Lc\`�H�8�'�k��C΃l<���'�#�.]�E�b������q:Ǥ���i����k��;���Y���b4��5��     