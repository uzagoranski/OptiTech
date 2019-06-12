/*
tlm.DriveData -> pridobiš preko propertyja vehicleId id za tabelo biz.Vehicles
biz.Vehicles -> pridobiš preko propertyja carModelId id za tabelo reg.CarModels
reg.CarModels -> pridobiš preko propertyja carMakerId id za tabelo reg.CarMakers

prikažeš ZNAMKO (reg.CarMakers), MODEL (reg.CarModels), POVPREČJA RPM, VSS, PORABE (tlm.DriveData), torej je biz.Vehicles samo prehodna tabela.
*/


/*  SLABO
1) View za modelId in makerId, ta View se v bazi nahaja pod: OptiTech -> Views -> dbo.nekaj
*/
-- select carModel, carMakerId
-- from optitech.reg.carModels
-- WHERE carModelId IN (select * from OptiTech.dbo.nekaj);


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

/*
3)
Povpraševalni stavek, za avte, kjer dobimo tudi ime avta.
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
Podatki kako je bil avto vožen.
*/
SELECT AVG(DrvDist) as povprecna_Prevozena_Razdalja,
       AVG(DrvTime) as povprecen_Cas_Voznje,
       AVG(RpmAvg)  as povprecen_RPM_Povprecna,
       AVG(RpmMax)  as povprecen_RPM_Max,
       avg(VssMax)  as povprecna_hitrost_max
FROM OptiTech.tlm.DriveData
WHERE vehicleId = 217
  AND DrvTime != 0
  AND DrvDist > 400;

/*
5)
Update, za imena avtov
*/
UPDATE optitech.biz.Vehicles
SET vehicleTitle='318d'
WHERE vehicleId = 217
UPDATE optitech.biz.Vehicles
SET vehicleTitle='320d Touring'
WHERE vehicleId = 224
UPDATE optitech.biz.Vehicles
SET vehicleTitle='330d'
WHERE vehicleId = 1384
UPDATE optitech.biz.Vehicles
SET vehicleTitle='320d'
WHERE vehicleId = 1386
UPDATE optitech.biz.Vehicles
SET vehicleTitle='GLE 350d'
WHERE vehicleId = 1369
UPDATE optitech.biz.Vehicles
SET vehicleTitle='Cooper S'
WHERE vehicleId = 1364
UPDATE optitech.biz.Vehicles
SET vehicleTitle='Megane'
WHERE vehicleId = 1380
UPDATE optitech.biz.Vehicles
SET vehicleTitle='Superb'
WHERE vehicleId = 225
UPDATE optitech.biz.Vehicles
SET vehicleTitle='S60'
WHERE vehicleId = 1309
UPDATE optitech.biz.Vehicles
SET vehicleTitle='V50'
WHERE vehicleId = 218
UPDATE optitech.biz.Vehicles
SET vehicleTitle='Golf GTI'
WHERE vehicleId = 1368
UPDATE optitech.biz.Vehicles
SET vehicleTitle='C3'
WHERE vehicleId = 1360
UPDATE optitech.biz.Vehicles
SET vehicleTitle='Mondeo'
WHERE vehicleId = 1363
UPDATE optitech.biz.Vehicles
SET vehicleTitle='Octavia RS'
WHERE vehicleId = 1361
UPDATE optitech.biz.Vehicles
SET vehicleTitle='Superb'
WHERE vehicleId = 245
UPDATE optitech.biz.Vehicles
SET vehicleTitle='Superb'
WHERE vehicleId = 1346
UPDATE optitech.biz.Vehicles
SET vehicleTitle='Golf'
WHERE vehicleId = 1358
UPDATE optitech.biz.Vehicles
SET vehicleTitle='Passat'
WHERE vehicleId = 1387

/*
6)
Select FuelType
*/
Select *
from OptiTech.reg.FuelTypes;

/*
7)
Create CarImage za .png slike vozil
*/
CREATE TABLE Optitech.[tlm].[CarImage]
(
    CarImageID int NOT NULL IDENTITY PRIMARY KEY,
    VehicleID  int NOT NULL,
    ImageLink  varchar(255)
);

/*
8)
Insert linkov v CarImage
*/
INSERT INTO Optitech.[tlm].[CarImage] (VehicleID, ImageLink)
VALUES (217, 'https://i.imgur.com/uA1z5yj.png'),
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

/*
9)
Update registrskih tablic
*/
UPDATE OptiTech.biz.Vehicles
SET regNumber = 'GO 28-841'
WHERE vehicleId = 1384;
UPDATE OptiTech.biz.Vehicles
SET regNumber = 'MB 98-CS0'
WHERE vehicleId = 1369;
UPDATE OptiTech.biz.Vehicles
SET regNumber = 'MB 64-8EA'
WHERE vehicleId = 1364;
UPDATE OptiTech.biz.Vehicles
SET regNumber = 'SG UV-768'
WHERE vehicleId = 224;
UPDATE OptiTech.biz.Vehicles
SET regNumber = 'LJ 55-A84'
WHERE vehicleId = 1380;
UPDATE OptiTech.biz.Vehicles
SET regNumber = 'LJ 89-7PA'
WHERE vehicleId = 225;
UPDATE OptiTech.biz.Vehicles
SET regNumber = 'MB UJ-735'
WHERE vehicleId = 218;
UPDATE OptiTech.biz.Vehicles
SET regNumber = 'MB UZ-998'
WHERE vehicleId = 1368;
UPDATE OptiTech.biz.Vehicles
SET regNumber = 'KP 97-AS3'
WHERE vehicleId = 1360;
UPDATE OptiTech.biz.Vehicles
SET regNumber = 'KK J1-85F'
WHERE vehicleId = 1363;
UPDATE OptiTech.biz.Vehicles
SET regNumber = 'NM H4-0H6'
WHERE vehicleId = 1361;
UPDATE OptiTech.biz.Vehicles
SET regNumber = 'MS HA-442'
WHERE vehicleId = 245;
UPDATE OptiTech.biz.Vehicles
SET regNumber = 'LJ 37-UGF'
WHERE vehicleId = 1346;
UPDATE OptiTech.biz.Vehicles
SET regNumber = 'LJ 17-2HZ'
WHERE vehicleId = 1358;
UPDATE OptiTech.biz.Vehicles
SET regNumber = 'MB 1P-H6V'
WHERE vehicleId = 1387;

UPDATE Optitech.biz.Vehicles
SET vin = 'WBANE53516B99215X'
WHERE vehicleId = 224;
UPDATE Optitech.biz.Vehicles
SET vin = 'YV4CZ982081434533'
WHERE vehicleId = 1309;
UPDATE Optitech.biz.Vehicles
SET enginePower = '75'
WHERE vehicleId = 1360;

/*
10)
Posodobitev slik v bazi.
*/
UPDATE optitech.tlm.CarImage
SET ImageLink='https://i.imgur.com/PinBdZf.png'
WHERE VehicleID = 217;
UPDATE optitech.tlm.CarImage
SET ImageLink='https://i.imgur.com/vT1hJ7h.png'
WHERE VehicleID = 224;
UPDATE optitech.tlm.CarImage
SET ImageLink='https://i.imgur.com/4FYRV7A.png'
WHERE VehicleID = 1384;
UPDATE optitech.tlm.CarImage
SET ImageLink='https://i.imgur.com/3BveKdH.png'
WHERE VehicleID = 1386;
UPDATE optitech.tlm.CarImage
SET ImageLink='https://i.imgur.com/i1KaZ0S.png'
WHERE VehicleID = 1369;
UPDATE optitech.tlm.CarImage
SET ImageLink='https://i.imgur.com/DuBKfmi.png'
WHERE VehicleID = 1364;
UPDATE optitech.tlm.CarImage
SET ImageLink='https://i.imgur.com/KQ1IIcK.png'
WHERE VehicleID = 1380;
UPDATE optitech.tlm.CarImage
SET ImageLink='https://i.imgur.com/zFzsacI.png'
WHERE VehicleID = 225;
UPDATE optitech.tlm.CarImage
SET ImageLink='https://i.imgur.com/v0aKVel.png'
WHERE VehicleID = 1309;
UPDATE optitech.tlm.CarImage
SET ImageLink='https://i.imgur.com/0qNAtWL.png'
WHERE VehicleID = 218;
UPDATE optitech.tlm.CarImage
SET ImageLink='https://i.imgur.com/AhHhnYs.png'
WHERE VehicleID = 1368;
UPDATE optitech.tlm.CarImage
SET ImageLink='https://i.imgur.com/I5vU9YU.png'
WHERE VehicleID = 1360;
UPDATE optitech.tlm.CarImage
SET ImageLink='https://i.imgur.com/d7NtCb5.png'
WHERE VehicleID = 1363;
UPDATE optitech.tlm.CarImage
SET ImageLink='https://i.imgur.com/Vs93QP0.png'
WHERE VehicleID = 1361;
UPDATE optitech.tlm.CarImage
SET ImageLink='https://i.imgur.com/zFzsacI.png'
WHERE VehicleID = 245;
UPDATE optitech.tlm.CarImage
SET ImageLink='https://i.imgur.com/zFzsacI.png'
WHERE VehicleID = 1346;
UPDATE optitech.tlm.CarImage
SET ImageLink='https://i.imgur.com/dalClVb.png'
WHERE VehicleID = 1358;
UPDATE optitech.tlm.CarImage
SET ImageLink='https://i.imgur.com/CcT8OJ1.png'
WHERE VehicleID = 1387;


/*
11)
Izbor dtcDescription
*/
SELECT *
FROM optitech.reg.DtcCodes
WHERE dtc = 'P0101';

/*
12)
Zaznane napake, za avte ki jih imamo
*/
SELECT vehicleId, userID, p.dtc, dtcCode, dtcDescription
FROM OptiTech.tlm.DtcInfo as p
         LEFT JOIN (select * from optitech.reg.dtccodes) as d ON p.dtc = d.dtc
WHERE vehicleId IN (select distinct vehicleId
                    from OPTITECH.TLM.DRIVEDATA
                    WHERE vehicleId IN (
                        SELECT vehicleId
                        FROM (SELECT prvi.carModelId,
                                     carMakerId,
                                     carModel,
                                     vehicleSubtypeId,
                                     status,
                                     countryID,
                                     carMaker
                              FROM (SELECT *
                                    FROM optitech.reg.carModels
                                    WHERE carModelId IN (SELECT carModelId
                                                         FROM OptiTech.biz.Vehicles
                                                         WHERE vehicleId
                                                                   IN (
                                                                   SELECT DISTINCT vehicleId
                                                                   FROM optitech.tlm.DriveData))) as prvi
                                       LEFT JOIN (
                                  SELECT carModelId, countryId, carMaker
                                  FROM optitech.reg.carModels
                                           LEFT JOIN optitech.reg.CarMakers
                                                     ON optitech.reg.CarMakers.carMakerId =
                                                        optitech.reg.CarModels.carMakerId
                              ) AS drugi
                                                 ON prvi.carModelId = drugi.carModelId
                             ) AS tabela
                                 RIGHT JOIN OptiTech.biz.Vehicles
                                            ON tabela.carModelId = optitech.biz.vehicles.carModelId


                        WHERE engineSize != 0
                          AND countryID != 'XY'
                          AND vehicleId != '1357'
                    )
);


/*
 13)
 Izbor podatkov za graf, na nekem časovnem intervalu. Potrebno je spremeniti vehicleId IN datuma!
 */
SELECT rpmAvg, dateMsg
FROM OptiTech.tlm.DriveData
WHERE vehicleId = 1380
  AND dateMsg > Convert(datetime, '2019-05-03')
  AND dateMsg < Convert(datetime, '2019-05-06');

/*
 14)
 Izbor podatkov za graf, na nekem časovnem intervalu. Potrebno je spremeniti vehicleId IN datuma!
 */
SELECT VssAvg, dateMsg
FROM OptiTech.tlm.DriveData
WHERE vehicleId = 1380
  AND dateMsg > Convert(datetime, '2019-05-03')
  AND dateMsg < Convert(datetime, '2019-05-06');


/*
 15)
 Pridobitev max in min vrednosti za izračun.
 */
SELECT MAX(AvgRpmMax)          AS rpmMaxMAX,
       MIN(AvgRpmMax)          AS rpmMaxMIN,

       MAX(avgRpmAvg)          AS rpmAvgMAX,
       MIN(avgRpmAvg)          AS rpmAvgMIN,

       MAX(avgVssMax)          AS vssMaxMAX,
       MIN(avgVssMax)          AS vssMaxMIN,

       MAX(vssAvg)             AS vssAvgMAX,
       MIN(vssAvg)             AS vssAvgMIN,

       MAX(avgDrvDist)         AS drvDistMAX,
       MIN(avgDrvDist)         AS drvDistMIN,

       MAX(avgDrvTime)         AS drvTimeMAX,
       MIN(avgDrvTime)         AS drvTimeMIN,

       MAX(avgDrvStartStopCnt) AS drvStartStopMAX,
       MIN(avgDrvStartStopCnt) AS drvStartStopMIN,

       MAX(avgFuelConsAvg)     AS FuelConsMAX,
       MIN(avgFuelConsAvg)     AS FuelConsMIN
from (SELECT vehicleId,
             COUNT(vehicleId)     as numberOfInputs,
             avg(rpmMax)          AS AvgRpmMax,
             MAX(RpmMax)          as maxRpmMax,
             AVG(RpmAvg)          as avgRpmAvg,
             AVG(vssMax)          as avgVssMax,
             AVG(VssAvg)          as vssAvg,
             Avg(DrvDist)         as avgDrvDist,
             avg(DrvTime)         as avgDrvTime,
             AVG(DrvStartStopCnt) AS avgDrvStartStopCnt,
             AVG(FuelConsAvg)     as avgFuelConsAvg
      from OptiTech.tlm.DriveData
      WHERE VssMax != 0
        AND VssAvg != 0
        AND DrvDist != 0
        AND DrvTime != 0
        AND FuelConsAvg != 0
      GROUP BY vehicleId) AS prva
WHERE prva.numberOfInputs != 1

/*
 16)
 Pridobitev podatkov o določenem avtu za izračun scora.
 */
SELECT COUNT(vehicleId),
       AVG(rpmMax)          AS AvgRpmMax,
       AVG(RpmAvg)          AS avgRpmAvg,
       AVG(vssMax)          AS avgVssMax,
       AVG(VssAvg)          as vssAvg,
       Avg(DrvDist)         as avgDrvDist,
       avg(DrvTime)         as avgDrvTime,
       AVG(DrvStartStopCnt) AS avgDrvStartStopCnt,
       AVG(FuelConsAvg)     as avgFuelConsAvg
FROM OptiTech.tlm.DriveData
WHERE VssMax != 0
  AND VssAvg != 0
  AND DrvDist != 0
  AND DrvTime != 0
  AND FuelConsAvg != 0
  AND VehicleId = 1368;

/*
17)
 Pridobitev Maksimalne hitrosti s katero je avto vozil.
 */
select MAX(VssMax)
from Optitech.tlm.DriveData
where vehicleId = 217;


/*
 18)
 Napake na določenem avtomobilu
 */
SELECT dateMsg, userID, DtcInfo.dtc, dtcCode, timeLog, dtcDescription
FROM optitech.tlm.dtcinfo
         LEFT JOIN (SELECT dtcDescription, dtc FROM optitech.reg.DtcCodes) AS prvi
                   ON optitech.tlm.dtcinfo.dtc = prvi.dtc
WHERE vehicleId != 0
  AND dtcDescription != 'null'
  AND vehicleId = 217
ORDER BY dateMsg


/*
 19)
 Podatki za AI, za avte ki jih imamo v bazi
 */
SELECT *
FROM (SELECT Optitech.tlm.DtcInfo.vehicleId, DrvTime, DrvDist, VssAvg, RpmAvg, DrvAbsDistOdo, dtc
      FROM Optitech.tlm.DtcInfo
               RIGHT JOIN (select max(VehicleId)     as vehicleId,
                                  MAX(DrvAbsDistOdo) as DrvAbsDistOdo,
                                  SUM(DrvTime)       as DrvTime,
                                  SUM(DrvDist)       as DrvDist,
                                  AVG(RpmAvg)        as RpmAvg,
                                  AVG(VssAvg)        as VssAvg,
                                  sessionId
                           from OptiTech.tlm.DriveData
                           WHERE DrvAbsDistOdo != 0
                           GROUP BY sessionId)
          AS prva ON Optitech.tlm.DtcInfo.sessionId = prva.sessionId
      where Optitech.tlm.DtcInfo.vehicleId IN (SELECT vehicleId
                                               FROM (
                                                        SELECT prvi.carModelId,
                                                               carMakerId,
                                                               carModel,
                                                               vehicleSubtypeId,
                                                               status,
                                                               countryID,
                                                               carMaker
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
                                                                               ON optitech.reg.CarMakers.carMakerId =
                                                                                  optitech.reg.CarModels.carMakerId
                                                        ) AS drugi
                                                                           ON prvi.carModelId = drugi.carModelId
                                                    ) AS tabela
                                                        LEFT JOIN OptiTech.biz.Vehicles
                                                                  ON tabela.carModelId = optitech.biz.vehicles.carModelId
                                               WHERE engineSize != 0
                                                 AND countryID != 'XY'
                                                 AND vehicleId != '1357')) AS p
         LEFT JOIN OptiTech.reg.DtcCodes ON p.dtc = optitech.reg.DtcCodes.dtc
WHERE Optitech.reg.DtcCodes.dtc != 'null';

/*
 20)
 Podatki za AI, za vse avte, ki obstajajo v bazi
 */
SELECT DrvTime, DrvDist,VssAvg, RpmAvg,DrvAbsDistOdo, dtcDescription
FROM (SELECT Optitech.tlm.DtcInfo.vehicleId, DrvTime, DrvDist, VssAvg, RpmAvg, DrvAbsDistOdo, dtc
      FROM Optitech.tlm.DtcInfo
               RIGHT JOIN (select max(VehicleId)     as vehicleId,
                                  MAX(DrvAbsDistOdo) as DrvAbsDistOdo,
                                  SUM(DrvTime)       as DrvTime,
                                  SUM(DrvDist)       as DrvDist,
                                  AVG(RpmAvg)        as RpmAvg,
                                  AVG(VssAvg)        as VssAvg,
                                  sessionId
                           from OptiTech.tlm.DriveData
                           WHERE DrvAbsDistOdo != 0
                           GROUP BY sessionId)
          AS prva ON Optitech.tlm.DtcInfo.sessionId = prva.sessionId)
         AS p
         LEFT JOIN OptiTech.reg.DtcCodes ON p.dtc = optitech.reg.DtcCodes.dtc
WHERE Optitech.reg.DtcCodes.dtc != 'null';

/*
 21)
 Napake na vozilu, grupirane po datumu in različnimi napakami na ta datum.
    Npr. če sta se zgodili dve različni napaki na enak datum, sta obe prikazani, če sta se pa zgodili dve enaki
    napaki na enak datum, je prikazana zgolj ena.
 */
SELECT *
FROM (SELECT Convert(varchar(11), dateMsg, 23) AS dateMsg, optitech.tlm.DtcInfo.dtc, DtcCode, dtcDescription
      FROM optitech.tlm.dtcinfo
               LEFT JOIN (SELECT dtcDescription, dtc FROM optitech.reg.DtcCodes) AS prvi
                         ON optitech.tlm.dtcinfo.dtc = prvi.dtc
      WHERE vehicleId != 0
        AND dtcDescription != 'null'
        AND vehicleId = 217
        AND dateMsg > Convert(datetime, '2017-01-01')
        AND dateMsg < Convert(datetime, '2027-01-01')
      GROUP BY DtcInfo.dtc, Convert(varchar(11), dateMsg, 23), DtcCode, DtcInfo.dtc, dtcDescription) AS p
ORDER BY dateMsg DESC

/*
 22)
 Podatki o trip data speed chart za izbrano vozilo, na izbran datum
 */
select Convert(varchar(8), dateMsg, 108), VssAvg
from OptiTech.tlm.driveData
where vehicleId = 217
  AND Convert(varchar(11), dateMsg, 23) = '2017-06-21'
  AND VssAvg != 0
ORDER BY dateMsg;


    /*
     23)
     Izbor datuma kdaj se je začela prva napaka in kdaj se je končala.
     */
SELECT DtcCode, dtcDescription, dtc, min(dateMsg) as min, max(dateMsg) as max
FROM (
         SELECT *
         FROM (SELECT Convert(varchar(11), dateMsg, 23) AS dateMsg, optitech.tlm.DtcInfo.dtc, DtcCode, dtcDescription
               FROM optitech.tlm.dtcinfo
                        LEFT JOIN (SELECT dtcDescription, dtc FROM optitech.reg.DtcCodes) AS prvi
                                  ON optitech.tlm.dtcinfo.dtc = prvi.dtc
               WHERE vehicleId != 0
                 AND dtcDescription != 'null'
                 AND vehicleId = 217
                 AND dateMsg > Convert(datetime, '2017-01-01')
                 AND dateMsg < Convert(datetime, '2027-01-01')
               GROUP BY DtcInfo.dtc, Convert(varchar(11), dateMsg, 23), DtcCode, DtcInfo.dtc, dtcDescription) AS p
     ) AS tabela
GROUP BY DtcCode, dtcDescription, dtc;
--Single line
SELECT dtcDescription, min(dateMsg) as firstOccurrence, max(dateMsg) as lastOccurrence FROM (SELECT * FROM (SELECT Convert(varchar(11), dateMsg, 23) AS dateMsg, optitech.tlm.DtcInfo.dtc, DtcCode, dtcDescription FROM optitech.tlm.dtcinfo LEFT JOIN (SELECT dtcDescription, dtc FROM optitech.reg.DtcCodes) AS prvi ON optitech.tlm.dtcinfo.dtc = prvi.dtc WHERE vehicleId != 0 AND dtcDescription != 'null' AND vehicleId = 217 AND dateMsg > Convert(datetime, '2017-01-01') AND dateMsg < Convert(datetime, '2027-01-01') GROUP BY DtcInfo.dtc, Convert(varchar(11), dateMsg, 23), DtcCode, DtcInfo.dtc, dtcDescription) AS p) AS tabela GROUP BY DtcCode, dtcDescription, dtc;

/*
 24)
 TripData podatki za eno vožnjo.
 */
select Convert(varchar(11), dateMsg, 23),
       MIN(dateMsg) as firstTime,
       MAX(dateMsg) as lastTime,
       MAX(userID) as userID,
       MAX(DrvAbsDistOdo) as lastDistance,
       MIN(DrvAbsDistOdo) as firstDistance,
       AVG(ScorePPAL) as scorePPAL,
       AVG(ScoreRPOL) as scoreRPOL,
       AVG(ScoreRVAR) as scoreRVAR,
       AVG(ScoreTotal) as scoreTotal,
       AVG(RpmAvg) as rpmAvg,
       MAX(RpmMax) as rpmMax,
       AVG(VssAvg) as vssAvg,
       MAX(VssMax) as vssMax
from OptiTech.tlm.driveData
where vehicleId = 217
    AND Convert(varchar(11), dateMsg, 23) = '2017-06-21'
GROUP BY Convert(varchar(11), dateMsg, 23);

