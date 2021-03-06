USE [master]
GO
/****** Object:  Database [ClubDeportivo]    Script Date: 9/11/2020 20:39:04 ******/
CREATE DATABASE [ClubDeportivo]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'ClubDeportivo', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL14.SQLEXPRESS\MSSQL\DATA\ClubDeportivo.mdf' , SIZE = 8192KB , MAXSIZE = UNLIMITED, FILEGROWTH = 65536KB )
 LOG ON 
( NAME = N'ClubDeportivo_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL14.SQLEXPRESS\MSSQL\DATA\ClubDeportivo_log.ldf' , SIZE = 8192KB , MAXSIZE = 2048GB , FILEGROWTH = 65536KB )
GO
ALTER DATABASE [ClubDeportivo] SET COMPATIBILITY_LEVEL = 140
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [ClubDeportivo].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [ClubDeportivo] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [ClubDeportivo] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [ClubDeportivo] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [ClubDeportivo] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [ClubDeportivo] SET ARITHABORT OFF 
GO
ALTER DATABASE [ClubDeportivo] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [ClubDeportivo] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [ClubDeportivo] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [ClubDeportivo] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [ClubDeportivo] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [ClubDeportivo] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [ClubDeportivo] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [ClubDeportivo] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [ClubDeportivo] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [ClubDeportivo] SET  DISABLE_BROKER 
GO
ALTER DATABASE [ClubDeportivo] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [ClubDeportivo] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [ClubDeportivo] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [ClubDeportivo] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [ClubDeportivo] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [ClubDeportivo] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [ClubDeportivo] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [ClubDeportivo] SET RECOVERY SIMPLE 
GO
ALTER DATABASE [ClubDeportivo] SET  MULTI_USER 
GO
ALTER DATABASE [ClubDeportivo] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [ClubDeportivo] SET DB_CHAINING OFF 
GO
ALTER DATABASE [ClubDeportivo] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [ClubDeportivo] SET TARGET_RECOVERY_TIME = 60 SECONDS 
GO
ALTER DATABASE [ClubDeportivo] SET DELAYED_DURABILITY = DISABLED 
GO
ALTER DATABASE [ClubDeportivo] SET QUERY_STORE = OFF
GO
USE [ClubDeportivo]
GO
ALTER DATABASE SCOPED CONFIGURATION SET IDENTITY_CACHE = ON;
GO
ALTER DATABASE SCOPED CONFIGURATION SET LEGACY_CARDINALITY_ESTIMATION = OFF;
GO
ALTER DATABASE SCOPED CONFIGURATION FOR SECONDARY SET LEGACY_CARDINALITY_ESTIMATION = PRIMARY;
GO
ALTER DATABASE SCOPED CONFIGURATION SET MAXDOP = 0;
GO
ALTER DATABASE SCOPED CONFIGURATION FOR SECONDARY SET MAXDOP = PRIMARY;
GO
ALTER DATABASE SCOPED CONFIGURATION SET PARAMETER_SNIFFING = ON;
GO
ALTER DATABASE SCOPED CONFIGURATION FOR SECONDARY SET PARAMETER_SNIFFING = PRIMARY;
GO
ALTER DATABASE SCOPED CONFIGURATION SET QUERY_OPTIMIZER_HOTFIXES = OFF;
GO
ALTER DATABASE SCOPED CONFIGURATION FOR SECONDARY SET QUERY_OPTIMIZER_HOTFIXES = PRIMARY;
GO
USE [ClubDeportivo]
GO
/****** Object:  Table [dbo].[Actividades]    Script Date: 9/11/2020 20:39:04 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Actividades](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[nombre] [varchar](50) NOT NULL,
	[descripcion] [varchar](max) NULL,
	[costoMensual] [decimal](18, 0) NULL,
	[eliminado] [bit] NULL,
	[fechaEliminacion] [date] NULL,
 CONSTRAINT [PK_Actividades] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[DetallePagos]    Script Date: 9/11/2020 20:39:05 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[DetallePagos](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[idActividad] [int] NOT NULL,
	[idPago] [int] NOT NULL,
 CONSTRAINT [PK_DetallePagos] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Inscripciones]    Script Date: 9/11/2020 20:39:05 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Inscripciones](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[idActividad] [int] NOT NULL,
	[idSocio] [int] NOT NULL,
	[fecha] [date] NULL,
 CONSTRAINT [PK_ActividadesSocios] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Pagos]    Script Date: 9/11/2020 20:39:05 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Pagos](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[referencia] [varchar](max) NULL,
	[idSocio] [int] NOT NULL,
	[monto] [decimal](18, 0) NOT NULL,
	[fecha] [date] NOT NULL,
 CONSTRAINT [PK_Pagos] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Publicaciones]    Script Date: 9/11/2020 20:39:05 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Publicaciones](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[titulo] [varchar](50) NOT NULL,
	[contenido] [varchar](max) NULL,
	[urlImagen] [varchar](max) NULL,
	[idActividad] [int] NOT NULL,
	[fecha] [date] NOT NULL,
	[activa] [bit] NOT NULL,
	[eliminado] [bit] NULL,
	[fechaEliminacion] [date] NULL,
 CONSTRAINT [PK_Publicaciones] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Socios]    Script Date: 9/11/2020 20:39:05 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Socios](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[nombre] [varchar](50) NOT NULL,
	[apellido] [varchar](50) NOT NULL,
	[dni] [int] NOT NULL,
	[fechaNacimiento] [date] NOT NULL,
	[genero] [varchar](50) NOT NULL,
	[telefono] [varchar](50) NULL,
	[eliminado] [bit] NULL,
	[fechaEliminacion] [date] NULL,
 CONSTRAINT [PK_Socios] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET IDENTITY_INSERT [dbo].[Actividades] ON 
GO
INSERT [dbo].[Actividades] ([id], [nombre], [descripcion], [costoMensual], [eliminado], [fechaEliminacion]) VALUES (3, N'Voley', N'Para todas las edades. Tambien contamos con cancha de arena ', CAST(900 AS Decimal(18, 0)), NULL, NULL)
GO
INSERT [dbo].[Actividades] ([id], [nombre], [descripcion], [costoMensual], [eliminado], [fechaEliminacion]) VALUES (4, N'Basquet', N'Se puede relizar en cualquiera de las 3 canchas que cuenta el club. Hay una Liga para la tercera edad', CAST(800 AS Decimal(18, 0)), NULL, NULL)
GO
INSERT [dbo].[Actividades] ([id], [nombre], [descripcion], [costoMensual], [eliminado], [fechaEliminacion]) VALUES (7, N'Futbol', N'Para todas las edades. Liga de menores incluida', CAST(700 AS Decimal(18, 0)), NULL, NULL)
GO
INSERT [dbo].[Actividades] ([id], [nombre], [descripcion], [costoMensual], [eliminado], [fechaEliminacion]) VALUES (8, N'Natacion', N'Proximamente en 2021', CAST(1500 AS Decimal(18, 0)), NULL, NULL)
GO
INSERT [dbo].[Actividades] ([id], [nombre], [descripcion], [costoMensual], [eliminado], [fechaEliminacion]) VALUES (9, N'Ping Pong', N'Contamos con 2 mesas en la recepcion del club', CAST(400 AS Decimal(18, 0)), 1, CAST(N'2020-11-09' AS Date))
GO
SET IDENTITY_INSERT [dbo].[Actividades] OFF
GO
SET IDENTITY_INSERT [dbo].[DetallePagos] ON 
GO
INSERT [dbo].[DetallePagos] ([id], [idActividad], [idPago]) VALUES (1, 4, 9)
GO
INSERT [dbo].[DetallePagos] ([id], [idActividad], [idPago]) VALUES (2, 7, 9)
GO
INSERT [dbo].[DetallePagos] ([id], [idActividad], [idPago]) VALUES (3, 3, 9)
GO
INSERT [dbo].[DetallePagos] ([id], [idActividad], [idPago]) VALUES (4, 4, 10)
GO
INSERT [dbo].[DetallePagos] ([id], [idActividad], [idPago]) VALUES (5, 7, 10)
GO
INSERT [dbo].[DetallePagos] ([id], [idActividad], [idPago]) VALUES (6, 4, 11)
GO
INSERT [dbo].[DetallePagos] ([id], [idActividad], [idPago]) VALUES (7, 7, 11)
GO
INSERT [dbo].[DetallePagos] ([id], [idActividad], [idPago]) VALUES (8, 3, 11)
GO
SET IDENTITY_INSERT [dbo].[DetallePagos] OFF
GO
SET IDENTITY_INSERT [dbo].[Inscripciones] ON 
GO
INSERT [dbo].[Inscripciones] ([id], [idActividad], [idSocio], [fecha]) VALUES (2, 4, 1, CAST(N'2020-03-07' AS Date))
GO
INSERT [dbo].[Inscripciones] ([id], [idActividad], [idSocio], [fecha]) VALUES (8, 7, 1, CAST(N'2020-11-09' AS Date))
GO
INSERT [dbo].[Inscripciones] ([id], [idActividad], [idSocio], [fecha]) VALUES (9, 3, 1, CAST(N'2020-11-09' AS Date))
GO
INSERT [dbo].[Inscripciones] ([id], [idActividad], [idSocio], [fecha]) VALUES (10, 4, 2, CAST(N'2020-11-09' AS Date))
GO
INSERT [dbo].[Inscripciones] ([id], [idActividad], [idSocio], [fecha]) VALUES (11, 7, 2, CAST(N'2020-11-09' AS Date))
GO
INSERT [dbo].[Inscripciones] ([id], [idActividad], [idSocio], [fecha]) VALUES (12, 7, 5, CAST(N'2020-11-09' AS Date))
GO
SET IDENTITY_INSERT [dbo].[Inscripciones] OFF
GO
SET IDENTITY_INSERT [dbo].[Pagos] ON 
GO
INSERT [dbo].[Pagos] ([id], [referencia], [idSocio], [monto], [fecha]) VALUES (9, N'Cuota correspondiente a Noviembre del 2020', 1, CAST(2400 AS Decimal(18, 0)), CAST(N'2020-11-09' AS Date))
GO
INSERT [dbo].[Pagos] ([id], [referencia], [idSocio], [monto], [fecha]) VALUES (10, N'Cuota correspondiente a Diciembre del 2020', 2, CAST(1500 AS Decimal(18, 0)), CAST(N'2020-11-09' AS Date))
GO
INSERT [dbo].[Pagos] ([id], [referencia], [idSocio], [monto], [fecha]) VALUES (11, N'Cuota correspondiente a Octubre del 2020', 1, CAST(2400 AS Decimal(18, 0)), CAST(N'2020-10-08' AS Date))
GO
SET IDENTITY_INSERT [dbo].[Pagos] OFF
GO
SET IDENTITY_INSERT [dbo].[Publicaciones] ON 
GO
INSERT [dbo].[Publicaciones] ([id], [titulo], [contenido], [urlImagen], [idActividad], [fecha], [activa], [eliminado], [fechaEliminacion]) VALUES (6, N'Amistoso con Victoria', N'El Juvenil del Club  Apolo de Futbol  Femenino se midió ante Club Acciòn  en un amistoso que se disputó en el Predio de Coquimbito, los dirigidos por Marcos Basteany sumaron una victoria y una igualdad en la mañana del sábado.
En horas de la mañana de este sábado, el Juvenil  volvió a disputar un nuevo amistoso de preparación para la vuelta a la actividad oficial, y se midió contra Acción, elenco que milita en el Torneo Regional . En un encuentro, donde las jóvenes dueñas de casa manejó el balón y tuvo las mejores ocasiones, aunque no pudo concretar ninguna en los primeros 20’. En la segunda etapa fue un compromiso trabado y friccionado, sin embargo, en los últimos minutos, el Equipo de Basteany logrò conseguir la victoria.
', N'https://image.shutterstock.com/image-photo/youth-soccer-team-training-boys-260nw-1840898590.jpg', 7, CAST(N'2020-11-25' AS Date), 1, NULL, NULL)
GO
INSERT [dbo].[Publicaciones] ([id], [titulo], [contenido], [urlImagen], [idActividad], [fecha], [activa], [eliminado], [fechaEliminacion]) VALUES (7, N'Inauguracion de la pileta del Club', N'Concretando otro de sus proyectos, el Club Apolo de Còrdoba inauguró en la tarde del lunes, la primera Pileta del Club, con el objeto de brindar màs servicios a los vecinos de la localidad y especialmente para los más chicos que, en el aspecto deportivo y recreativo, podrán desarrollar plenamente sus derechos.
Con la presencia de autoridades municipales, representantes de instituciones intermedias y vecinos de la localidad, el acto inauguración comenzó pasadas las 18 hs.
El natatorio, que se encuentra emplazado en el interior del Club Deportivo Apolo, cuenta con 15 metros de largo por 5 de ancho, vereda perimetral atérmica y solarium, es de suma importancia a la hora de llevar a cabo las colonias de verano; asimismo, estimula la natación y promueve los beneficios que implica la práctica de la misma.
', N'https://image.shutterstock.com/image-photo/empty-olympic-swimming-pool-clear-260nw-444907216.jpg', 8, CAST(N'2020-11-21' AS Date), 1, NULL, NULL)
GO
INSERT [dbo].[Publicaciones] ([id], [titulo], [contenido], [urlImagen], [idActividad], [fecha], [activa], [eliminado], [fechaEliminacion]) VALUES (8, N'Con el corazòn por delante', N'Al final fue victoria de Apolo  ante Astros 87-79 para ponerse por delante en las finales de la Zona Oeste. Gamarra fue la figura del club con 28 puntos, a la vez que Piñero aportó 5 rebotes y Mancuello 6 tantos.
Astros empezó un poco mejor el juego, dominando a partir de la relevancia de Ayón, que se sentía cómodo. Lo mismo ocurrió en el segundo periodo, en el que Apoleros parecía sentirse mejor, situación que se confirmó en el tercer cuarto, cuando los de Cordoba supieron controlarlo y luego dominarlo para llevarse el triunfo en el inicio de las finales de la Zona  (87-79). 
', N'https://image.shutterstock.com/image-photo/beach-basketball-court-recreation-center-260nw-1760476580.jpg', 4, CAST(N'2020-10-15' AS Date), 1, NULL, NULL)
GO
INSERT [dbo].[Publicaciones] ([id], [titulo], [contenido], [urlImagen], [idActividad], [fecha], [activa], [eliminado], [fechaEliminacion]) VALUES (9, N'Sigue la pelea por el campeonato', N'El  equipo  masculino de vóleibol no pudo con Atléticos Juniors de Córdoba, bicampeón , y perdió 3-1 (27-29, 25-17, 25-18 y 26-24) esta tarde noche , en un partido válido por la cuarta fecha de la Copa Inter Còrdoba.
El equipo dirigido por Marcelo Marquez acumuló así su segunda derrota en el torneo.
En las dos primeras fechas, Apolo Deportivo ganó a Villa Carlos Paz, el defensor del título , por 3 a 2, y luego superó a Chalecito por 3 a 0.
Mañana, desde las 7.20 , se enfrentarán al equipo local con transmisión en directo de la via streaming
', N'https://image.shutterstock.com/image-photo/rio-de-janeirobrazilapril-10-2007-600w-206885083.jpg', 3, CAST(N'2020-10-30' AS Date), 1, NULL, NULL)
GO
INSERT [dbo].[Publicaciones] ([id], [titulo], [contenido], [urlImagen], [idActividad], [fecha], [activa], [eliminado], [fechaEliminacion]) VALUES (10, N'Se cancela el Ping Pong ', N'Por inconvenientes con el equipamiento del club ya no se podra realizar Ping pong como actividad. Mas consultas acercarse a recepcion', N'', 9, CAST(N'2020-07-23' AS Date), 0, NULL, NULL)
GO
SET IDENTITY_INSERT [dbo].[Publicaciones] OFF
GO
SET IDENTITY_INSERT [dbo].[Socios] ON 
GO
INSERT [dbo].[Socios] ([id], [nombre], [apellido], [dni], [fechaNacimiento], [genero], [telefono], [eliminado], [fechaEliminacion]) VALUES (1, N'Juan', N'Perez', 30123456, CAST(N'1986-05-05' AS Date), N'Masculino', N'3655228913', NULL, NULL)
GO
INSERT [dbo].[Socios] ([id], [nombre], [apellido], [dni], [fechaNacimiento], [genero], [telefono], [eliminado], [fechaEliminacion]) VALUES (2, N'Carolina', N'Blanco', 342333332, CAST(N'1987-11-04' AS Date), N'Femenino', N'', NULL, NULL)
GO
INSERT [dbo].[Socios] ([id], [nombre], [apellido], [dni], [fechaNacimiento], [genero], [telefono], [eliminado], [fechaEliminacion]) VALUES (3, N'Jose', N'Eliminado', 111111111, CAST(N'2020-10-14' AS Date), N'Otro', N'', 1, CAST(N'2020-11-08' AS Date))
GO
INSERT [dbo].[Socios] ([id], [nombre], [apellido], [dni], [fechaNacimiento], [genero], [telefono], [eliminado], [fechaEliminacion]) VALUES (4, N'Roberta', N'Rodriguez', 20456785, CAST(N'1999-02-12' AS Date), N'Femenino', N'3657890887', NULL, NULL)
GO
INSERT [dbo].[Socios] ([id], [nombre], [apellido], [dni], [fechaNacimiento], [genero], [telefono], [eliminado], [fechaEliminacion]) VALUES (5, N'Duarte', N'Roca', 2687890, CAST(N'1994-08-04' AS Date), N'Otro', N'', NULL, NULL)
GO
SET IDENTITY_INSERT [dbo].[Socios] OFF
GO
ALTER TABLE [dbo].[DetallePagos]  WITH CHECK ADD  CONSTRAINT [FK_DetallePagos_Actividades] FOREIGN KEY([idActividad])
REFERENCES [dbo].[Actividades] ([id])
GO
ALTER TABLE [dbo].[DetallePagos] CHECK CONSTRAINT [FK_DetallePagos_Actividades]
GO
ALTER TABLE [dbo].[DetallePagos]  WITH CHECK ADD  CONSTRAINT [FK_DetallePagos_Pagos] FOREIGN KEY([idPago])
REFERENCES [dbo].[Pagos] ([id])
GO
ALTER TABLE [dbo].[DetallePagos] CHECK CONSTRAINT [FK_DetallePagos_Pagos]
GO
ALTER TABLE [dbo].[Inscripciones]  WITH CHECK ADD  CONSTRAINT [FK_ActividadesSocios_Actividades] FOREIGN KEY([idActividad])
REFERENCES [dbo].[Actividades] ([id])
GO
ALTER TABLE [dbo].[Inscripciones] CHECK CONSTRAINT [FK_ActividadesSocios_Actividades]
GO
ALTER TABLE [dbo].[Inscripciones]  WITH CHECK ADD  CONSTRAINT [FK_ActividadesSocios_Socios] FOREIGN KEY([idSocio])
REFERENCES [dbo].[Socios] ([id])
GO
ALTER TABLE [dbo].[Inscripciones] CHECK CONSTRAINT [FK_ActividadesSocios_Socios]
GO
ALTER TABLE [dbo].[Pagos]  WITH CHECK ADD  CONSTRAINT [FK_Pagos_Socios] FOREIGN KEY([idSocio])
REFERENCES [dbo].[Socios] ([id])
GO
ALTER TABLE [dbo].[Pagos] CHECK CONSTRAINT [FK_Pagos_Socios]
GO
ALTER TABLE [dbo].[Publicaciones]  WITH CHECK ADD  CONSTRAINT [FK_Publicaciones_Actividades] FOREIGN KEY([idActividad])
REFERENCES [dbo].[Actividades] ([id])
GO
ALTER TABLE [dbo].[Publicaciones] CHECK CONSTRAINT [FK_Publicaciones_Actividades]
GO
USE [master]
GO
ALTER DATABASE [ClubDeportivo] SET  READ_WRITE 
GO
