--
-- PostgreSQL database dump
--

-- Dumped from database version 10.7
-- Dumped by pg_dump version 10.7

-- Started on 2020-02-24 19:29:55 CET

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 8 (class 2615 OID 73795)
-- Name: webmail; Type: SCHEMA; Schema: -; Owner: webmail
--

CREATE SCHEMA IF NOT EXISTS webmail;


ALTER SCHEMA webmail OWNER TO webmail;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 197 (class 1259 OID 73806)
-- Name: allegato_mail_in; Type: TABLE; Schema: webmail; Owner: webmail
--

CREATE TABLE webmail.allegato_mail_in (
    id uuid NOT NULL,
    id_mail_in uuid NOT NULL,
    mime_type_allegato character varying(500) NOT NULL,
    path_allegato character varying(500) NOT NULL,
    nome_allegato character varying(200) NOT NULL,
    id_documentale_allegato character varying(100) NOT NULL,
    data_creazione timestamp without time zone NOT NULL,
    dimensione_allegato integer NOT NULL
);


ALTER TABLE webmail.allegato_mail_in OWNER TO webmail;

--
-- TOC entry 2926 (class 0 OID 0)
-- Dependencies: 197
-- Name: TABLE allegato_mail_in; Type: COMMENT; Schema: webmail; Owner: webmail
--

COMMENT ON TABLE webmail.allegato_mail_in IS 'Tabella allegato mail ingresso';


--
-- TOC entry 2927 (class 0 OID 0)
-- Dependencies: 197
-- Name: COLUMN allegato_mail_in.mime_type_allegato; Type: COMMENT; Schema: webmail; Owner: webmail
--

COMMENT ON COLUMN webmail.allegato_mail_in.mime_type_allegato IS 'Il mime type dell''allegato';


--
-- TOC entry 2928 (class 0 OID 0)
-- Dependencies: 197
-- Name: COLUMN allegato_mail_in.dimensione_allegato; Type: COMMENT; Schema: webmail; Owner: webmail
--

COMMENT ON COLUMN webmail.allegato_mail_in.dimensione_allegato IS 'La dimensione in byte dell''allegato';


--
-- TOC entry 198 (class 1259 OID 73812)
-- Name: allegato_mail_out; Type: TABLE; Schema: webmail; Owner: webmail
--

CREATE TABLE webmail.allegato_mail_out (
    id uuid NOT NULL,
    id_mail_out uuid NOT NULL,
    nome_allegato character varying(250) NOT NULL,
    mime_type_allegato character varying(250) NOT NULL,
    path_allegato character varying(500),
    id_documentale_allegato character varying(100),
    data_creazione timestamp without time zone NOT NULL,
    dimensione_allegato integer NOT NULL
);


ALTER TABLE webmail.allegato_mail_out OWNER TO webmail;

--
-- TOC entry 2929 (class 0 OID 0)
-- Dependencies: 198
-- Name: TABLE allegato_mail_out; Type: COMMENT; Schema: webmail; Owner: webmail
--

COMMENT ON TABLE webmail.allegato_mail_out IS 'Allegati della mail';


--
-- TOC entry 2930 (class 0 OID 0)
-- Dependencies: 198
-- Name: COLUMN allegato_mail_out.id; Type: COMMENT; Schema: webmail; Owner: webmail
--

COMMENT ON COLUMN webmail.allegato_mail_out.id IS 'PK del record';


--
-- TOC entry 2931 (class 0 OID 0)
-- Dependencies: 198
-- Name: COLUMN allegato_mail_out.id_mail_out; Type: COMMENT; Schema: webmail; Owner: webmail
--

COMMENT ON COLUMN webmail.allegato_mail_out.id_mail_out IS 'ID della mail out a cui l''allegato fa riferimento';


--
-- TOC entry 2932 (class 0 OID 0)
-- Dependencies: 198
-- Name: COLUMN allegato_mail_out.nome_allegato; Type: COMMENT; Schema: webmail; Owner: webmail
--

COMMENT ON COLUMN webmail.allegato_mail_out.nome_allegato IS 'Nome allegato';


--
-- TOC entry 2933 (class 0 OID 0)
-- Dependencies: 198
-- Name: COLUMN allegato_mail_out.mime_type_allegato; Type: COMMENT; Schema: webmail; Owner: webmail
--

COMMENT ON COLUMN webmail.allegato_mail_out.mime_type_allegato IS 'mime type allegato';


--
-- TOC entry 2934 (class 0 OID 0)
-- Dependencies: 198
-- Name: COLUMN allegato_mail_out.dimensione_allegato; Type: COMMENT; Schema: webmail; Owner: webmail
--

COMMENT ON COLUMN webmail.allegato_mail_out.dimensione_allegato IS 'La dimensione in byte dell''allegato';


--
-- TOC entry 199 (class 1259 OID 73818)
-- Name: casella_postale; Type: TABLE; Schema: webmail; Owner: webmail
--

CREATE TABLE webmail.casella_postale (
    id uuid NOT NULL,
    indirizzo_mail character varying(250) NOT NULL,
    password character varying(250) NOT NULL,
    nome_visualizzato character varying(250),
    pec boolean NOT NULL,
    username character varying(250) NOT NULL,
    id_folder_documentale character varying(250) NOT NULL
);


ALTER TABLE webmail.casella_postale OWNER TO webmail;

--
-- TOC entry 2935 (class 0 OID 0)
-- Dependencies: 199
-- Name: COLUMN casella_postale.id; Type: COMMENT; Schema: webmail; Owner: webmail
--

COMMENT ON COLUMN webmail.casella_postale.id IS 'La primary key del record';


--
-- TOC entry 2936 (class 0 OID 0)
-- Dependencies: 199
-- Name: COLUMN casella_postale.indirizzo_mail; Type: COMMENT; Schema: webmail; Owner: webmail
--

COMMENT ON COLUMN webmail.casella_postale.indirizzo_mail IS 'L''indirizzo mail della casella di posta. È univoco';


--
-- TOC entry 2937 (class 0 OID 0)
-- Dependencies: 199
-- Name: COLUMN casella_postale.password; Type: COMMENT; Schema: webmail; Owner: webmail
--

COMMENT ON COLUMN webmail.casella_postale.password IS 'La password da utilizzare per accedere alla casella di posta';


--
-- TOC entry 2938 (class 0 OID 0)
-- Dependencies: 199
-- Name: COLUMN casella_postale.nome_visualizzato; Type: COMMENT; Schema: webmail; Owner: webmail
--

COMMENT ON COLUMN webmail.casella_postale.nome_visualizzato IS 'L''eventuale nome visualizzato';


--
-- TOC entry 2939 (class 0 OID 0)
-- Dependencies: 199
-- Name: COLUMN casella_postale.pec; Type: COMMENT; Schema: webmail; Owner: webmail
--

COMMENT ON COLUMN webmail.casella_postale.pec IS 'Indica se è una casella PEC o meno';


--
-- TOC entry 2940 (class 0 OID 0)
-- Dependencies: 199
-- Name: COLUMN casella_postale.username; Type: COMMENT; Schema: webmail; Owner: webmail
--

COMMENT ON COLUMN webmail.casella_postale.username IS 'La username dell''utente. Può coincidere con l''indirizzo mail';


--
-- TOC entry 2941 (class 0 OID 0)
-- Dependencies: 199
-- Name: COLUMN casella_postale.id_folder_documentale; Type: COMMENT; Schema: webmail; Owner: webmail
--

COMMENT ON COLUMN webmail.casella_postale.id_folder_documentale IS 'Indica l''ID del folder principale creato sul documentale.';


--
-- TOC entry 200 (class 1259 OID 73824)
-- Name: config_mail_in; Type: TABLE; Schema: webmail; Owner: webmail
--

CREATE TABLE webmail.config_mail_in (
    id uuid NOT NULL,
    imap boolean DEFAULT false NOT NULL,
    host character varying(250) NOT NULL,
    richiede_ssl boolean DEFAULT false NOT NULL,
    data_creazione timestamp without time zone NOT NULL,
    creato_da character varying(250) NOT NULL,
    data_modifica timestamp without time zone,
    modificato_da character varying,
    id_casella_postale uuid NOT NULL,
    porta integer NOT NULL,
    protocollo character varying(10) NOT NULL,
    richiede_starttls boolean NOT NULL,
    richiede_autenticazione boolean NOT NULL
);


ALTER TABLE webmail.config_mail_in OWNER TO webmail;

--
-- TOC entry 2942 (class 0 OID 0)
-- Dependencies: 200
-- Name: COLUMN config_mail_in.id; Type: COMMENT; Schema: webmail; Owner: webmail
--

COMMENT ON COLUMN webmail.config_mail_in.id IS 'La primary key';


--
-- TOC entry 2943 (class 0 OID 0)
-- Dependencies: 200
-- Name: COLUMN config_mail_in.imap; Type: COMMENT; Schema: webmail; Owner: webmail
--

COMMENT ON COLUMN webmail.config_mail_in.imap IS 'Indica se configurazione IMAP o meno';


--
-- TOC entry 2944 (class 0 OID 0)
-- Dependencies: 200
-- Name: COLUMN config_mail_in.host; Type: COMMENT; Schema: webmail; Owner: webmail
--

COMMENT ON COLUMN webmail.config_mail_in.host IS 'L''host da utilizzare per inviare la mail';


--
-- TOC entry 2945 (class 0 OID 0)
-- Dependencies: 200
-- Name: COLUMN config_mail_in.richiede_ssl; Type: COMMENT; Schema: webmail; Owner: webmail
--

COMMENT ON COLUMN webmail.config_mail_in.richiede_ssl IS 'Indica se richiede SSL';


--
-- TOC entry 2946 (class 0 OID 0)
-- Dependencies: 200
-- Name: COLUMN config_mail_in.data_creazione; Type: COMMENT; Schema: webmail; Owner: webmail
--

COMMENT ON COLUMN webmail.config_mail_in.data_creazione IS 'La data di creazione del record.';


--
-- TOC entry 2947 (class 0 OID 0)
-- Dependencies: 200
-- Name: COLUMN config_mail_in.creato_da; Type: COMMENT; Schema: webmail; Owner: webmail
--

COMMENT ON COLUMN webmail.config_mail_in.creato_da IS 'La username dell''utente che ha creato il record.';


--
-- TOC entry 2948 (class 0 OID 0)
-- Dependencies: 200
-- Name: COLUMN config_mail_in.data_modifica; Type: COMMENT; Schema: webmail; Owner: webmail
--

COMMENT ON COLUMN webmail.config_mail_in.data_modifica IS 'La eventuale data di modifica dell record.';


--
-- TOC entry 2949 (class 0 OID 0)
-- Dependencies: 200
-- Name: COLUMN config_mail_in.modificato_da; Type: COMMENT; Schema: webmail; Owner: webmail
--

COMMENT ON COLUMN webmail.config_mail_in.modificato_da IS 'La username dell''utente che ha modificato il record.';


--
-- TOC entry 2950 (class 0 OID 0)
-- Dependencies: 200
-- Name: COLUMN config_mail_in.id_casella_postale; Type: COMMENT; Schema: webmail; Owner: webmail
--

COMMENT ON COLUMN webmail.config_mail_in.id_casella_postale IS 'FK verso casella_postale';


--
-- TOC entry 2951 (class 0 OID 0)
-- Dependencies: 200
-- Name: COLUMN config_mail_in.protocollo; Type: COMMENT; Schema: webmail; Owner: webmail
--

COMMENT ON COLUMN webmail.config_mail_in.protocollo IS 'Indica il protocollo da utilizzare. Può essere uno dei valori: imap, imaps, pop3, pop3s';


--
-- TOC entry 2952 (class 0 OID 0)
-- Dependencies: 200
-- Name: COLUMN config_mail_in.richiede_starttls; Type: COMMENT; Schema: webmail; Owner: webmail
--

COMMENT ON COLUMN webmail.config_mail_in.richiede_starttls IS 'Indica se richieste l''utilizzo di starttls; valore di default false';


--
-- TOC entry 2953 (class 0 OID 0)
-- Dependencies: 200
-- Name: COLUMN config_mail_in.richiede_autenticazione; Type: COMMENT; Schema: webmail; Owner: webmail
--

COMMENT ON COLUMN webmail.config_mail_in.richiede_autenticazione IS 'Indica se richiede autenticazione o meno. Se pari a true si utilizza la coppia di username e password della casella postale. Se pari a false non si utilizza nessuna autenticazione. Valore di default false.';


--
-- TOC entry 201 (class 1259 OID 73832)
-- Name: config_mail_out; Type: TABLE; Schema: webmail; Owner: webmail
--

CREATE TABLE webmail.config_mail_out (
    id uuid NOT NULL,
    host character varying(250) NOT NULL,
    richiede_ssl boolean DEFAULT false,
    richiede_tls boolean DEFAULT false,
    porta_ssl integer NOT NULL,
    porta_tls integer,
    data_creazione timestamp without time zone NOT NULL,
    creato_da character varying(250) NOT NULL,
    data_modifica timestamp without time zone,
    modificato_da character varying,
    id_casella_postale uuid NOT NULL,
    protocollo character varying(5) NOT NULL,
    richiede_starttls boolean NOT NULL,
    richiede_autenticazione boolean NOT NULL
);


ALTER TABLE webmail.config_mail_out OWNER TO webmail;

--
-- TOC entry 2954 (class 0 OID 0)
-- Dependencies: 201
-- Name: COLUMN config_mail_out.id; Type: COMMENT; Schema: webmail; Owner: webmail
--

COMMENT ON COLUMN webmail.config_mail_out.id IS 'la primary key';


--
-- TOC entry 2955 (class 0 OID 0)
-- Dependencies: 201
-- Name: COLUMN config_mail_out.host; Type: COMMENT; Schema: webmail; Owner: webmail
--

COMMENT ON COLUMN webmail.config_mail_out.host IS 'L''host del mail server';


--
-- TOC entry 2956 (class 0 OID 0)
-- Dependencies: 201
-- Name: COLUMN config_mail_out.richiede_ssl; Type: COMMENT; Schema: webmail; Owner: webmail
--

COMMENT ON COLUMN webmail.config_mail_out.richiede_ssl IS 'Indica se necessaria autenticazione SSL';


--
-- TOC entry 2957 (class 0 OID 0)
-- Dependencies: 201
-- Name: COLUMN config_mail_out.richiede_tls; Type: COMMENT; Schema: webmail; Owner: webmail
--

COMMENT ON COLUMN webmail.config_mail_out.richiede_tls IS 'Indica se richiede TLS';


--
-- TOC entry 2958 (class 0 OID 0)
-- Dependencies: 201
-- Name: COLUMN config_mail_out.porta_ssl; Type: COMMENT; Schema: webmail; Owner: webmail
--

COMMENT ON COLUMN webmail.config_mail_out.porta_ssl IS 'Indica la porta SSL da utilizzare.';


--
-- TOC entry 2959 (class 0 OID 0)
-- Dependencies: 201
-- Name: COLUMN config_mail_out.porta_tls; Type: COMMENT; Schema: webmail; Owner: webmail
--

COMMENT ON COLUMN webmail.config_mail_out.porta_tls IS 'Indica la porta da utilizzare per autenticazione TLS';


--
-- TOC entry 2960 (class 0 OID 0)
-- Dependencies: 201
-- Name: COLUMN config_mail_out.data_creazione; Type: COMMENT; Schema: webmail; Owner: webmail
--

COMMENT ON COLUMN webmail.config_mail_out.data_creazione IS 'La data di creazione del record.';


--
-- TOC entry 2961 (class 0 OID 0)
-- Dependencies: 201
-- Name: COLUMN config_mail_out.creato_da; Type: COMMENT; Schema: webmail; Owner: webmail
--

COMMENT ON COLUMN webmail.config_mail_out.creato_da IS 'La username dell''utente che ha creato il record.';


--
-- TOC entry 2962 (class 0 OID 0)
-- Dependencies: 201
-- Name: COLUMN config_mail_out.data_modifica; Type: COMMENT; Schema: webmail; Owner: webmail
--

COMMENT ON COLUMN webmail.config_mail_out.data_modifica IS 'La data di modifica del record.';


--
-- TOC entry 2963 (class 0 OID 0)
-- Dependencies: 201
-- Name: COLUMN config_mail_out.modificato_da; Type: COMMENT; Schema: webmail; Owner: webmail
--

COMMENT ON COLUMN webmail.config_mail_out.modificato_da IS 'La username dell''utente che ha modificato il record.';


--
-- TOC entry 2964 (class 0 OID 0)
-- Dependencies: 201
-- Name: COLUMN config_mail_out.id_casella_postale; Type: COMMENT; Schema: webmail; Owner: webmail
--

COMMENT ON COLUMN webmail.config_mail_out.id_casella_postale IS 'FK verso la tabella casella_postale';


--
-- TOC entry 2965 (class 0 OID 0)
-- Dependencies: 201
-- Name: COLUMN config_mail_out.protocollo; Type: COMMENT; Schema: webmail; Owner: webmail
--

COMMENT ON COLUMN webmail.config_mail_out.protocollo IS 'Indica il protocollo da utilizzare. Può essere uno dei valori smtp o smtps';


--
-- TOC entry 2966 (class 0 OID 0)
-- Dependencies: 201
-- Name: COLUMN config_mail_out.richiede_starttls; Type: COMMENT; Schema: webmail; Owner: webmail
--

COMMENT ON COLUMN webmail.config_mail_out.richiede_starttls IS 'Indica se richieste l''utilizzo di starttls; valore di default false';


--
-- TOC entry 2967 (class 0 OID 0)
-- Dependencies: 201
-- Name: COLUMN config_mail_out.richiede_autenticazione; Type: COMMENT; Schema: webmail; Owner: webmail
--

COMMENT ON COLUMN webmail.config_mail_out.richiede_autenticazione IS 'Indica se richiede autenticazione o meno. Se pari a true si utilizza la coppia di username e password della casella postale. Se pari a false non si utilizza nessuna autenticazione. Valore di default false.';


--
-- TOC entry 202 (class 1259 OID 73840)
-- Name: email_in; Type: TABLE; Schema: webmail; Owner: webmail
--

CREATE TABLE webmail.email_in (
    id uuid NOT NULL,
    id_messaggio character varying(500) NOT NULL,
    id_casella_postale uuid NOT NULL,
    data_ricezione_mail timestamp without time zone NOT NULL,
    oggetto_mail character varying(500),
    corpo_mail text NOT NULL,
    data_creazione timestamp without time zone NOT NULL,
    mail_letta boolean,
    id_folder_documentale character varying(250) NOT NULL,
    id_documentale_eml character varying(250) NOT NULL
);


ALTER TABLE webmail.email_in OWNER TO webmail;

--
-- TOC entry 2968 (class 0 OID 0)
-- Dependencies: 202
-- Name: COLUMN email_in.id; Type: COMMENT; Schema: webmail; Owner: webmail
--

COMMENT ON COLUMN webmail.email_in.id IS 'La primary KEY';


--
-- TOC entry 2969 (class 0 OID 0)
-- Dependencies: 202
-- Name: COLUMN email_in.id_messaggio; Type: COMMENT; Schema: webmail; Owner: webmail
--

COMMENT ON COLUMN webmail.email_in.id_messaggio IS 'Indica l''ID del messaggio mail. Dovrebbe essere univoco';


--
-- TOC entry 2970 (class 0 OID 0)
-- Dependencies: 202
-- Name: COLUMN email_in.id_casella_postale; Type: COMMENT; Schema: webmail; Owner: webmail
--

COMMENT ON COLUMN webmail.email_in.id_casella_postale IS 'Indica a chi è stato inviato il messaggio mail';


--
-- TOC entry 2971 (class 0 OID 0)
-- Dependencies: 202
-- Name: COLUMN email_in.data_ricezione_mail; Type: COMMENT; Schema: webmail; Owner: webmail
--

COMMENT ON COLUMN webmail.email_in.data_ricezione_mail IS 'Data di ricezione della mail';


--
-- TOC entry 2972 (class 0 OID 0)
-- Dependencies: 202
-- Name: COLUMN email_in.oggetto_mail; Type: COMMENT; Schema: webmail; Owner: webmail
--

COMMENT ON COLUMN webmail.email_in.oggetto_mail IS 'L''oggetto della mail';


--
-- TOC entry 2973 (class 0 OID 0)
-- Dependencies: 202
-- Name: COLUMN email_in.corpo_mail; Type: COMMENT; Schema: webmail; Owner: webmail
--

COMMENT ON COLUMN webmail.email_in.corpo_mail IS 'Corpo della mail';


--
-- TOC entry 2974 (class 0 OID 0)
-- Dependencies: 202
-- Name: COLUMN email_in.data_creazione; Type: COMMENT; Schema: webmail; Owner: webmail
--

COMMENT ON COLUMN webmail.email_in.data_creazione IS 'Data creazione del record';


--
-- TOC entry 2975 (class 0 OID 0)
-- Dependencies: 202
-- Name: COLUMN email_in.mail_letta; Type: COMMENT; Schema: webmail; Owner: webmail
--

COMMENT ON COLUMN webmail.email_in.mail_letta IS 'Indica se un messaggio mail sia stato letto o meno. ';


--
-- TOC entry 2976 (class 0 OID 0)
-- Dependencies: 202
-- Name: COLUMN email_in.id_folder_documentale; Type: COMMENT; Schema: webmail; Owner: webmail
--

COMMENT ON COLUMN webmail.email_in.id_folder_documentale IS 'ID Folder documentale in cui è salvata la mail';


--
-- TOC entry 2977 (class 0 OID 0)
-- Dependencies: 202
-- Name: COLUMN email_in.id_documentale_eml; Type: COMMENT; Schema: webmail; Owner: webmail
--

COMMENT ON COLUMN webmail.email_in.id_documentale_eml IS 'Indica l''ID documentale della mail in formato eml';


--
-- TOC entry 203 (class 1259 OID 73846)
-- Name: email_out; Type: TABLE; Schema: webmail; Owner: webmail
--

CREATE TABLE webmail.email_out (
    id uuid NOT NULL,
    id_messaggio character varying(250),
    oggetto_mail character varying(500),
    corpo_mail text NOT NULL,
    id_casella_postale uuid NOT NULL,
    data_creazione timestamp without time zone NOT NULL,
    id_folder_documentale character varying(250) NOT NULL,
    destinatari jsonb NOT NULL,
    bcc jsonb,
    bccn jsonb,
    id_documentale_eml character varying(250) NOT NULL
);


ALTER TABLE webmail.email_out OWNER TO webmail;

--
-- TOC entry 2978 (class 0 OID 0)
-- Dependencies: 203
-- Name: TABLE email_out; Type: COMMENT; Schema: webmail; Owner: webmail
--

COMMENT ON TABLE webmail.email_out IS 'Tabella mail in uscita.';


--
-- TOC entry 2979 (class 0 OID 0)
-- Dependencies: 203
-- Name: COLUMN email_out.id; Type: COMMENT; Schema: webmail; Owner: webmail
--

COMMENT ON COLUMN webmail.email_out.id IS 'Primary key';


--
-- TOC entry 2980 (class 0 OID 0)
-- Dependencies: 203
-- Name: COLUMN email_out.id_messaggio; Type: COMMENT; Schema: webmail; Owner: webmail
--

COMMENT ON COLUMN webmail.email_out.id_messaggio IS 'Indica l''id del messaggio mail generato dal mail server. Dovrebbe essere univoco';


--
-- TOC entry 2981 (class 0 OID 0)
-- Dependencies: 203
-- Name: COLUMN email_out.oggetto_mail; Type: COMMENT; Schema: webmail; Owner: webmail
--

COMMENT ON COLUMN webmail.email_out.oggetto_mail IS 'Indica l''oggetto della mail. Di solito sempre valorizzato ma potrebbe non esserlo.';


--
-- TOC entry 2982 (class 0 OID 0)
-- Dependencies: 203
-- Name: COLUMN email_out.corpo_mail; Type: COMMENT; Schema: webmail; Owner: webmail
--

COMMENT ON COLUMN webmail.email_out.corpo_mail IS 'Contiene il corpo della mail. Campo obbligatorio';


--
-- TOC entry 2983 (class 0 OID 0)
-- Dependencies: 203
-- Name: COLUMN email_out.id_casella_postale; Type: COMMENT; Schema: webmail; Owner: webmail
--

COMMENT ON COLUMN webmail.email_out.id_casella_postale IS 'Indica l''ID dell''utenza mittente';


--
-- TOC entry 2984 (class 0 OID 0)
-- Dependencies: 203
-- Name: COLUMN email_out.data_creazione; Type: COMMENT; Schema: webmail; Owner: webmail
--

COMMENT ON COLUMN webmail.email_out.data_creazione IS 'Indica la data di creazione del record. Obbligatorio';


--
-- TOC entry 2985 (class 0 OID 0)
-- Dependencies: 203
-- Name: COLUMN email_out.destinatari; Type: COMMENT; Schema: webmail; Owner: webmail
--

COMMENT ON COLUMN webmail.email_out.destinatari IS 'Rappresenta un JSON contenente l''elenco dei destinatari';


--
-- TOC entry 2986 (class 0 OID 0)
-- Dependencies: 203
-- Name: COLUMN email_out.bcc; Type: COMMENT; Schema: webmail; Owner: webmail
--

COMMENT ON COLUMN webmail.email_out.bcc IS 'Contiene l''eventuale JSON dei destinatari in copia carbone';


--
-- TOC entry 2987 (class 0 OID 0)
-- Dependencies: 203
-- Name: COLUMN email_out.bccn; Type: COMMENT; Schema: webmail; Owner: webmail
--

COMMENT ON COLUMN webmail.email_out.bccn IS 'Contiene l''eventuale JSON dei destinarari in copia carbone nascosta';


--
-- TOC entry 2988 (class 0 OID 0)
-- Dependencies: 203
-- Name: COLUMN email_out.id_documentale_eml; Type: COMMENT; Schema: webmail; Owner: webmail
--

COMMENT ON COLUMN webmail.email_out.id_documentale_eml IS 'Indica l''ID della mail in formato eml conservato nel documentale';


--
-- TOC entry 204 (class 1259 OID 73852)
-- Name: retry_notification; Type: TABLE; Schema: webmail; Owner: webmail
--

CREATE TABLE webmail.retry_notification (
    id uuid NOT NULL,
    topic_name character varying(250) NOT NULL,
    json_message jsonb NOT NULL,
    id_messaggio character varying(250) NOT NULL
);


ALTER TABLE webmail.retry_notification OWNER TO webmail;

--
-- TOC entry 2989 (class 0 OID 0)
-- Dependencies: 204
-- Name: COLUMN retry_notification.id_messaggio; Type: COMMENT; Schema: webmail; Owner: webmail
--

COMMENT ON COLUMN webmail.retry_notification.id_messaggio IS 'Indica l''id del messaggio di cui tentare l''invio notifica';


--
-- TOC entry 205 (class 1259 OID 73858)
-- Name: ricevuta; Type: TABLE; Schema: webmail; Owner: webmail
--

CREATE TABLE webmail.ricevuta (
    id uuid NOT NULL,
    id_mail_out uuid NOT NULL,
    oggetto character varying(500),
    data_ricevuta timestamp without time zone NOT NULL,
    data_creazione timestamp without time zone NOT NULL,
    id_folder_documentale character varying(250) NOT NULL,
    id_documentale_eml character varying(250) NOT NULL,
    errore character varying(50) NOT NULL,
    tipo_posta_cert character varying(50) NOT NULL,
    mittente character varying(100) NOT NULL,
    destinatari jsonb NOT NULL,
    risposte character varying(100) NOT NULL,
    gestore_emittente character varying(250) NOT NULL,
    identificativo character varying(250) NOT NULL,
    ricevuta character varying(250),
    consegna character varying(250),
    oggetto_mail character varying(250) NOT NULL
);


ALTER TABLE webmail.ricevuta OWNER TO webmail;

--
-- TOC entry 2990 (class 0 OID 0)
-- Dependencies: 205
-- Name: TABLE ricevuta; Type: COMMENT; Schema: webmail; Owner: webmail
--

COMMENT ON TABLE webmail.ricevuta IS 'Tabella delle ricevute delle PEC.';


--
-- TOC entry 2991 (class 0 OID 0)
-- Dependencies: 205
-- Name: COLUMN ricevuta.id; Type: COMMENT; Schema: webmail; Owner: webmail
--

COMMENT ON COLUMN webmail.ricevuta.id IS 'La primary key';


--
-- TOC entry 2992 (class 0 OID 0)
-- Dependencies: 205
-- Name: COLUMN ricevuta.id_mail_out; Type: COMMENT; Schema: webmail; Owner: webmail
--

COMMENT ON COLUMN webmail.ricevuta.id_mail_out IS 'Indica la mail PEC alla quale la ricevuta fa riferimento';


--
-- TOC entry 2993 (class 0 OID 0)
-- Dependencies: 205
-- Name: COLUMN ricevuta.oggetto; Type: COMMENT; Schema: webmail; Owner: webmail
--

COMMENT ON COLUMN webmail.ricevuta.oggetto IS 'Oggetto ricevuta';


--
-- TOC entry 2994 (class 0 OID 0)
-- Dependencies: 205
-- Name: COLUMN ricevuta.data_ricevuta; Type: COMMENT; Schema: webmail; Owner: webmail
--

COMMENT ON COLUMN webmail.ricevuta.data_ricevuta IS 'Data della ricevuta presa dal messaggio mail';


--
-- TOC entry 2995 (class 0 OID 0)
-- Dependencies: 205
-- Name: COLUMN ricevuta.data_creazione; Type: COMMENT; Schema: webmail; Owner: webmail
--

COMMENT ON COLUMN webmail.ricevuta.data_creazione IS 'Data creazione del record';


--
-- TOC entry 2996 (class 0 OID 0)
-- Dependencies: 205
-- Name: COLUMN ricevuta.id_folder_documentale; Type: COMMENT; Schema: webmail; Owner: webmail
--

COMMENT ON COLUMN webmail.ricevuta.id_folder_documentale IS 'L''ID del folder documentale dove si trova l''EML della ricevuta';


--
-- TOC entry 2997 (class 0 OID 0)
-- Dependencies: 205
-- Name: COLUMN ricevuta.id_documentale_eml; Type: COMMENT; Schema: webmail; Owner: webmail
--

COMMENT ON COLUMN webmail.ricevuta.id_documentale_eml IS 'L''ID documentale del file EML della ricevuta';


--
-- TOC entry 2998 (class 0 OID 0)
-- Dependencies: 205
-- Name: COLUMN ricevuta.errore; Type: COMMENT; Schema: webmail; Owner: webmail
--

COMMENT ON COLUMN webmail.ricevuta.errore IS 'Indica l''eventuale errore riscontrato in consegna e può assumere uno di questi valori nessuno | no-dest | no-dominio | virus | altro. Il valore di default è nessuno';


--
-- TOC entry 2999 (class 0 OID 0)
-- Dependencies: 205
-- Name: COLUMN ricevuta.tipo_posta_cert; Type: COMMENT; Schema: webmail; Owner: webmail
--

COMMENT ON COLUMN webmail.ricevuta.tipo_posta_cert IS 'Coincide con l''attributo tipo del tag postacert del file daticert.xml. Può assumere uno di questi valori: accettazione | non-accettazione | presa-in-carico | avvenuta-consegna | posta-certificata |
errore-consegna | preavviso-errore-consegna | rilevazione-virus';


--
-- TOC entry 3000 (class 0 OID 0)
-- Dependencies: 205
-- Name: COLUMN ricevuta.mittente; Type: COMMENT; Schema: webmail; Owner: webmail
--

COMMENT ON COLUMN webmail.ricevuta.mittente IS 'Coincide con il tag mittente figlio del tag intestazione del file daticert.xml';


--
-- TOC entry 3001 (class 0 OID 0)
-- Dependencies: 205
-- Name: COLUMN ricevuta.destinatari; Type: COMMENT; Schema: webmail; Owner: webmail
--

COMMENT ON COLUMN webmail.ricevuta.destinatari IS 'Rappresenta un JSON contenente la serializzazione dei vari tag destinatari figli del tag intestazione';


--
-- TOC entry 3002 (class 0 OID 0)
-- Dependencies: 205
-- Name: COLUMN ricevuta.risposte; Type: COMMENT; Schema: webmail; Owner: webmail
--

COMMENT ON COLUMN webmail.ricevuta.risposte IS 'Coincide con il tag risposte del tag intestazione';


--
-- TOC entry 3003 (class 0 OID 0)
-- Dependencies: 205
-- Name: COLUMN ricevuta.gestore_emittente; Type: COMMENT; Schema: webmail; Owner: webmail
--

COMMENT ON COLUMN webmail.ricevuta.gestore_emittente IS 'Coincide con il campo gestore-emittente del file daticert.xml';


--
-- TOC entry 3004 (class 0 OID 0)
-- Dependencies: 205
-- Name: COLUMN ricevuta.identificativo; Type: COMMENT; Schema: webmail; Owner: webmail
--

COMMENT ON COLUMN webmail.ricevuta.identificativo IS 'Coincide con il campo identificativo del tag dati del file daticert.xml';


--
-- TOC entry 3005 (class 0 OID 0)
-- Dependencies: 205
-- Name: COLUMN ricevuta.ricevuta; Type: COMMENT; Schema: webmail; Owner: webmail
--

COMMENT ON COLUMN webmail.ricevuta.ricevuta IS 'Coincide con il campo ricevuta del tag dati del file daticert.xml.';


--
-- TOC entry 3006 (class 0 OID 0)
-- Dependencies: 205
-- Name: COLUMN ricevuta.consegna; Type: COMMENT; Schema: webmail; Owner: webmail
--

COMMENT ON COLUMN webmail.ricevuta.consegna IS 'Coincide con il campo consegna del tag dati del file daticert.xml.';


--
-- TOC entry 3007 (class 0 OID 0)
-- Dependencies: 205
-- Name: COLUMN ricevuta.oggetto_mail; Type: COMMENT; Schema: webmail; Owner: webmail
--

COMMENT ON COLUMN webmail.ricevuta.oggetto_mail IS 'Coincide con l''oggetto della mail';


--
-- TOC entry 2912 (class 0 OID 73806)
-- Dependencies: 197
-- Data for Name: allegato_mail_in; Type: TABLE DATA; Schema: webmail; Owner: webmail
--



--
-- TOC entry 2913 (class 0 OID 73812)
-- Dependencies: 198
-- Data for Name: allegato_mail_out; Type: TABLE DATA; Schema: webmail; Owner: webmail
--



--
-- TOC entry 2914 (class 0 OID 73818)
-- Dependencies: 199
-- Data for Name: casella_postale; Type: TABLE DATA; Schema: webmail; Owner: webmail
--



--
-- TOC entry 2915 (class 0 OID 73824)
-- Dependencies: 200
-- Data for Name: config_mail_in; Type: TABLE DATA; Schema: webmail; Owner: webmail
--



--
-- TOC entry 2916 (class 0 OID 73832)
-- Dependencies: 201
-- Data for Name: config_mail_out; Type: TABLE DATA; Schema: webmail; Owner: webmail
--



--
-- TOC entry 2917 (class 0 OID 73840)
-- Dependencies: 202
-- Data for Name: email_in; Type: TABLE DATA; Schema: webmail; Owner: webmail
--



--
-- TOC entry 2918 (class 0 OID 73846)
-- Dependencies: 203
-- Data for Name: email_out; Type: TABLE DATA; Schema: webmail; Owner: webmail
--



--
-- TOC entry 2919 (class 0 OID 73852)
-- Dependencies: 204
-- Data for Name: retry_notification; Type: TABLE DATA; Schema: webmail; Owner: webmail
--



--
-- TOC entry 2920 (class 0 OID 73858)
-- Dependencies: 205
-- Data for Name: ricevuta; Type: TABLE DATA; Schema: webmail; Owner: webmail
--



--
-- TOC entry 2756 (class 2606 OID 73865)
-- Name: allegato_mail_out allegati_mail_out_pkey; Type: CONSTRAINT; Schema: webmail; Owner: webmail
--

ALTER TABLE ONLY webmail.allegato_mail_out
    ADD CONSTRAINT allegati_mail_out_pkey PRIMARY KEY (id);


--
-- TOC entry 2754 (class 2606 OID 73867)
-- Name: allegato_mail_in allegato_mail_in_pkey; Type: CONSTRAINT; Schema: webmail; Owner: webmail
--

ALTER TABLE ONLY webmail.allegato_mail_in
    ADD CONSTRAINT allegato_mail_in_pkey PRIMARY KEY (id);


--
-- TOC entry 2764 (class 2606 OID 73869)
-- Name: config_mail_in config_mail_in_pkey; Type: CONSTRAINT; Schema: webmail; Owner: webmail
--

ALTER TABLE ONLY webmail.config_mail_in
    ADD CONSTRAINT config_mail_in_pkey PRIMARY KEY (id);


--
-- TOC entry 2770 (class 2606 OID 73871)
-- Name: email_in email_in_pkey; Type: CONSTRAINT; Schema: webmail; Owner: webmail
--

ALTER TABLE ONLY webmail.email_in
    ADD CONSTRAINT email_in_pkey PRIMARY KEY (id);


--
-- TOC entry 2775 (class 2606 OID 73873)
-- Name: email_out email_out_pkey; Type: CONSTRAINT; Schema: webmail; Owner: webmail
--

ALTER TABLE ONLY webmail.email_out
    ADD CONSTRAINT email_out_pkey PRIMARY KEY (id);


--
-- TOC entry 2766 (class 2606 OID 73875)
-- Name: config_mail_in id_casella_postale_univoco_mail_in; Type: CONSTRAINT; Schema: webmail; Owner: webmail
--

ALTER TABLE ONLY webmail.config_mail_in
    ADD CONSTRAINT id_casella_postale_univoco_mail_in UNIQUE (id_casella_postale);


--
-- TOC entry 3008 (class 0 OID 0)
-- Dependencies: 2766
-- Name: CONSTRAINT id_casella_postale_univoco_mail_in ON config_mail_in; Type: COMMENT; Schema: webmail; Owner: webmail
--

COMMENT ON CONSTRAINT id_casella_postale_univoco_mail_in ON webmail.config_mail_in IS 'La relazione è 1 a 1. Rendiamo univoco l''ID della casella postale';


--
-- TOC entry 2768 (class 2606 OID 73877)
-- Name: config_mail_out id_casella_postale_univoco_mail_out; Type: CONSTRAINT; Schema: webmail; Owner: webmail
--

ALTER TABLE ONLY webmail.config_mail_out
    ADD CONSTRAINT id_casella_postale_univoco_mail_out UNIQUE (id_casella_postale);


--
-- TOC entry 2777 (class 2606 OID 73879)
-- Name: email_out id_documentale_mail_out_unique; Type: CONSTRAINT; Schema: webmail; Owner: webmail
--

ALTER TABLE ONLY webmail.email_out
    ADD CONSTRAINT id_documentale_mail_out_unique UNIQUE (id_folder_documentale);


--
-- TOC entry 2772 (class 2606 OID 73881)
-- Name: email_in id_folder_documentale_unique_mail_in; Type: CONSTRAINT; Schema: webmail; Owner: webmail
--

ALTER TABLE ONLY webmail.email_in
    ADD CONSTRAINT id_folder_documentale_unique_mail_in UNIQUE (id_folder_documentale);


--
-- TOC entry 2758 (class 2606 OID 73883)
-- Name: casella_postale id_folder_documentale_univoco; Type: CONSTRAINT; Schema: webmail; Owner: webmail
--

ALTER TABLE ONLY webmail.casella_postale
    ADD CONSTRAINT id_folder_documentale_univoco UNIQUE (id_folder_documentale);


--
-- TOC entry 3009 (class 0 OID 0)
-- Dependencies: 2758
-- Name: CONSTRAINT id_folder_documentale_univoco ON casella_postale; Type: COMMENT; Schema: webmail; Owner: webmail
--

COMMENT ON CONSTRAINT id_folder_documentale_univoco ON webmail.casella_postale IS 'Constraint sull''univocità dell''ID del folder ';


--
-- TOC entry 2760 (class 2606 OID 73885)
-- Name: casella_postale indirizzo_mail_univoco; Type: CONSTRAINT; Schema: webmail; Owner: webmail
--

ALTER TABLE ONLY webmail.casella_postale
    ADD CONSTRAINT indirizzo_mail_univoco UNIQUE (indirizzo_mail);


--
-- TOC entry 2780 (class 2606 OID 73887)
-- Name: retry_notification retry_notification_pkey; Type: CONSTRAINT; Schema: webmail; Owner: webmail
--

ALTER TABLE ONLY webmail.retry_notification
    ADD CONSTRAINT retry_notification_pkey PRIMARY KEY (id);


--
-- TOC entry 2783 (class 2606 OID 73889)
-- Name: ricevuta ricevuta_pkey; Type: CONSTRAINT; Schema: webmail; Owner: webmail
--

ALTER TABLE ONLY webmail.ricevuta
    ADD CONSTRAINT ricevuta_pkey PRIMARY KEY (id);


--
-- TOC entry 2762 (class 2606 OID 73891)
-- Name: casella_postale utenza_mail_pkey; Type: CONSTRAINT; Schema: webmail; Owner: webmail
--

ALTER TABLE ONLY webmail.casella_postale
    ADD CONSTRAINT utenza_mail_pkey PRIMARY KEY (id);


--
-- TOC entry 2773 (class 1259 OID 73892)
-- Name: idx_id_casella_postale_email_in; Type: INDEX; Schema: webmail; Owner: webmail
--

CREATE INDEX idx_id_casella_postale_email_in ON webmail.email_in USING btree (id_casella_postale);


--
-- TOC entry 2778 (class 1259 OID 73893)
-- Name: idx_id_casella_postale_email_out; Type: INDEX; Schema: webmail; Owner: webmail
--

CREATE INDEX idx_id_casella_postale_email_out ON webmail.email_out USING btree (id_casella_postale);


--
-- TOC entry 2781 (class 1259 OID 73894)
-- Name: idx_id_mail_out; Type: INDEX; Schema: webmail; Owner: webmail
--

CREATE INDEX idx_id_mail_out ON webmail.ricevuta USING btree (id_mail_out);


--
-- TOC entry 3010 (class 0 OID 0)
-- Dependencies: 2781
-- Name: INDEX idx_id_mail_out; Type: COMMENT; Schema: webmail; Owner: webmail
--

COMMENT ON INDEX webmail.idx_id_mail_out IS 'Indice id_mail_out';


--
-- TOC entry 2784 (class 2606 OID 73895)
-- Name: allegato_mail_in fk_allegato_mail_in; Type: FK CONSTRAINT; Schema: webmail; Owner: webmail
--

ALTER TABLE ONLY webmail.allegato_mail_in
    ADD CONSTRAINT fk_allegato_mail_in FOREIGN KEY (id_mail_in) REFERENCES webmail.email_in(id);


--
-- TOC entry 2785 (class 2606 OID 73900)
-- Name: allegato_mail_out fk_allegato_mail_out; Type: FK CONSTRAINT; Schema: webmail; Owner: webmail
--

ALTER TABLE ONLY webmail.allegato_mail_out
    ADD CONSTRAINT fk_allegato_mail_out FOREIGN KEY (id_mail_out) REFERENCES webmail.email_out(id);


--
-- TOC entry 2786 (class 2606 OID 73905)
-- Name: config_mail_in fk_config_mail_in_casella_postale; Type: FK CONSTRAINT; Schema: webmail; Owner: webmail
--

ALTER TABLE ONLY webmail.config_mail_in
    ADD CONSTRAINT fk_config_mail_in_casella_postale FOREIGN KEY (id_casella_postale) REFERENCES webmail.casella_postale(id);


--
-- TOC entry 2787 (class 2606 OID 73910)
-- Name: config_mail_out fk_config_mail_out_casella_postale; Type: FK CONSTRAINT; Schema: webmail; Owner: webmail
--

ALTER TABLE ONLY webmail.config_mail_out
    ADD CONSTRAINT fk_config_mail_out_casella_postale FOREIGN KEY (id_casella_postale) REFERENCES webmail.casella_postale(id);


--
-- TOC entry 3011 (class 0 OID 0)
-- Dependencies: 2787
-- Name: CONSTRAINT fk_config_mail_out_casella_postale ON config_mail_out; Type: COMMENT; Schema: webmail; Owner: webmail
--

COMMENT ON CONSTRAINT fk_config_mail_out_casella_postale ON webmail.config_mail_out IS 'La FK da config_mail_out verso casella_postale';


--
-- TOC entry 2788 (class 2606 OID 73915)
-- Name: email_in fk_emil_in_casella_postale; Type: FK CONSTRAINT; Schema: webmail; Owner: webmail
--

ALTER TABLE ONLY webmail.email_in
    ADD CONSTRAINT fk_emil_in_casella_postale FOREIGN KEY (id_casella_postale) REFERENCES webmail.casella_postale(id);


--
-- TOC entry 3012 (class 0 OID 0)
-- Dependencies: 2788
-- Name: CONSTRAINT fk_emil_in_casella_postale ON email_in; Type: COMMENT; Schema: webmail; Owner: webmail
--

COMMENT ON CONSTRAINT fk_emil_in_casella_postale ON webmail.email_in IS 'FK da email_in a casella_postale';


--
-- TOC entry 2789 (class 2606 OID 73920)
-- Name: email_out fk_mail_out_casella_postale; Type: FK CONSTRAINT; Schema: webmail; Owner: webmail
--

ALTER TABLE ONLY webmail.email_out
    ADD CONSTRAINT fk_mail_out_casella_postale FOREIGN KEY (id_casella_postale) REFERENCES webmail.casella_postale(id);


--
-- TOC entry 3013 (class 0 OID 0)
-- Dependencies: 2789
-- Name: CONSTRAINT fk_mail_out_casella_postale ON email_out; Type: COMMENT; Schema: webmail; Owner: webmail
--

COMMENT ON CONSTRAINT fk_mail_out_casella_postale ON webmail.email_out IS 'La FK tra mail out e casella_postale';


--
-- TOC entry 2790 (class 2606 OID 73925)
-- Name: ricevuta fk_ricevuta_mail_out; Type: FK CONSTRAINT; Schema: webmail; Owner: webmail
--

ALTER TABLE ONLY webmail.ricevuta
    ADD CONSTRAINT fk_ricevuta_mail_out FOREIGN KEY (id_mail_out) REFERENCES webmail.email_out(id);


--
-- TOC entry 3014 (class 0 OID 0)
-- Dependencies: 2790
-- Name: CONSTRAINT fk_ricevuta_mail_out ON ricevuta; Type: COMMENT; Schema: webmail; Owner: webmail
--

COMMENT ON CONSTRAINT fk_ricevuta_mail_out ON webmail.ricevuta IS 'FK tra ricevuta e mail_out';


-- Completed on 2020-02-24 19:29:55 CET

--
-- PostgreSQL database dump complete
--
