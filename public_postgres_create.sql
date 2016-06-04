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
	"reg_number" character varying(20) NOT NULL,
	"model_id" serial NOT NULL,
	"year_prodaction" int,
	"transmission_type" int,
	"power" int,
	"descriptoon" character varying(500),
	"image" character varying(100),
	"car_status" int NOT NULL,
	"price" DECIMAL NOT NULL,
	"location_id" serial NOT NULL,
	CONSTRAINT car_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "model" (
	"id" serial NOT NULL,
	"model_name" character varying(100) NOT NULL,
	CONSTRAINT model_pk PRIMARY KEY ("id")
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
	"contract" character varying(100),
	"summ" DECIMAL NOT NULL,
	"note" character varying(100),
	"order_status" int NOT NULL,
	"reason_id" serial,
	"invoice_id" serial,
	"location_from" serial NOT NULL,
	"location_to" serial NOT NULL,
	CONSTRAINT order_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "invoice" (
	"id" serial NOT NULL,
	"created" TIMESTAMP NOT NULL,
	"check_number" character varying(20),
	"summ" DECIMAL NOT NULL,
	"reason" character varying(100),
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



CREATE TABLE "damage" (
	"id" serial NOT NULL,
	"order_id" serial NOT NULL,
	"damage" character varying(100) NOT NULL,
	CONSTRAINT damage_pk PRIMARY KEY ("id")
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



ALTER TABLE "user_profile" ADD CONSTRAINT "user_profile_fk0" FOREIGN KEY ("id") REFERENCES "user_credentials"("id");


ALTER TABLE "car" ADD CONSTRAINT "car_fk0" FOREIGN KEY ("model_id") REFERENCES "model"("id");
ALTER TABLE "car" ADD CONSTRAINT "car_fk1" FOREIGN KEY ("location_id") REFERENCES "location"("id");


ALTER TABLE "order" ADD CONSTRAINT "order_fk0" FOREIGN KEY ("client_id") REFERENCES "user_profile"("id");
ALTER TABLE "order" ADD CONSTRAINT "order_fk1" FOREIGN KEY ("car_id") REFERENCES "car"("id");
ALTER TABLE "order" ADD CONSTRAINT "order_fk2" FOREIGN KEY ("reason_id") REFERENCES "reason"("id");
ALTER TABLE "order" ADD CONSTRAINT "order_fk3" FOREIGN KEY ("invoice_id") REFERENCES "invoice"("id");
ALTER TABLE "order" ADD CONSTRAINT "order_fk4" FOREIGN KEY ("location_from") REFERENCES "location"("id");
ALTER TABLE "order" ADD CONSTRAINT "order_fk5" FOREIGN KEY ("location_to") REFERENCES "location"("id");



ALTER TABLE "damage" ADD CONSTRAINT "damage_fk0" FOREIGN KEY ("order_id") REFERENCES "order"("id");


