using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace CCIMT.Presentation
{

    #region Common Enums

    public enum TemplateType
    {
        None = 0,
        ConcreteTempate = 1,
        SteelTemplate = 2,
        TimberTemplate = 3
    }

    public enum Location
    {
        None = 0,
        Gladstone = 1,
        PortKembla = 2,
        Sydney = 3
    }

    public enum CarbonEmissionScenario
    {
        None = 0,
        Base = 1,
        A1FI = 2,
        A1B = 3
    }

    public enum FutureClimateModel
    {
        None = 0,
        MostLikely = 1,
        HotterDrier = 2,
        WetterCooler = 3
    }

    public enum TableTypeConcrete
    {
        None = 0,
        CarbonationCorrosionRatesAt20C = 1,
        ChlorideCorrosionRatesAt20C = 2,
        CO2DiffusionCoefficient = 3,
        ActivationEnergy = 4,
        SurfaceChlorideConcentration = 5,
        ChlorideDiffusionCoefficient = 6,
        Legend = 7
    }

    public enum TableTypeTimber
    {
        None = 0,
        Table1MaterialKwood = 1,
        Table2ClassSalinityKsal = 2,
        Table3WaterZoneKshelter = 3,
        Table4MaintenanceMeasureKprotext = 4,
        Table5ContractKcontract = 5,
        Table6KnotPresenceKnot = 6
    }

    public enum TableTypeSteel
    {
        None = 0,
        NoOfRainDays = 1,
        ZoneFactor = 2,
        CoastalExposure = 3,
        SiteClassification = 4
    }

    #endregion

    #region Common Template Enums

    public enum Zone
    {
        None = 0,
        atmospheric = 1,
        tidal = 2,
        splash = 3,
        submerged = 4
    }

    #endregion

    #region Concrete Enums

    public enum Exposure
    {
        None = 0,
        B1 = 1,
        B2 = 2,
        C1 = 3,
        C2 = 4
    }

    public enum Carbonation
    {
        None = 0,
        CB1 = 1,
        CB2 = 2,
        CB3 = 3,
        CB4 = 4
    }

    public enum Chloride
    {
        None = 0,
        CL1 = 1,
        CL2 = 2,
        CL3 = 3,
        CL4 = 4,
        CL5 = 5
    }

    #endregion

    #region Timber Enums

    public enum TimberHW
    {
        None = 0,
        HW1 = 1,
        HW2 = 2,
        HW3 = 3,
        HW4 = 4
    }

    public enum TimberSW
    {
        None = 0,
        SPSU = 1,
        SPSCR1 = 2,
        SPSCR2 = 3,
        SPSCC1 = 4,
        SPSCC2 = 5,
        SPSCC3 = 6,
        SPSCC4 = 7,
        SPSDBT = 8,
        SPHU = 9,
        SPHCR1 = 10,
        SPHCR2 = 11,
        SPHCC1 = 12,
        SPHCC2 = 13,
        SPHCC3 = 14,
        SPHDBT = 15
    }

    public enum TimberMaintenance
    {
        None = 0,
        MTN1 = 1,
        MTN2 = 2
    }

    public enum TimberContact
    {
        None = 0,
        CNT1 = 1,
        CNT2 = 2
    }

    public enum TimberKnot
    {
        None = 0,
        KNT1 = 1,
        KNT2 = 2,
        KNT3 = 3
    }

    public enum TimberZone
    {
        None = 0,
        A = 1,
        B = 2,
        C = 3,
        D = 4,
        E = 5,
        F = 6,
        G = 7
    }

    public enum TimberExposure
    {
        None = 0,
        EXP1 = 1,
        EXP2 = 2
    }

    #endregion

    #region Steel Enums

    public enum SteelHazardZone
    {
        None = 0,
        A = 1,
        B = 2,
        C = 3,
        D = 4,
        E = 5
    }

    public enum SteelCoastalZone
    {
        None = 0,
        C1 = 1,
        C2 = 2,
        C3 = 3
    }

    public enum SteelCoastalClass
    {
        None = 0,
        EXP1 = 1,
        EXP2 = 2,
        EXP3 = 3,
        EXP4 = 4
    }

    public enum SteelSiteClassification
    {
        None = 0,
        SITE1 = 1,
        SITE2 = 2,
        SITE3 = 3,
        SITE4 = 4
    }

    #endregion

}