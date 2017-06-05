--
-- PostgreSQL database dump
--

-- Dumped from database version 9.6.3
-- Dumped by pg_dump version 9.6.3

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: multicert; Type: SCHEMA; Schema: -; Owner: postgres
--

CREATE SCHEMA multicert;


ALTER SCHEMA multicert OWNER TO postgres;

--
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = multicert, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: clients; Type: TABLE; Schema: multicert; Owner: postgres
--

CREATE TABLE clients (
    id integer NOT NULL,
    name text NOT NULL,
    nif character varying(9) NOT NULL,
    address character(50) NOT NULL,
    phonenumber character varying(50) NOT NULL
);


ALTER TABLE clients OWNER TO postgres;

--
-- Name: clients_id_seq; Type: SEQUENCE; Schema: multicert; Owner: postgres
--

CREATE SEQUENCE clients_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE clients_id_seq OWNER TO postgres;

--
-- Name: clients_id_seq; Type: SEQUENCE OWNED BY; Schema: multicert; Owner: postgres
--

ALTER SEQUENCE clients_id_seq OWNED BY clients.id;


--
-- Name: clients id; Type: DEFAULT; Schema: multicert; Owner: postgres
--

ALTER TABLE ONLY clients ALTER COLUMN id SET DEFAULT nextval('clients_id_seq'::regclass);


--
-- Data for Name: clients; Type: TABLE DATA; Schema: multicert; Owner: postgres
--

COPY clients (id, name, nif, address, phonenumber) FROM stdin;
\.


--
-- Name: clients_id_seq; Type: SEQUENCE SET; Schema: multicert; Owner: postgres
--

SELECT pg_catalog.setval('clients_id_seq', 8, true);


--
-- Name: clients clients_pkey; Type: CONSTRAINT; Schema: multicert; Owner: postgres
--

ALTER TABLE ONLY clients
    ADD CONSTRAINT clients_pkey PRIMARY KEY (id);


--
-- PostgreSQL database dump complete
--

