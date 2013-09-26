using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Security;
using System.Web.SessionState;
using System.IO;

namespace CCIMT.Presentation
{
    public class Global : System.Web.HttpApplication
    {

        void Application_Start(object sender, EventArgs e)
        {
            // Code that runs on application startup

            // Load Concrete Templates
            Object templateConcreteGladstone = new Object();
            CommonMethods.LoadTemplateData(Server, ref templateConcreteGladstone, TemplateType.ConcreteTempate, Location.Gladstone);
            Object templateConcreteKembla = new Object();
            CommonMethods.LoadTemplateData(Server, ref templateConcreteKembla, TemplateType.ConcreteTempate, Location.PortKembla);
            Object templateConcreteSydney = new Object();
            CommonMethods.LoadTemplateData(Server, ref templateConcreteSydney, TemplateType.ConcreteTempate, Location.Sydney);

            // Load Timber Templates
            Object templateTimberGladstone = new Object();
            CommonMethods.LoadTemplateData(Server, ref templateTimberGladstone, TemplateType.TimberTemplate, Location.Gladstone);
            Object templateTimberKembla = new Object();
            CommonMethods.LoadTemplateData(Server, ref templateTimberKembla, TemplateType.TimberTemplate, Location.PortKembla);
            Object templateTimberSydney = new Object();
            CommonMethods.LoadTemplateData(Server, ref templateTimberSydney, TemplateType.TimberTemplate, Location.Sydney);

            // Load Steel Templates
            Object templateSteelGladstone = new Object();
            CommonMethods.LoadTemplateData(Server, ref templateSteelGladstone, TemplateType.SteelTemplate, Location.Gladstone);
            Object templateSteelKembla = new Object();
            CommonMethods.LoadTemplateData(Server, ref templateSteelKembla, TemplateType.SteelTemplate, Location.PortKembla);
            Object templateSteelSydney = new Object();
            CommonMethods.LoadTemplateData(Server, ref templateSteelSydney, TemplateType.SteelTemplate, Location.Sydney);

            Templates allTemplates = new Templates();
            allTemplates.GladstoneConcrete = (ConcreteTemplate)templateConcreteGladstone;
            allTemplates.PortKemblaConcrete = (ConcreteTemplate)templateConcreteKembla;
            allTemplates.SydneyConcrete = (ConcreteTemplate)templateConcreteSydney;
            allTemplates.GladstoneTimber = (TimberTemplate)templateTimberGladstone;
            allTemplates.PortKemblaTimber = (TimberTemplate)templateTimberKembla;
            allTemplates.SydneyTimber = (TimberTemplate)templateTimberSydney;
            allTemplates.GladstoneSteel = (SteelTemplate)templateSteelGladstone;
            allTemplates.PortKemblaSteel = (SteelTemplate)templateSteelKembla;
            allTemplates.SydneySteel = (SteelTemplate)templateSteelSydney;

            Application["AllTemplates"] = allTemplates;
        }

        void Application_End(object sender, EventArgs e)
        {
            //  Code that runs on application shutdown
            Application["AllTemplates"] = null;
        }

        void Application_Error(object sender, EventArgs e)
        {
            // Code that runs when an unhandled error occurs

        }

        void Session_Start(object sender, EventArgs e)
        {
            // Code that runs when a new session is started
            try
            {
                string folderName = Session.SessionID;
                string folderPath = Server.MapPath(String.Concat(Common.OBJECT_PATH, folderName));
                if (Directory.Exists(folderPath))
                {
                    CommonMethods methods = new CommonMethods();
                    methods.DeleteContainingFiles(folderPath);
                    Directory.Delete(folderPath);
                }
                Directory.CreateDirectory(folderPath);
            }
            catch (Exception ex)
            {
            }
        }

        void Session_End(object sender, EventArgs e)
        {
            // Code that runs when a session ends. 
            // Note: The Session_End event is raised only when the sessionstate mode
            // is set to InProc in the Web.config file. If session mode is set to StateServer 
            // or SQLServer, the event is not raised.

        }

    }
}
