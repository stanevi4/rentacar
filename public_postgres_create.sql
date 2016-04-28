CREATE TABLE "user_profile" (
	"id" serial NOT NULL,
	"first_name" character varying(100) NOT NULL,
	"last_name" character varying(100) NOT NULL,
	"created" TIMESTAMP NOT NULL,
	"passport_number" character varying(20) NOT NULL,
	"birth_day" DATE NOT NULL,
	"address" character varying(100) NOT NULL,
	"phone_number" character varying(100) NOT NULL,
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
	CONSTRAINT car_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "brand" (
	"id" serial NOT NULL,
	"brand_name" character varying(100) NOT NULL,
	CONSTRAINT brand_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "model" (
	"id" serial NOT NULL,
	"brand_id" serial NOT NULL,
	"model_name" character varying(100) NOT NULL,
	CONSTRAINT model_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "price" (
	"id" serial NOT NULL,
	"created" TIMESTAMP NOT NULL,
	"car_id" serial NOT NULL,
	"date_from" TIMESTAMP NOT NULL,
	"value" DECIMAL NOT NULL,
	CONSTRAINT price_pk PRIMARY KEY ("id")
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
	CONSTRAINT order_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "order_history" (
	"id" serial NOT NULL,
	"created" TIMESTAMP NOT NULL,
	"order_id" serial NOT NULL,
	"order_status" int NOT NULL,
	CONSTRAINT order_history_pk PRIMARY KEY ("id")
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



ALTER TABLE "user_profile" ADD CONSTRAINT "user_profile_fk0" FOREIGN KEY ("id") REFERENCES "user_credentials"("id");


ALTER TABLE "car" ADD CONSTRAINT "car_fk0" FOREIGN KEY ("model_id") REFERENCES "model"("id");


ALTER TABLE "model" ADD CONSTRAINT "model_fk0" FOREIGN KEY ("brand_id") REFERENCES "brand"("id");

ALTER TABLE "price" ADD CONSTRAINT "price_fk0" FOREIGN KEY ("car_id") REFERENCES "car"("id");

ALTER TABLE "order" ADD CONSTRAINT "order_fk0" FOREIGN KEY ("client_id") REFERENCES "user_profile"("id");
ALTER TABLE "order" ADD CONSTRAINT "order_fk1" FOREIGN KEY ("car_id") REFERENCES "car"("id");
ALTER TABLE "order" ADD CONSTRAINT "order_fk2" FOREIGN KEY ("reason_id") REFERENCES "reason"("id");
ALTER TABLE "order" ADD CONSTRAINT "order_fk3" FOREIGN KEY ("invoice_id") REFERENCES "invoice"("id");

ALTER TABLE "order_history" ADD CONSTRAINT "order_history_fk0" FOREIGN KEY ("order_id") REFERENCES "order"("id");



ALTER TABLE "damage" ADD CONSTRAINT "damage_fk0" FOREIGN KEY ("order_id") REFERENCES "order"("id");

