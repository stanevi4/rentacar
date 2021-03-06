--
-- PostgreSQL database dump
--

-- Dumped from database version 9.5.2
-- Dumped by pg_dump version 9.5.2

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

SET search_path = public, pg_catalog;

SET default_with_oids = false;

--
-- Name: booking; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE booking (
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


--
-- Name: booking_car_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE booking_car_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: booking_car_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE booking_car_id_seq OWNED BY booking.car_id;


--
-- Name: booking_client_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE booking_client_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: booking_client_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE booking_client_id_seq OWNED BY booking.client_id;


--
-- Name: booking_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE booking_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: booking_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE booking_id_seq OWNED BY booking.id;


--
-- Name: booking_location_from_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE booking_location_from_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: booking_location_from_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE booking_location_from_id_seq OWNED BY booking.location_from_id;


--
-- Name: booking_location_to_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE booking_location_to_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: booking_location_to_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE booking_location_to_id_seq OWNED BY booking.location_to_id;


--
-- Name: booking_reason_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE booking_reason_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: booking_reason_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE booking_reason_id_seq OWNED BY booking.reason_id;


--
-- Name: car; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE car (
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


--
-- Name: car_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE car_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: car_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE car_id_seq OWNED BY car.id;


--
-- Name: car_location_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE car_location_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: car_location_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE car_location_id_seq OWNED BY car.location_id;


--
-- Name: car_type_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE car_type_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: car_type_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE car_type_id_seq OWNED BY car.type_id;


--
-- Name: invoice; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE invoice (
    id integer NOT NULL,
    created timestamp without time zone NOT NULL,
    summ numeric NOT NULL,
    note character varying,
    order_id integer NOT NULL
);


--
-- Name: invoice_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE invoice_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: invoice_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE invoice_id_seq OWNED BY invoice.id;


--
-- Name: invoice_order_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE invoice_order_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: invoice_order_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE invoice_order_id_seq OWNED BY invoice.order_id;


--
-- Name: location; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE location (
    id integer NOT NULL,
    name character varying(100) NOT NULL,
    lat numeric NOT NULL,
    lng numeric NOT NULL
);


--
-- Name: location_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE location_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: location_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE location_id_seq OWNED BY location.id;


--
-- Name: order; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE "order" (
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


--
-- Name: order_car_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE order_car_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: order_car_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE order_car_id_seq OWNED BY "order".car_id;


--
-- Name: order_client_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE order_client_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: order_client_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE order_client_id_seq OWNED BY "order".client_id;


--
-- Name: order_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE order_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: order_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE order_id_seq OWNED BY "order".id;


--
-- Name: order_location_from_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE order_location_from_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: order_location_from_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE order_location_from_seq OWNED BY "order".location_from;


--
-- Name: order_location_to_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE order_location_to_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: order_location_to_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE order_location_to_seq OWNED BY "order".location_to;


--
-- Name: order_reason_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE order_reason_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: order_reason_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE order_reason_id_seq OWNED BY "order".reason_id;


--
-- Name: reason; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE reason (
    id integer NOT NULL,
    name character varying(100) NOT NULL
);


--
-- Name: reason_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE reason_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: reason_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE reason_id_seq OWNED BY reason.id;


--
-- Name: setting; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE setting (
    id integer NOT NULL,
    min_booking_length integer DEFAULT 0 NOT NULL,
    car_while_pending integer DEFAULT 0 NOT NULL,
    deposit_payment integer DEFAULT 10 NOT NULL,
    currency integer NOT NULL,
    car_between_pending integer NOT NULL
);


--
-- Name: setting_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE setting_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: setting_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE setting_id_seq OWNED BY setting.id;


--
-- Name: type; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE type (
    id integer NOT NULL,
    name character varying(100) NOT NULL,
    num_passengers integer NOT NULL,
    num_bags integer NOT NULL,
    num_doors integer NOT NULL,
    transmission_type integer NOT NULL,
    price_per_hour numeric NOT NULL
);


--
-- Name: type_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE type_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: type_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE type_id_seq OWNED BY type.id;


--
-- Name: user_credentials; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE user_credentials (
    id integer NOT NULL,
    email character varying(100) NOT NULL,
    password character varying(100) NOT NULL,
    role integer NOT NULL
);


--
-- Name: user_credentials_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE user_credentials_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: user_credentials_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE user_credentials_id_seq OWNED BY user_credentials.id;


--
-- Name: user_profile; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE user_profile (
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


--
-- Name: user_profile_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE user_profile_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: user_profile_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE user_profile_id_seq OWNED BY user_profile.id;


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY booking ALTER COLUMN id SET DEFAULT nextval('booking_id_seq'::regclass);


--
-- Name: client_id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY booking ALTER COLUMN client_id SET DEFAULT nextval('booking_client_id_seq'::regclass);


--
-- Name: car_id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY booking ALTER COLUMN car_id SET DEFAULT nextval('booking_car_id_seq'::regclass);


--
-- Name: location_from_id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY booking ALTER COLUMN location_from_id SET DEFAULT nextval('booking_location_from_id_seq'::regclass);


--
-- Name: location_to_id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY booking ALTER COLUMN location_to_id SET DEFAULT nextval('booking_location_to_id_seq'::regclass);


--
-- Name: reason_id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY booking ALTER COLUMN reason_id SET DEFAULT nextval('booking_reason_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY car ALTER COLUMN id SET DEFAULT nextval('car_id_seq'::regclass);


--
-- Name: type_id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY car ALTER COLUMN type_id SET DEFAULT nextval('car_type_id_seq'::regclass);


--
-- Name: location_id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY car ALTER COLUMN location_id SET DEFAULT nextval('car_location_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY invoice ALTER COLUMN id SET DEFAULT nextval('invoice_id_seq'::regclass);


--
-- Name: order_id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY invoice ALTER COLUMN order_id SET DEFAULT nextval('invoice_order_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY location ALTER COLUMN id SET DEFAULT nextval('location_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY "order" ALTER COLUMN id SET DEFAULT nextval('order_id_seq'::regclass);


--
-- Name: client_id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY "order" ALTER COLUMN client_id SET DEFAULT nextval('order_client_id_seq'::regclass);


--
-- Name: car_id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY "order" ALTER COLUMN car_id SET DEFAULT nextval('order_car_id_seq'::regclass);


--
-- Name: location_from; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY "order" ALTER COLUMN location_from SET DEFAULT nextval('order_location_from_seq'::regclass);


--
-- Name: location_to; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY "order" ALTER COLUMN location_to SET DEFAULT nextval('order_location_to_seq'::regclass);


--
-- Name: reason_id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY "order" ALTER COLUMN reason_id SET DEFAULT nextval('order_reason_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY reason ALTER COLUMN id SET DEFAULT nextval('reason_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY setting ALTER COLUMN id SET DEFAULT nextval('setting_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY type ALTER COLUMN id SET DEFAULT nextval('type_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY user_credentials ALTER COLUMN id SET DEFAULT nextval('user_credentials_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY user_profile ALTER COLUMN id SET DEFAULT nextval('user_profile_id_seq'::regclass);


--
-- Data for Name: booking; Type: TABLE DATA; Schema: public; Owner: -
--



--
-- Name: booking_car_id_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('booking_car_id_seq', 1, false);


--
-- Name: booking_client_id_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('booking_client_id_seq', 1, false);


--
-- Name: booking_id_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('booking_id_seq', 5, true);


--
-- Name: booking_location_from_id_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('booking_location_from_id_seq', 1, false);


--
-- Name: booking_location_to_id_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('booking_location_to_id_seq', 1, false);


--
-- Name: booking_reason_id_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('booking_reason_id_seq', 1, false);


--
-- Data for Name: car; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO car (id, name, type_id, location_id, car_status, status_changed, reg_number, year_prodaction, description, image) VALUES (1, 'Mercedes Benz', 2, 1, 0, NULL, 'RG34553', 2012, 'Mercedes-Benz traces its origins to Karl Benz creation of the first petrol-powered car  the Benz Patent Motorwagen  financed by Bertha Benz and patented in January 1886 and Gottlieb Daimler', 'mercedes.jpg');
INSERT INTO car (id, name, type_id, location_id, car_status, status_changed, reg_number, year_prodaction, description, image) VALUES (2, 'Audi A6 GTI CABRI', 2, 1, 0, NULL, 'RG3456', 2014, 'venerable Ford flathead V8 engine was refined and updated until its overhead valve successor filled its spot under the hoods of Ford passenger', 'audi_cabri.jpg');
INSERT INTO car (id, name, type_id, location_id, car_status, status_changed, reg_number, year_prodaction, description, image) VALUES (3, 'Audi Q5 cross', 2, 1, 0, NULL, 'TE878787', 2010, 'Have circumnavigated the United States in 22 days in a zero-option 88 Hilux and taken all manner of godawful beaters on long highway drives  and so a few not-so-harsh bumps on a deferred-maintenanc', 'audi_cross.jpg');
INSERT INTO car (id, name, type_id, location_id, car_status, status_changed, reg_number, year_prodaction, description, image) VALUES (5, 'VAZ 21099 SAMARA', 1, 1, 0, NULL, 'RH984985', 1993, 'Just nice car', 'zubilo.jpg');
INSERT INTO car (id, name, type_id, location_id, car_status, status_changed, reg_number, year_prodaction, description, image) VALUES (6, 'Volkswagen Passat B3', 1, 1, 0, NULL, 'KL9983', 1990, 'Grodno-Bialystok-Grodno-Bialystok-Grodno', 'vw-passat_b3.jpg');
INSERT INTO car (id, name, type_id, location_id, car_status, status_changed, reg_number, year_prodaction, description, image) VALUES (4, 'ZAZ 968M', 1, 1, 0, NULL, 'KR98987', 1980, 'Just nice car', 'zapor.jpg');


--
-- Name: car_id_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('car_id_seq', 6, true);


--
-- Name: car_location_id_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('car_location_id_seq', 1, false);


--
-- Name: car_type_id_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('car_type_id_seq', 1, false);


--
-- Data for Name: invoice; Type: TABLE DATA; Schema: public; Owner: -
--



--
-- Name: invoice_id_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('invoice_id_seq', 1, false);


--
-- Name: invoice_order_id_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('invoice_order_id_seq', 1, false);


--
-- Data for Name: location; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO location (id, name, lat, lng) VALUES (1, 'Grodno Office1 main', 33.45, 44.32);
INSERT INTO location (id, name, lat, lng) VALUES (2, 'Grodno Office2', 55.33, 55.22);
INSERT INTO location (id, name, lat, lng) VALUES (3, 'Grodno Office3', 66.44, 33.67);


--
-- Name: location_id_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('location_id_seq', 3, true);


--
-- Data for Name: order; Type: TABLE DATA; Schema: public; Owner: -
--



--
-- Name: order_car_id_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('order_car_id_seq', 1, false);


--
-- Name: order_client_id_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('order_client_id_seq', 1, false);


--
-- Name: order_id_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('order_id_seq', 1, false);


--
-- Name: order_location_from_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('order_location_from_seq', 1, false);


--
-- Name: order_location_to_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('order_location_to_seq', 1, false);


--
-- Name: order_reason_id_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('order_reason_id_seq', 1, false);


--
-- Data for Name: reason; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO reason (id, name) VALUES (1, 'Reason 1 description');
INSERT INTO reason (id, name) VALUES (2, 'Reason 2 description');
INSERT INTO reason (id, name) VALUES (3, 'Reason 3 description');


--
-- Name: reason_id_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('reason_id_seq', 3, true);


--
-- Data for Name: setting; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO setting (id, min_booking_length, car_while_pending, deposit_payment, currency, car_between_pending) VALUES (1, 2, 1, 10, 0, 1);


--
-- Name: setting_id_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('setting_id_seq', 1, true);


--
-- Data for Name: type; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO type (id, name, num_passengers, num_bags, num_doors, transmission_type, price_per_hour) VALUES (1, 'Small Econom', 4, 2, 4, 0, 3);
INSERT INTO type (id, name, num_passengers, num_bags, num_doors, transmission_type, price_per_hour) VALUES (2, 'Premium Large', 5, 5, 5, 1, 10);


--
-- Name: type_id_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('type_id_seq', 2, true);


--
-- Data for Name: user_credentials; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO user_credentials (id, email, password, role) VALUES (1, 'sstbox@gmail.com', '111111', 0);
INSERT INTO user_credentials (id, email, password, role) VALUES (2, 'manager@test.com', '111111', 2);
INSERT INTO user_credentials (id, email, password, role) VALUES (3, 'client1@test.com', '111111', 1);
INSERT INTO user_credentials (id, email, password, role) VALUES (4, 'client2@test.com', '111111', 1);
INSERT INTO user_credentials (id, email, password, role) VALUES (5, 'client3@test.com', '111111', 1);


--
-- Name: user_credentials_id_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('user_credentials_id_seq', 5, true);


--
-- Data for Name: user_profile; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO user_profile (id, first_name, last_name, created, license_number, birth_day, address, phone_number, city, region, zip_code) VALUES (1, 'Siarhei', 'Stanev', '2016-06-15 09:36:55.43', 'LIC879485', '2016-06-08', 'Bogushevicha 42/72', '+37529-0987654', 'Grodno', 'Grodno', '230018');
INSERT INTO user_profile (id, first_name, last_name, created, license_number, birth_day, address, phone_number, city, region, zip_code) VALUES (2, 'Andrey', 'Ivanov', '2016-06-15 09:39:22.501', 'LIC879485', '2016-06-21', 'Sovetskaya 44/66', '+789-786876', 'Grodno', 'Grodno', '230023');
INSERT INTO user_profile (id, first_name, last_name, created, license_number, birth_day, address, phone_number, city, region, zip_code) VALUES (3, 'Ivan', 'Ivanov', '2016-06-15 09:40:57.403', 'LIC989898', '2016-06-23', 'Uluca  87/44', '+987987897', 'Grodno', 'Grodno', '23232');
INSERT INTO user_profile (id, first_name, last_name, created, license_number, birth_day, address, phone_number, city, region, zip_code) VALUES (4, 'Vladimir', 'Putin', '2016-06-15 09:42:48.281', 'LIC9987987', '2016-06-21', 'Sadovaja 55/81', '+37529-2763300', 'Moscow', 'Moscow', '545466');
INSERT INTO user_profile (id, first_name, last_name, created, license_number, birth_day, address, phone_number, city, region, zip_code) VALUES (5, 'Egor', 'Zub', '2016-06-15 09:45:33.217', 'RG234234', '2016-06-28', 'Wall Street 98/3', '+37529-434535', 'NewYork', 'NY', '897755');


--
-- Name: user_profile_id_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('user_profile_id_seq', 1, false);


--
-- Name: booking_pk; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY booking
    ADD CONSTRAINT booking_pk PRIMARY KEY (id);


--
-- Name: car_pk; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY car
    ADD CONSTRAINT car_pk PRIMARY KEY (id);


--
-- Name: invoice_pk; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY invoice
    ADD CONSTRAINT invoice_pk PRIMARY KEY (id);


--
-- Name: location_pk; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY location
    ADD CONSTRAINT location_pk PRIMARY KEY (id);


--
-- Name: order_pk; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY "order"
    ADD CONSTRAINT order_pk PRIMARY KEY (id);


--
-- Name: reason_pk; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY reason
    ADD CONSTRAINT reason_pk PRIMARY KEY (id);


--
-- Name: setting_pk; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY setting
    ADD CONSTRAINT setting_pk PRIMARY KEY (id);


--
-- Name: type_pk; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY type
    ADD CONSTRAINT type_pk PRIMARY KEY (id);


--
-- Name: user_credentials_pk; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY user_credentials
    ADD CONSTRAINT user_credentials_pk PRIMARY KEY (id);


--
-- Name: user_profile_pk; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY user_profile
    ADD CONSTRAINT user_profile_pk PRIMARY KEY (id);


--
-- Name: booking_fk0; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY booking
    ADD CONSTRAINT booking_fk0 FOREIGN KEY (client_id) REFERENCES user_profile(id);


--
-- Name: booking_fk1; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY booking
    ADD CONSTRAINT booking_fk1 FOREIGN KEY (car_id) REFERENCES car(id);


--
-- Name: booking_fk2; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY booking
    ADD CONSTRAINT booking_fk2 FOREIGN KEY (location_from_id) REFERENCES location(id);


--
-- Name: booking_fk3; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY booking
    ADD CONSTRAINT booking_fk3 FOREIGN KEY (location_to_id) REFERENCES location(id);


--
-- Name: booking_fk4; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY booking
    ADD CONSTRAINT booking_fk4 FOREIGN KEY (reason_id) REFERENCES reason(id);


--
-- Name: car_fk0; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY car
    ADD CONSTRAINT car_fk0 FOREIGN KEY (type_id) REFERENCES type(id);


--
-- Name: car_fk1; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY car
    ADD CONSTRAINT car_fk1 FOREIGN KEY (location_id) REFERENCES location(id);


--
-- Name: invoice_fk0; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY invoice
    ADD CONSTRAINT invoice_fk0 FOREIGN KEY (order_id) REFERENCES "order"(id);


--
-- Name: order_fk0; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY "order"
    ADD CONSTRAINT order_fk0 FOREIGN KEY (client_id) REFERENCES user_profile(id);


--
-- Name: order_fk1; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY "order"
    ADD CONSTRAINT order_fk1 FOREIGN KEY (car_id) REFERENCES car(id);


--
-- Name: order_fk2; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY "order"
    ADD CONSTRAINT order_fk2 FOREIGN KEY (location_from) REFERENCES location(id);


--
-- Name: order_fk3; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY "order"
    ADD CONSTRAINT order_fk3 FOREIGN KEY (location_to) REFERENCES location(id);


--
-- Name: order_fk4; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY "order"
    ADD CONSTRAINT order_fk4 FOREIGN KEY (reason_id) REFERENCES reason(id);


--
-- Name: user_profile_fk0; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY user_profile
    ADD CONSTRAINT user_profile_fk0 FOREIGN KEY (id) REFERENCES user_credentials(id);


--
-- PostgreSQL database dump complete
--

