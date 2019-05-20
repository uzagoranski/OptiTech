/*
tlm.DriveData -> pridobiš preko propertyja vehicleId id za tabelo biz.Vehicles
biz.Vehicles -> pridobiš preko propertyja carModelId id za tabelo reg.CarModels
reg.CarModels -> pridobiš preko propertyja carMakerId id za tabelo reg.CarMakers

prikažeš ZNAMKO (reg.CarMakers), MODEL (reg.CarModels), POVPREČJA RPM, VSS, PORABE (tlm.DriveData), torej je biz.Vehicles samo prehodna tabela.
*/


/*  SLABO
1) View za modelId in makerId, ta View se v bazi nahaja pod: OptiTech -> Views -> dbo.nekaj
*/
select carModel, carMakerId
from optitech.reg.carModels
where carModelId IN (select * from OptiTech.dbo.nekaj);


/* SLABO
2) Povpraševalni stavek za avte, ki so v bazi.
Pred tem je treba kreirati view "nekaj", ki je v 1)
*/
SELECT *
FROM (
         SELECT *
         FROM optitech.reg.carModels
         WHERE carModelId IN
               (
                   SELECT carModelId
                   FROM OptiTech.biz.Vehicles
                   WHERE vehicleId IN
                         (
                             SELECT DISTINCT vehicleId
                             FROM optitech.tlm.DriveData
                         )
               )
     ) AS prvi
         LEFT JOIN
     (
         SELECT carModelId, countryId, carMaker
         FROM optitech.reg.carModels
                  LEFT JOIN optitech.reg.CarMakers
                            ON optitech.reg.CarMakers.carMakerId = optitech.reg.CarModels.carMakerId)
         AS drugi
     ON prvi.carModelId = drugi.carModelId;

/* UPORABNO
3) Povpraševalni stavek, za avte, kjer dobimo tudi ime avta.
*/
SELECT Vehicles.carModelId,
       carMakerId,
       carModel,
       vehicleSubtypeId,
       countryID,
       carMaker,
       vehicleId,
       vin,
       vehicleTitle,
       regNumber,
       carModelYear,
       fuelTypeId,
       drivenWheelsId,
       engineSize,
       enginePower,
       dateRegStart,
       dateRegEnd
FROM (
         SELECT prvi.carModelId, carMakerId, carModel, vehicleSubtypeId, status, countryID, carMaker
         FROM (
                  SELECT *
                  FROM optitech.reg.carModels
                  WHERE carModelId
                            IN (
                            SELECT carModelId
                            FROM OptiTech.biz.Vehicles
                            WHERE vehicleId
                                      IN (
                                      SELECT DISTINCT vehicleId
                                      FROM optitech.tlm.DriveData))) as prvi
                  LEFT JOIN (
             SELECT carModelId, countryId, carMaker
             FROM optitech.reg.carModels
                      LEFT JOIN optitech.reg.CarMakers
                                ON optitech.reg.CarMakers.carMakerId = optitech.reg.CarModels.carMakerId
         ) AS drugi
                            ON prvi.carModelId = drugi.carModelId
     ) AS tabela
         LEFT JOIN OptiTech.biz.Vehicles
                   ON tabela.carModelId = optitech.biz.vehicles.carModelId
WHERE engineSize != 0
  AND countryID != 'XY'
  AND vehicleId != '1357';

/*
4)
*/


/*
5) Update, za imena avtov
*/
UPDATE optitech.biz.Vehicles SET vehicleTitle='318d' where vehicleId = 217
UPDATE optitech.biz.Vehicles SET vehicleTitle='320d Touring' where vehicleId = 224
UPDATE optitech.biz.Vehicles SET vehicleTitle='330d' where vehicleId = 1384
UPDATE optitech.biz.Vehicles SET vehicleTitle='320d' where vehicleId = 1386
UPDATE optitech.biz.Vehicles SET vehicleTitle='GLE 350d' where vehicleId = 1369
UPDATE optitech.biz.Vehicles SET vehicleTitle='Cooper S' where vehicleId = 1364
UPDATE optitech.biz.Vehicles SET vehicleTitle='Megane' where vehicleId = 1380
UPDATE optitech.biz.Vehicles SET vehicleTitle='Superb' where vehicleId = 225
UPDATE optitech.biz.Vehicles SET vehicleTitle='S60' where vehicleId = 1309
UPDATE optitech.biz.Vehicles SET vehicleTitle='V50' where vehicleId = 218
UPDATE optitech.biz.Vehicles SET vehicleTitle='Golf GTI' where vehicleId = 1368
UPDATE optitech.biz.Vehicles SET vehicleTitle='C3' where vehicleId = 1360
UPDATE optitech.biz.Vehicles SET vehicleTitle='Mondeo' where vehicleId = 1363
UPDATE optitech.biz.Vehicles SET vehicleTitle='Octavia RS' where vehicleId = 1361
UPDATE optitech.biz.Vehicles SET vehicleTitle='Superb' where vehicleId = 245
UPDATE optitech.biz.Vehicles SET vehicleTitle='Superb' where vehicleId = 1346
UPDATE optitech.biz.Vehicles SET vehicleTitle='Golf' where vehicleId = 1358
UPDATE optitech.biz.Vehicles SET vehicleTitle='Passat' where vehicleId = 1387

/*
6)
Select FuelType
*/
 Select * from OptiTech.reg.FuelTypes;

/*
7)
Create CarImage za .png slike vozil
*/
 CREATE TABLE Optitech.[tlm].[CarImage] (
    CarImageID int NOT NULL IDENTITY PRIMARY KEY,
    VehicleID int NOT NULL,
    ImageLink varchar(255)
);

/*
8)
Insert linkov v CarImage
*/
INSERT INTO Optitech.[tlm].[CarImage] (VehicleID, ImageLink) VALUES
(217, 'https://i.imgur.com/uA1z5yj.png'),
(224, 'https://i.imgur.com/ojMbZFQ.png'),
(1384, 'https://i.imgur.com/fZeAdw6.png'),
(1386, 'https://i.imgur.com/X8UyLYa.png'),
(1369, 'https://i.imgur.com/ZJCxvSy.png'),
(1364, 'https://i.imgur.com/Ofn4rdC.png'),
(1380, 'https://i.imgur.com/hjfAP6D.png'),
(225, 'https://i.imgur.com/zfOPCgH.png'),
(1309, 'https://i.imgur.com/xrgKTPh.png'),
(218, 'https://i.imgur.com/G8h167J.png'),
(1368, 'https://i.imgur.com/vFYW2U0.png'),
(1360, 'https://i.imgur.com/TTxOPFp.png'),
(1363, 'https://i.imgur.com/srrzmnJ.png'),
(1361, 'https://i.imgur.com/GDYRqy1.png'),
(245, 'https://i.imgur.com/zfOPCgH.png'),
(1346, 'https://i.imgur.com/zfOPCgH.png'),
(1358, 'https://i.imgur.com/ZAzuis3.png'),
(1387, 'https://i.imgur.com/DsD8AwZ.png');
