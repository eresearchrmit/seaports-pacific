using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace CCIMT.Presentation
{
    public class BasePage : System.Web.UI.Page
    {

        #region Public Properties

        public CurrentSelection UserSelection 
        {
            get {
                if (Session["CurrentSelection"] != null)
                    return Session["CurrentSelection"] as CurrentSelection;
                else
                    return CommonMethods.DefaultCurrentSelection();

            }
            set { Session["CurrentSelection"] = value; }
        }

        public ConcreteTemplate CurrentConcreteTemplate
        {
            get
            {
                ConcreteTemplate template = new ConcreteTemplate();

                if (Session["CurrentTemplate"] == null)
                {
                    Templates allTemplates = (Templates)Application["AllTemplates"];

                    if (UserSelection.SelectedLocation == Location.Gladstone)
                        template = CommonMethods.CreateNewConcreteTemplate((ConcreteTemplate)allTemplates.GladstoneConcrete);
                    else if (UserSelection.SelectedLocation == Location.PortKembla)
                        template = CommonMethods.CreateNewConcreteTemplate((ConcreteTemplate)allTemplates.PortKemblaConcrete);
                    else if (UserSelection.SelectedLocation == Location.Sydney)
                        template = CommonMethods.CreateNewConcreteTemplate((ConcreteTemplate)allTemplates.SydneyConcrete);

                    Session["CurrentTemplate"] = template;
                }
                else
                {
                    if ((Session["CurrentTemplate"]).GetType() == typeof(TimberTemplate) ||
                        (Session["CurrentTemplate"]).GetType() == typeof(SteelTemplate))
                    {
                        Session["CurrentTemplate"] = null;
                        template = CurrentConcreteTemplate;
                    }
                    else
                    {
                        template = (ConcreteTemplate)(Session["CurrentTemplate"]);
                    }
                }
                return template;
            }
            set
            {
                Session["CurrentTemplate"] = value;
            }
        }

        public SteelTemplate CurrentSteelTemplate
        {
            get
            {
                SteelTemplate template = new SteelTemplate();

                if (Session["CurrentTemplate"] == null)
                {
                    Templates allTemplates = (Templates)Application["AllTemplates"];

                    if (UserSelection.SelectedLocation == Location.Gladstone)
                        template = CommonMethods.CreateNewSteelTemplate((SteelTemplate)allTemplates.GladstoneSteel);
                    else if (UserSelection.SelectedLocation == Location.PortKembla)
                        template = CommonMethods.CreateNewSteelTemplate((SteelTemplate)allTemplates.PortKemblaSteel);
                    else if (UserSelection.SelectedLocation == Location.Sydney)
                        template = CommonMethods.CreateNewSteelTemplate((SteelTemplate)allTemplates.SydneySteel);

                    Session["CurrentTemplate"] = template;
                }
                else
                {
                    if ((Session["CurrentTemplate"]).GetType() == typeof(ConcreteTemplate) ||
                        (Session["CurrentTemplate"]).GetType() == typeof(TimberTemplate))
                    {
                        Session["CurrentTemplate"] = null;
                        template = CurrentSteelTemplate;
                    }
                    else
                    {
                        template = (SteelTemplate)(Session["CurrentTemplate"]);
                    }
                }
                return template;
            }
            set
            {
                Session["CurrentTemplate"] = value;
            }
        }

        public TimberTemplate CurrentTimberTemplate
        {
            get
            {
                TimberTemplate template = new TimberTemplate();

                if (Session["CurrentTemplate"] == null)
                {
                    Templates allTemplates = (Templates)Application["AllTemplates"];

                    if (UserSelection.SelectedLocation == Location.Gladstone)
                        template = CommonMethods.CreateNewTimberTemplate((TimberTemplate)allTemplates.GladstoneTimber);
                    else if (UserSelection.SelectedLocation == Location.PortKembla)
                        template = CommonMethods.CreateNewTimberTemplate((TimberTemplate)allTemplates.PortKemblaTimber);
                    else if (UserSelection.SelectedLocation == Location.Sydney)
                        template = CommonMethods.CreateNewTimberTemplate((TimberTemplate)allTemplates.SydneyTimber); 

                    Session["CurrentTemplate"] = template;
                }
                else
                {
                    if ((Session["CurrentTemplate"]).GetType() == typeof(ConcreteTemplate) ||
                        (Session["CurrentTemplate"]).GetType() == typeof(SteelTemplate))
                    {
                        Session["CurrentTemplate"] = null;
                        template = CurrentTimberTemplate;
                    }
                    else
                    {
                        template = (TimberTemplate)(Session["CurrentTemplate"]);
                    }
                }
                return template;
            }
            set
            {
                Session["CurrentTemplate"] = value;
            }
        }

        #endregion

        #region Public Methods

        public string GetObjectFileName()
        {
            string objectName = String.Empty;

            if (UserSelection.SelectedTemplateType == TemplateType.ConcreteTempate & UserSelection.SelectedLocation == Location.Gladstone)
                objectName = Common.TEMPLATE_CONCRETE_GLADSTONE;
            else if (UserSelection.SelectedTemplateType == TemplateType.ConcreteTempate & UserSelection.SelectedLocation == Location.PortKembla)
                objectName = Common.TEMPLATE_CONCRETE_PORTKEMBLA;
            else if (UserSelection.SelectedTemplateType == TemplateType.ConcreteTempate & UserSelection.SelectedLocation == Location.Sydney)
                objectName = Common.TEMPLATE_CONCRETE_SYDNEY;
            else if (UserSelection.SelectedTemplateType == TemplateType.TimberTemplate & UserSelection.SelectedLocation == Location.Gladstone)
                objectName = Common.TEMPLATE_TIMBER_GLADSTONE;
            else if (UserSelection.SelectedTemplateType == TemplateType.TimberTemplate & UserSelection.SelectedLocation == Location.PortKembla)
                objectName = Common.TEMPLATE_TIMBER_PORTKEMBLA;
            else if (UserSelection.SelectedTemplateType == TemplateType.TimberTemplate & UserSelection.SelectedLocation == Location.Sydney)
                objectName = Common.TEMPLATE_TIMBER_SYDNEY;
            else if (UserSelection.SelectedTemplateType == TemplateType.SteelTemplate & UserSelection.SelectedLocation == Location.Gladstone)
                objectName = Common.TEMPLATE_STEEL_GLADSTONE;
            else if (UserSelection.SelectedTemplateType == TemplateType.SteelTemplate & UserSelection.SelectedLocation == Location.PortKembla)
                objectName = Common.TEMPLATE_STEEL_PORTKEMBLA;
            else if (UserSelection.SelectedTemplateType == TemplateType.SteelTemplate & UserSelection.SelectedLocation == Location.Sydney)
                objectName = Common.TEMPLATE_STEEL_SYDNEY;

            return objectName;
        }

        #endregion

    }
}