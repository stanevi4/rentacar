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
	"year_prodaction" int NOT NULL,
	"transmission_type" int NOT NULL,
	"power" int NOT NULL,
	"actual_car" BOOLEAN NOT NULL,
	"image" bytea NOT NULL,
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



CREATE TABLE "car_status" (
	"id" serial NOT NULL,
	"created" TIMESTAMP NOT NULL,
	"car_id" serial NOT NULL,
	"date_from" TIMESTAMP NOT NULL,
	"status" BOOLEAN NOT NULL,
	CONSTRAINT car_status_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "price" (
	"id" serial NOT NULL,
	"created" TIMESTAMP NOT NULL,
	"car_id" serial NOT NULL,
	"date_from" TIMESTAMP NOT NULL,
	"value" double NOT NULL,
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
	"contract" character varying(100) NOT NULL,
	"summ" double NOT NULL,
	"note" character varying(100) NOT NULL,
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
	"order_id" serial NOT NULL,
	"check_number" character varying(20) NOT NULL,
	"summ" double NOT NULL,
	"reason" character varying(100) NOT NULL,
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
	"name" character varying(100) NOT NULL,
	CONSTRAINT damage_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "order_details" (
	"id" serial NOT NULL,
	"order_id" serial NOT NULL,
	"reason_id" serial NOT NULL,
	"damage_id" serial NOT NULL,
	CONSTRAINT order_details_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



ALTER TABLE "user_profile" ADD CONSTRAINT "user_profile_fk0" FOREIGN KEY ("id") REFERENCES "user_credentials"("id");

ALTER TABLE "user_credentials" ADD CONSTRAINT "user_credentials_fk0" FOREIGN KEY ("id") REFERENCES "user_profile"("id");

ALTER TABLE "car" ADD CONSTRAINT "car_fk0" FOREIGN KEY ("model_id") REFERENCES "model"("id");


ALTER TABLE "model" ADD CONSTRAINT "model_fk0" FOREIGN KEY ("brand_id") REFERENCES "brand"("id");

ALTER TABLE "car_status" ADD CONSTRAINT "car_status_fk0" FOREIGN KEY ("car_id") REFERENCES "car"("id");

ALTER TABLE "price" ADD CONSTRAINT "price_fk0" FOREIGN KEY ("car_id") REFERENCES "car"("id");

ALTER TABLE "order" ADD CONSTRAINT "order_fk0" FOREIGN KEY ("client_id") REFERENCES "user_credentials"("id");
ALTER TABLE "order" ADD CONSTRAINT "order_fk1" FOREIGN KEY ("car_id") REFERENCES "car"("id");

ALTER TABLE "order_history" ADD CONSTRAINT "order_history_fk0" FOREIGN KEY ("order_id") REFERENCES "order"("id");

ALTER TABLE "invoice" ADD CONSTRAINT "invoice_fk0" FOREIGN KEY ("order_id") REFERENCES "order"("id");



ALTER TABLE "order_details" ADD CONSTRAINT "order_details_fk0" FOREIGN KEY ("order_id") REFERENCES "order"("id");
ALTER TABLE "order_details" ADD CONSTRAINT "order_details_fk1" FOREIGN KEY ("reason_id") REFERENCES "reason"("id");
ALTER TABLE "order_details" ADD CONSTRAINT "order_details_fk2" FOREIGN KEY ("damage_id") REFERENCES "damage"("id");

