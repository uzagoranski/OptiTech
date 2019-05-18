
/*  SLABO
1) View za modelId in makerId, ta View se v bazi nahaja pod: OptiTech -> Views -> dbo.nekaj
*/
select carModel, carMakerId  from optitech.reg.carModels where carModelId IN (select * from OptiTech.dbo.nekaj);


/* SLABO
2) Povpraševalni stavek za avte, ki so v bazi.
Pred tem je treba kreirati view "nekaj", ki je v 1)
*/
SELECT * FROM
              (
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
                      SELECT carModelId,countryId,carMaker
                      FROM optitech.reg.carModels
                          LEFT JOIN optitech.reg.CarMakers
                              ON optitech.reg.CarMakers.carMakerId = optitech.reg.CarModels.carMakerId)
                      AS drugi
                      ON prvi.carModelId = drugi.carModelId
    ;

/* UPORABNO
3) Povpraševalni stavek, za avte, kjer dobimo tudi ime avta.
*/

SELECT Vehicles.carModelId, carMakerId,carModel,vehicleSubtypeId,countryID,carMaker,vehicleId,vin,vehicleTitle,regNumber,carModelYear,fuelTypeId,drivenWheelsId,engineSize,enginePower,dateRegStart,dateRegEnd
FROM
    (
        SELECT prvi.carModelId, carMakerId, carModel, vehicleSubtypeId, status, countryID, carMaker
        FROM
            (
                SELECT *
                FROM optitech.reg.carModels
                WHERE carModelId
                          IN (
                          SELECT carModelId
                          FROM OptiTech.biz.Vehicles
                          WHERE vehicleId
                                    IN  (
                                    SELECT DISTINCT vehicleId
                                    FROM optitech.tlm.DriveData))) as prvi
                LEFT JOIN (
                SELECT carModelId,countryId,carMaker
                FROM optitech.reg.carModels                                                                                                                                                        LEFT JOIN optitech.reg.CarMakers on optitech.reg.CarMakers.carMakerId = optitech.reg.CarModels.carMakerId) as drugi
                          ON prvi.carModelId = drugi.carModelId
    ) AS tabela
        LEFT JOIN  OptiTech.biz.Vehicles
                   ON tabela.carModelId = optitech.biz.vehicles.carModelId
WHERE engineSize != 0
  AND countryID != 'XY'
  AND vehicleId != '1357';
