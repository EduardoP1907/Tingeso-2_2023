PGDMP     /                     {           tingeso2    15.3    15.3 $               0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false                       0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false                       0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false                       1262    20459    tingeso2    DATABASE     {   CREATE DATABASE tingeso2 WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'Spanish_Chile.1252';
    DROP DATABASE tingeso2;
                postgres    false            �            1259    20462 
   cuota_pago    TABLE       CREATE TABLE public.cuota_pago (
    id bigint NOT NULL,
    fecha_vencimiento date,
    monto numeric(38,2),
    estudiante_id integer NOT NULL,
    numero_cuota integer NOT NULL,
    pagada boolean NOT NULL,
    estudiante_id_estudiante integer NOT NULL
);
    DROP TABLE public.cuota_pago;
       public         heap    postgres    false            �            1259    20483 '   cuota_pago_estudiante_id_estudiante_seq    SEQUENCE     �   CREATE SEQUENCE public.cuota_pago_estudiante_id_estudiante_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 >   DROP SEQUENCE public.cuota_pago_estudiante_id_estudiante_seq;
       public          postgres    false    216                       0    0 '   cuota_pago_estudiante_id_estudiante_seq    SEQUENCE OWNED BY     s   ALTER SEQUENCE public.cuota_pago_estudiante_id_estudiante_seq OWNED BY public.cuota_pago.estudiante_id_estudiante;
          public          postgres    false    219            �            1259    20461    cuota_pago_estudiante_id_seq    SEQUENCE     �   CREATE SEQUENCE public.cuota_pago_estudiante_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 3   DROP SEQUENCE public.cuota_pago_estudiante_id_seq;
       public          postgres    false    216                       0    0    cuota_pago_estudiante_id_seq    SEQUENCE OWNED BY     ]   ALTER SEQUENCE public.cuota_pago_estudiante_id_seq OWNED BY public.cuota_pago.estudiante_id;
          public          postgres    false    215            �            1259    20460    cuota_pago_id_seq    SEQUENCE     z   CREATE SEQUENCE public.cuota_pago_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 (   DROP SEQUENCE public.cuota_pago_id_seq;
       public          postgres    false    216                       0    0    cuota_pago_id_seq    SEQUENCE OWNED BY     G   ALTER SEQUENCE public.cuota_pago_id_seq OWNED BY public.cuota_pago.id;
          public          postgres    false    214            �            1259    20470 
   estudiante    TABLE     i  CREATE TABLE public.estudiante (
    id_estudiante bigint NOT NULL,
    ano_egreso_colegio integer NOT NULL,
    apellidos character varying(255),
    fecha_nacimiento character varying(255),
    nombre_colegio character varying(255),
    nombres character varying(255),
    rut character varying(255),
    tipo_colegio_de_procedencia character varying(255)
);
    DROP TABLE public.estudiante;
       public         heap    postgres    false            �            1259    20469    estudiante_id_estudiante_seq    SEQUENCE     �   CREATE SEQUENCE public.estudiante_id_estudiante_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 3   DROP SEQUENCE public.estudiante_id_estudiante_seq;
       public          postgres    false    218                       0    0    estudiante_id_estudiante_seq    SEQUENCE OWNED BY     ]   ALTER SEQUENCE public.estudiante_id_estudiante_seq OWNED BY public.estudiante.id_estudiante;
          public          postgres    false    217            �            1259    20495    estudiante_pago    TABLE     �   CREATE TABLE public.estudiante_pago (
    id bigint NOT NULL,
    montoapagar double precision NOT NULL,
    nombre_estudiante character varying(255),
    pagado boolean NOT NULL,
    estudiante_id integer NOT NULL,
    fecha_pago date
);
 #   DROP TABLE public.estudiante_pago;
       public         heap    postgres    false            �            1259    20494 !   estudiante_pago_estudiante_id_seq    SEQUENCE     �   CREATE SEQUENCE public.estudiante_pago_estudiante_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 8   DROP SEQUENCE public.estudiante_pago_estudiante_id_seq;
       public          postgres    false    221                       0    0 !   estudiante_pago_estudiante_id_seq    SEQUENCE OWNED BY     g   ALTER SEQUENCE public.estudiante_pago_estudiante_id_seq OWNED BY public.estudiante_pago.estudiante_id;
          public          postgres    false    220            q           2604    20465    cuota_pago id    DEFAULT     n   ALTER TABLE ONLY public.cuota_pago ALTER COLUMN id SET DEFAULT nextval('public.cuota_pago_id_seq'::regclass);
 <   ALTER TABLE public.cuota_pago ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    216    214    216            r           2604    20466    cuota_pago estudiante_id    DEFAULT     �   ALTER TABLE ONLY public.cuota_pago ALTER COLUMN estudiante_id SET DEFAULT nextval('public.cuota_pago_estudiante_id_seq'::regclass);
 G   ALTER TABLE public.cuota_pago ALTER COLUMN estudiante_id DROP DEFAULT;
       public          postgres    false    215    216    216            s           2604    20484 #   cuota_pago estudiante_id_estudiante    DEFAULT     �   ALTER TABLE ONLY public.cuota_pago ALTER COLUMN estudiante_id_estudiante SET DEFAULT nextval('public.cuota_pago_estudiante_id_estudiante_seq'::regclass);
 R   ALTER TABLE public.cuota_pago ALTER COLUMN estudiante_id_estudiante DROP DEFAULT;
       public          postgres    false    219    216            t           2604    20473    estudiante id_estudiante    DEFAULT     �   ALTER TABLE ONLY public.estudiante ALTER COLUMN id_estudiante SET DEFAULT nextval('public.estudiante_id_estudiante_seq'::regclass);
 G   ALTER TABLE public.estudiante ALTER COLUMN id_estudiante DROP DEFAULT;
       public          postgres    false    217    218    218            u           2604    20498    estudiante_pago estudiante_id    DEFAULT     �   ALTER TABLE ONLY public.estudiante_pago ALTER COLUMN estudiante_id SET DEFAULT nextval('public.estudiante_pago_estudiante_id_seq'::regclass);
 L   ALTER TABLE public.estudiante_pago ALTER COLUMN estudiante_id DROP DEFAULT;
       public          postgres    false    221    220    221                      0    20462 
   cuota_pago 
   TABLE DATA           �   COPY public.cuota_pago (id, fecha_vencimiento, monto, estudiante_id, numero_cuota, pagada, estudiante_id_estudiante) FROM stdin;
    public          postgres    false    216   �,                 0    20470 
   estudiante 
   TABLE DATA           �   COPY public.estudiante (id_estudiante, ano_egreso_colegio, apellidos, fecha_nacimiento, nombre_colegio, nombres, rut, tipo_colegio_de_procedencia) FROM stdin;
    public          postgres    false    218   �,                 0    20495    estudiante_pago 
   TABLE DATA           p   COPY public.estudiante_pago (id, montoapagar, nombre_estudiante, pagado, estudiante_id, fecha_pago) FROM stdin;
    public          postgres    false    221   �.                   0    0 '   cuota_pago_estudiante_id_estudiante_seq    SEQUENCE SET     V   SELECT pg_catalog.setval('public.cuota_pago_estudiante_id_estudiante_seq', 1, false);
          public          postgres    false    219            !           0    0    cuota_pago_estudiante_id_seq    SEQUENCE SET     K   SELECT pg_catalog.setval('public.cuota_pago_estudiante_id_seq', 1, false);
          public          postgres    false    215            "           0    0    cuota_pago_id_seq    SEQUENCE SET     @   SELECT pg_catalog.setval('public.cuota_pago_id_seq', 1, false);
          public          postgres    false    214            #           0    0    estudiante_id_estudiante_seq    SEQUENCE SET     K   SELECT pg_catalog.setval('public.estudiante_id_estudiante_seq', 44, true);
          public          postgres    false    217            $           0    0 !   estudiante_pago_estudiante_id_seq    SEQUENCE SET     P   SELECT pg_catalog.setval('public.estudiante_pago_estudiante_id_seq', 1, false);
          public          postgres    false    220            w           2606    20468    cuota_pago cuota_pago_pkey 
   CONSTRAINT     X   ALTER TABLE ONLY public.cuota_pago
    ADD CONSTRAINT cuota_pago_pkey PRIMARY KEY (id);
 D   ALTER TABLE ONLY public.cuota_pago DROP CONSTRAINT cuota_pago_pkey;
       public            postgres    false    216            {           2606    20500 $   estudiante_pago estudiante_pago_pkey 
   CONSTRAINT     b   ALTER TABLE ONLY public.estudiante_pago
    ADD CONSTRAINT estudiante_pago_pkey PRIMARY KEY (id);
 N   ALTER TABLE ONLY public.estudiante_pago DROP CONSTRAINT estudiante_pago_pkey;
       public            postgres    false    221            y           2606    20477    estudiante estudiante_pkey 
   CONSTRAINT     c   ALTER TABLE ONLY public.estudiante
    ADD CONSTRAINT estudiante_pkey PRIMARY KEY (id_estudiante);
 D   ALTER TABLE ONLY public.estudiante DROP CONSTRAINT estudiante_pkey;
       public            postgres    false    218            |           2606    20478 &   cuota_pago fk3nca2u9x0xifw2tnys8e4r9j1    FK CONSTRAINT     �   ALTER TABLE ONLY public.cuota_pago
    ADD CONSTRAINT fk3nca2u9x0xifw2tnys8e4r9j1 FOREIGN KEY (estudiante_id) REFERENCES public.estudiante(id_estudiante);
 P   ALTER TABLE ONLY public.cuota_pago DROP CONSTRAINT fk3nca2u9x0xifw2tnys8e4r9j1;
       public          postgres    false    216    3193    218            ~           2606    20501 +   estudiante_pago fk9thidxtifrgjym9j1d5n75j7e    FK CONSTRAINT     �   ALTER TABLE ONLY public.estudiante_pago
    ADD CONSTRAINT fk9thidxtifrgjym9j1d5n75j7e FOREIGN KEY (estudiante_id) REFERENCES public.estudiante(id_estudiante);
 U   ALTER TABLE ONLY public.estudiante_pago DROP CONSTRAINT fk9thidxtifrgjym9j1d5n75j7e;
       public          postgres    false    221    218    3193            }           2606    20489 &   cuota_pago fk9w7n3dr26qff688mv0bwxyxtx    FK CONSTRAINT     �   ALTER TABLE ONLY public.cuota_pago
    ADD CONSTRAINT fk9w7n3dr26qff688mv0bwxyxtx FOREIGN KEY (estudiante_id_estudiante) REFERENCES public.estudiante(id_estudiante);
 P   ALTER TABLE ONLY public.cuota_pago DROP CONSTRAINT fk9w7n3dr26qff688mv0bwxyxtx;
       public          postgres    false    218    3193    216                  x������ � �         �  x���AN�0E��)��#{�$���UB��Dq�B��*�ވ%g��'�J%ʆ�L���<���т�>~���C۽UB;�*�v"��C��������&pJFK��9Iw����ת[Pd)��DT���ˎaE)U.��7���v���5�PY�lV$�f�&��7�Sc4��#��Q��1䲥�g ;��8�g�R)߇q_�� N�H��`�m��X�EFq�h|:\>8���?�O�f�Z�/�V3��vێ��_?[�߄(�g�d��8�@P4�B��P,��C(�1WC��w	�.A�%��trw�Es�[|3�5nX�n�~&�����'>i��z"��IN�N����O��	��ю�.RJe	�8�(EC(��B(9�q�@�5w�]q�Bܵw-�]q�)[,�&]A            x������ � �     