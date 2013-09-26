using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace CCIMT.Presentation
{
    public class SteelTemplate
    {

        #region Number of Rain Days

        public bool isNoOfRDUpdated { get; set; }
        public int NORDA { get; set; }
        public int NORDB { get; set; }
        public int NORDC { get; set; }
        public int NORDD { get; set; }
        public int NORDE { get; set; }

        #endregion

        #region Zone Factor

        public bool isZoneFactorUpdated { get; set; }
        public int ZFAC1 { get; set; }
        public int ZFAO1 { get; set; }
        public int ZFAC2 { get; set; }
        public int ZFAO2 { get; set; }
        public int ZFAC3 { get; set; }
        public int ZFAO3 { get; set; }

        #endregion

        #region Coastal Exposure

        public bool isCoastalExposureUpdated { get; set; }
        public decimal CEBCExp1 { get; set; }
        public decimal CEBCExp2 { get; set; }
        public decimal CEBCExp3 { get; set; }
        public decimal CEBCExp4 { get; set; }

        #endregion

        #region Site Classification

        public bool isSiteClassificationUpdated { get; set; }
        public decimal SCSite1 { get; set; }
        public decimal SCSite2 { get; set; }
        public decimal SCSite3 { get; set; }
        public decimal SCSite4 { get; set; }

        #endregion

        public IList<SteelAsset> SteelAssetList { get; set; }        
        
    }

    [Serializable]
    public class SteelAsset
    {

        public string AssetCode { get; set; }
        public int YearBuilt { get; set; }
        public string Description { get; set; }
        public Zone Zone { get; set; }
        public SteelHazardZone HazardZone { get; set; }
        public SteelCoastalZone CoastalZone { get; set; }
        public SteelCoastalClass CoastClass { get; set; }
        public SteelSiteClassification SiteClass { get; set; }
        public decimal DistFromCost { get; set; }
        public bool IsEdited { get; set; }

    }
}