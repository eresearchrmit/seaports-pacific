using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace CCIMT.Presentation
{
    public class TimberTemplate
    {
        
        #region Legends
                
        #endregion

        #region Table1 Material-Zone-Kwood

        public bool isTable1Update { get; set; }
        public decimal valTbl1HWZAC1 { get; set; }
        public decimal valTbl1HWZAC2 { get; set; }
        public decimal valTbl1HWZAC3 { get; set; }
        public decimal valTbl1HWZAC4 { get; set; }
        public decimal valTbl1SPSUZAC { get; set; }
        public decimal valTbl1SPSCRZAC1 { get; set; }
        public decimal valTbl1SPSCRZAC2 { get; set; }
        public decimal valTbl1SPSCCZAC1 { get; set; }
        public decimal valTbl1SPSCCZAC2 { get; set; }
        public decimal valTbl1SPSCCZAC3 { get; set; }
        public decimal valTbl1SPSCCZAC4 { get; set; }
        public decimal valTbl1SPSDBTZAC { get; set; }
        public decimal valTbl1SPHUZAC { get; set; }
        public decimal valTbl1SPHCRZAC1 { get; set; }
        public decimal valTbl1SPHCRZAC2 { get; set; }
        public decimal valTbl1SPHCCZAC1 { get; set; }
        public decimal valTbl1SPHCCZAC2 { get; set; }
        public decimal valTbl1SPHCCZAC3 { get; set; }
        public decimal valTbl1SPHDBTZAC { get; set; }
        public decimal valTbl1HWZDG1 { get; set; }
        public decimal valTbl1HWZDG2 { get; set; }
        public decimal valTbl1HWZDG3 { get; set; }
        public decimal valTbl1HWZDG4 { get; set; }
        public decimal valTbl1SPSUZDG { get; set; }
        public decimal valTbl1SPSCRZDG1 { get; set; }
        public decimal valTbl1SPSCRZDG2 { get; set; }
        public decimal valTbl1SPSCCZDG1 { get; set; }
        public decimal valTbl1SPSCCZDG2 { get; set; }
        public decimal valTbl1SPSCCZDG3 { get; set; }
        public decimal valTbl1SPSCCZDG4 { get; set; }
        public decimal valTbl1SPSDBTZDG { get; set; }
        public decimal valTbl1SPHUZDG { get; set; }
        public decimal valTbl1SPHCRZDG1 { get; set; }
        public decimal valTbl1SPHCRZDG2 { get; set; }
        public decimal valTbl1SPHCCZDG1 { get; set; }
        public decimal valTbl1SPHCCZDG2 { get; set; }
        public decimal valTbl1SPHCCZDG3 { get; set; }
        public decimal valTbl1SPHDBTZDG { get; set; }

        #endregion

        #region Table2 Class-Salinity-Ksal

        public bool isTable2Update { get; set; }
        public decimal valTbl2Class1ZAD { get; set; }
        public decimal valTbl2Class1ZEG { get; set; }
        public decimal valTbl2Class2ZAD { get; set; }
        public decimal valTbl2Class2ZEG { get; set; }
        public decimal valTbl2Class3ZAD { get; set; }
        public decimal valTbl2Class3ZEG { get; set; }

        #endregion

        #region Table3 Water-Zone-Kshelter

        public bool isTable3Update { get; set; }
        public decimal valTbl3Exp1 { get; set; }
        public decimal valTbl3Exp2 { get; set; }

        #endregion

        #region Table4 Maintenance-Measure-Kprotect

        public bool isTable4Update { get; set; }
        public decimal valTbl4Mtn1 { get; set; }
        public decimal valTbl4Mtn2 { get; set; }

        #endregion

        #region Table5 Contact-Kcontact

        public bool isTable5Update { get; set; }
        public decimal valTbl5Cnt1 { get; set; }
        public decimal valTbl5Cnt2 { get; set; }

        #endregion

        #region Table6 Knot-Presence-Knot

        public bool isTable6Update { get; set; }
        public decimal valTbl6Knt1 { get; set; }
        public decimal valTbl6Knt2 { get; set; }
        public decimal valTbl6Knt3 { get; set; }
         
        #endregion

        public IList<TimberAsset> TimberAssetList { get; set; }  

    }

    [Serializable]
    public class TimberAsset
    {

        public string AssetCode { get; set; }
        public int YearInstalled { get; set; }
        public string Description { get; set; }
        public string Type { get; set; }
        public TimberHW HW { get; set; }
        public TimberSW SW { get; set; }
        public int Do { get; set; }
        public int DSap { get; set; }
        public int DReplace { get; set; }
        public TimberMaintenance Maintenance { get; set; }
        public TimberContact Contact { get; set; }
        public TimberKnot Knot { get; set; }
        public TimberZone Zone { get; set; }
        public TimberExposure Exposure { get; set; }
        public bool IsEdited { get; set; }

    }

}