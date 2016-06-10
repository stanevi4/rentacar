CREATE TABLE "user_profile" (
	"id" serial NOT NULL,
	"first_name" character varying(100) NOT NULL,
	"last_name" character varying(100) NOT NULL,
	"created" TIMESTAMP NOT NULL,
	"license_number" character varying(20),
	"birth_day" DATE,
	"address" character varying(100),
	"phone_number" character varying(100) NOT NULL,
	"city" character varying(100),
	"region" character varying(100),
	"zip_code" character varying(100),
	CONSTRAINT user_profile_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "user_credentials" (
	"id" serial NOT NULL,
	"email" character varying(100) NOT NULL,
	"password" character varying(100) NOT NULL,
	"role" int NOT NULL,
	CONSTRAINT user_credentials_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "car" (
	"id" serial NOT NULL,
	"name" character varying(100) NOT NULL,
	"type_id" serial NOT NULL,
	"location_id" serial NOT NULL,
	"car_status" int NOT NULL,
	"status_changed" TIMESTAMP,
	"reg_number" character varying(20),
	"year_prodaction" int,
	"description" character varying,
	"image" character varying,
	CONSTRAINT car_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "order" (
	"id" serial NOT NULL,
	"created" TIMESTAMP NOT NULL,
	"client_id" serial NOT NULL,
	"car_id" serial NOT NULL,
	"date_from" TIMESTAMP NOT NULL,
	"date_to" TIMESTAMP NOT NULL,
	"summ" DECIMAL NOT NULL,
	"location_from" serial NOT NULL,
	"location_to" serial NOT NULL,
	"reason_id" serial,
	"damage" character varying,
	"note" character varying,
	"order_status" int NOT NULL,
	CONSTRAINT order_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "invoice" (
	"id" serial NOT NULL,
	"created" TIMESTAMP NOT NULL,
	"summ" DECIMAL NOT NULL,
	"note" character varying,
	"order_id" serial NOT NULL,
	CONSTRAINT invoice_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "reason" (
	"id" serial NOT NULL,
	"name" character varying(100) NOT NULL,
	CONSTRAINT reason_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "location" (
	"id" serial NOT NULL,
	"name" character varying(100) NOT NULL,
	"lat" DECIMAL NOT NULL,
	"lng" DECIMAL NOT NULL,
	CONSTRAINT location_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "type" (
	"id" serial NOT NULL,
	"name" character varying(100) NOT NULL,
	"num_passengers" integer NOT NULL,
	"num_bags" integer NOT NULL,
	"num_doors" integer NOT NULL,
	"transmission_type" integer NOT NULL,
	"price_per_hour" DECIMAL NOT NULL,
	CONSTRAINT type_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "setting" (
	"id" serial NOT NULL,
	"min_booking_length" integer NOT NULL DEFAULT '0',
	"car_while_pending" integer NOT NULL DEFAULT '0',
	"deposit_payment" integer NOT NULL DEFAULT '10',
	"currency" integer NOT NULL,
	"car_between_pending" integer NOT NULL,
	CONSTRAINT setting_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "booking" (
	"id" serial NOT NULL,
	"created" TIMESTAMP NOT NULL,
	"client_id" serial NOT NULL,
	"car_id" serial NOT NULL,
	"location_from_id" serial NOT NULL,
	"location_to_id" serial NOT NULL,
	"date_from" TIMESTAMP NOT NULL,
	"date_to" TIMESTAMP NOT NULL,
	"summ" DECIMAL NOT NULL,
	"reason_id" serial,
	"damage" character varying,
	"note" character varying,
	"order_status" int NOT NULL,
	CONSTRAINT booking_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



ALTER TABLE "user_profile" ADD CONSTRAINT "user_profile_fk0" FOREIGN KEY ("id") REFERENCES "user_credentials"("id");


ALTER TABLE "car" ADD CONSTRAINT "car_fk0" FOREIGN KEY ("type_id") REFERENCES "type"("id");
ALTER TABLE "car" ADD CONSTRAINT "car_fk1" FOREIGN KEY ("location_id") REFERENCES "location"("id");

ALTER TABLE "order" ADD CONSTRAINT "order_fk0" FOREIGN KEY ("client_id") REFERENCES "user_profile"("id");
ALTER TABLE "order" ADD CONSTRAINT "order_fk1" FOREIGN KEY ("car_id") REFERENCES "car"("id");
ALTER TABLE "order" ADD CONSTRAINT "order_fk2" FOREIGN KEY ("location_from") REFERENCES "location"("id");
ALTER TABLE "order" ADD CONSTRAINT "order_fk3" FOREIGN KEY ("location_to") REFERENCES "location"("id");
ALTER TABLE "order" ADD CONSTRAINT "order_fk4" FOREIGN KEY ("reason_id") REFERENCES "reason"("id");

ALTER TABLE "invoice" ADD CONSTRAINT "invoice_fk0" FOREIGN KEY ("order_id") REFERENCES "order"("id");





ALTER TABLE "booking" ADD CONSTRAINT "booking_fk0" FOREIGN KEY ("client_id") REFERENCES "user_profile"("id");
ALTER TABLE "booking" ADD CONSTRAINT "booking_fk1" FOREIGN KEY ("car_id") REFERENCES "car"("id");
ALTER TABLE "booking" ADD CONSTRAINT "booking_fk2" FOREIGN KEY ("location_from_id") REFERENCES "location"("id");
ALTER TABLE "booking" ADD CONSTRAINT "booking_fk3" FOREIGN KEY ("location_to_id") REFERENCES "location"("id");
ALTER TABLE "booking" ADD CONSTRAINT "booking_fk4" FOREIGN KEY ("reason_id") REFERENCES "reason"("id");

