--
-- PostgreSQL database dump
--

-- Dumped from database version 14.0 (Debian 14.0-1.pgdg110+1)
-- Dumped by pg_dump version 14.0

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: empleados_bd; Type: DATABASE; Schema: -; Owner: root
--

CREATE DATABASE empleados_bd WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE = 'en_US.utf8';


ALTER DATABASE empleados_bd OWNER TO root;

\connect empleados_bd

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: empleados; Type: TABLE; Schema: public; Owner: root
--

CREATE TABLE public.empleados (
    id integer NOT NULL,
    nombre character varying(100) NOT NULL,
    apellido character varying(100) NOT NULL,
    numero_rut bigint NOT NULL,
    digito_verificador character(1) NOT NULL,
    cargo character varying(100) NOT NULL,
    salario_base numeric(10,2) NOT NULL,
    bono numeric(10,2) DEFAULT 0.00 NOT NULL,
    descuento numeric(10,2) DEFAULT 0.00 NOT NULL
);


ALTER TABLE public.empleados OWNER TO root;

--
-- Name: empleados_id_seq; Type: SEQUENCE; Schema: public; Owner: root
--

CREATE SEQUENCE public.empleados_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.empleados_id_seq OWNER TO root;

--
-- Name: empleados_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: root
--

ALTER SEQUENCE public.empleados_id_seq OWNED BY public.empleados.id;


--
-- Name: empleados id; Type: DEFAULT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.empleados ALTER COLUMN id SET DEFAULT nextval('public.empleados_id_seq'::regclass);


--
-- Name: empleados empleados_numero_rut_key; Type: CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.empleados
    ADD CONSTRAINT empleados_numero_rut_key UNIQUE (numero_rut);


--
-- Name: empleados empleados_pkey; Type: CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.empleados
    ADD CONSTRAINT empleados_pkey PRIMARY KEY (id);


--
-- PostgreSQL database dump complete
--

