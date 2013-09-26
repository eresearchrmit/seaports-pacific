using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace CCIMT.Presentation
{
    [Serializable]
    public class ConcreteTemplate
    {
        
        #region Legends

        public bool isLegendsUpdated { get; set; }
        public decimal legendKUrban { get; set; }
        public decimal legendNM { get; set; }

        #endregion

        #region Carbonation corrosion rates at 20°C

        public bool isCarbCR20Updated { get; set; }
        public decimal CB1Mean { get; set; }
        public decimal CB1SD { get; set; }
        public decimal CB2Mean { get; set; }
        public decimal CB2SD { get; set; }
        public decimal CB3Mean { get; set; }
        public decimal CB3SD { get; set; }
        public decimal CB4Mean { get; set; }
        public decimal CB4SD { get; set; }

        #endregion

        #region Chloride corrosion rates at 20°C

        public bool isChlCR20Updated { get; set; }
        public decimal CL1Mean { get; set; }
        public decimal CL1SD { get; set; }
        public decimal CL2Mean { get; set; }
        public decimal CL2SD { get; set; }
        public decimal CL3Mean { get; set; }
        public decimal CL3SD { get; set; }
        public decimal CL4Mean { get; set; }
        public decimal CL4SD { get; set; }
        public decimal CL5Mean { get; set; }
        public decimal CL5SD { get; set; }

        #endregion

        #region CO2 diffusion coefficient

        public bool isCO2DCUpdated { get; set; }
        public decimal coVal30D1 { get; set; }
        public decimal coVal30ND { get; set; }
        public decimal coVal35D1 { get; set; }
        public decimal coVal35ND { get; set; }
        public decimal coVal40D1 { get; set; }
        public decimal coVal40ND { get; set; }
        public decimal coVal45D1 { get; set; }
        public decimal coVal45ND { get; set; }
        public decimal coVal50D1 { get; set; }
        public decimal coVal50ND { get; set; }
        public decimal coVal55D1 { get; set; }
        public decimal coVal55ND { get; set; }
        public decimal coVal60D1 { get; set; }
        public decimal coVal60ND { get; set; }
        public decimal coVal65D1 { get; set; }
        public decimal coVal65ND { get; set; }

        #endregion

        #region Activation energy

        public bool isAEUpdated { get; set; }
        public decimal aeVal3E { get; set; }
        public decimal aeVal4E { get; set; }
        public decimal aeVal5E { get; set; }
        public decimal aeVal6E { get; set; }
        public decimal aeVal7E { get; set; }

        #endregion

        #region Surface chloride concentration

        public bool isSCCUpdated { get; set; }
        public decimal sccTidalSplashMean { get; set; }
        public decimal sccAtmNearCoastMean { get; set; }
        public decimal sccAtmFarCoastMean { get; set; }

        #endregion

        #region Chloride diffusion coefficient

        public bool isCDCUpdated { get; set; }
        public decimal cdcVal40FC { get; set; }
        public decimal cdcVal40DC { get; set; }
        public decimal cdcVal45FC { get; set; }
        public decimal cdcVal45DC { get; set; }
        public decimal cdcVal50FC { get; set; }
        public decimal cdcVal50DC { get; set; }
        public decimal cdcVal55FC { get; set; }
        public decimal cdcVal55DC { get; set; }

        #endregion

        public IList<ConcreteAsset> ConcreteAssetList { get; set; }        
        
    }

    [Serializable]
    public class ConcreteAsset
    {

        public string AssetCode { get; set; }
        public int YearBuilt { get; set; }
        public string Description { get; set; }
        public Zone Zone { get; set; }
        public decimal DistFromCost { get; set; }
        public Exposure Exposure { get; set; }
        public Carbonation Carbonation { get; set; }
        public Chloride Chloride { get; set; }
        public string P { get; set; }
        public int Cover { get; set; }
        public int DMember { get; set; }
        public int F1C { get; set; }
        public decimal WC { get; set; }
        public int Ce { get; set; }
        public int DBar { get; set; }
        public decimal XcTo { get; set; }
        public bool IsEdited { get; set; }

    }

}
