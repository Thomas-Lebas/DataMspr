// ============================================================================
//
// Copyright (c) 2006-2015, Talend SA
//
// Ce code source a été automatiquement généré par_Talend Open Studio for Data Integration
// / Soumis à la Licence Apache, Version 2.0 (la "Licence") ;
// votre utilisation de ce fichier doit respecter les termes de la Licence.
// Vous pouvez obtenir une copie de la Licence sur
// http://www.apache.org/licenses/LICENSE-2.0
// 
// Sauf lorsqu'explicitement prévu par la loi en vigueur ou accepté par écrit, le logiciel
// distribué sous la Licence est distribué "TEL QUEL",
// SANS GARANTIE OU CONDITION D'AUCUNE SORTE, expresse ou implicite.
// Consultez la Licence pour connaître la terminologie spécifique régissant les autorisations et
// les limites prévues par la Licence.

package datamspr.intervention_0_1;

import routines.Numeric;
import routines.DataOperation;
import routines.TalendDataGenerator;
import routines.TalendStringUtil;
import routines.TalendString;
import routines.StringHandling;
import routines.Relational;
import routines.TalendDate;
import routines.Mathematical;
import routines.system.*;
import routines.system.api.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.math.BigDecimal;
import java.io.ByteArrayOutputStream;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.IOException;
import java.util.Comparator;

@SuppressWarnings("unused")

/**
 * Job: Intervention Purpose: <br>
 * Description: <br>
 * 
 * @author user@talend.com
 * @version 8.0.1.20211109_1610
 * @status
 */
public class Intervention implements TalendJob {

	protected static void logIgnoredError(String message, Throwable cause) {
		System.err.println(message);
		if (cause != null) {
			cause.printStackTrace();
		}

	}

	public final Object obj = new Object();

	// for transmiting parameters purpose
	private Object valueObject = null;

	public Object getValueObject() {
		return this.valueObject;
	}

	public void setValueObject(Object valueObject) {
		this.valueObject = valueObject;
	}

	private final static String defaultCharset = java.nio.charset.Charset.defaultCharset().name();

	private final static String utf8Charset = "UTF-8";

	// contains type for every context property
	public class PropertiesWithType extends java.util.Properties {
		private static final long serialVersionUID = 1L;
		private java.util.Map<String, String> propertyTypes = new java.util.HashMap<>();

		public PropertiesWithType(java.util.Properties properties) {
			super(properties);
		}

		public PropertiesWithType() {
			super();
		}

		public void setContextType(String key, String type) {
			propertyTypes.put(key, type);
		}

		public String getContextType(String key) {
			return propertyTypes.get(key);
		}
	}

	// create and load default properties
	private java.util.Properties defaultProps = new java.util.Properties();

	// create application properties with default
	public class ContextProperties extends PropertiesWithType {

		private static final long serialVersionUID = 1L;

		public ContextProperties(java.util.Properties properties) {
			super(properties);
		}

		public ContextProperties() {
			super();
		}

		public void synchronizeContext() {

		}

		// if the stored or passed value is "<TALEND_NULL>" string, it mean null
		public String getStringValue(String key) {
			String origin_value = this.getProperty(key);
			if (NULL_VALUE_EXPRESSION_IN_COMMAND_STRING_FOR_CHILD_JOB_ONLY.equals(origin_value)) {
				return null;
			}
			return origin_value;
		}

	}

	protected ContextProperties context = new ContextProperties(); // will be instanciated by MS.

	public ContextProperties getContext() {
		return this.context;
	}

	private final String jobVersion = "0.1";
	private final String jobName = "Intervention";
	private final String projectName = "DATAMSPR";
	public Integer errorCode = null;
	private String currentComponent = "";

	private final java.util.Map<String, Object> globalMap = new java.util.HashMap<String, Object>();
	private final static java.util.Map<String, Object> junitGlobalMap = new java.util.HashMap<String, Object>();

	private final java.util.Map<String, Long> start_Hash = new java.util.HashMap<String, Long>();
	private final java.util.Map<String, Long> end_Hash = new java.util.HashMap<String, Long>();
	private final java.util.Map<String, Boolean> ok_Hash = new java.util.HashMap<String, Boolean>();
	public final java.util.List<String[]> globalBuffer = new java.util.ArrayList<String[]>();

	private RunStat runStat = new RunStat();

	// OSGi DataSource
	private final static String KEY_DB_DATASOURCES = "KEY_DB_DATASOURCES";

	private final static String KEY_DB_DATASOURCES_RAW = "KEY_DB_DATASOURCES_RAW";

	public void setDataSources(java.util.Map<String, javax.sql.DataSource> dataSources) {
		java.util.Map<String, routines.system.TalendDataSource> talendDataSources = new java.util.HashMap<String, routines.system.TalendDataSource>();
		for (java.util.Map.Entry<String, javax.sql.DataSource> dataSourceEntry : dataSources.entrySet()) {
			talendDataSources.put(dataSourceEntry.getKey(),
					new routines.system.TalendDataSource(dataSourceEntry.getValue()));
		}
		globalMap.put(KEY_DB_DATASOURCES, talendDataSources);
		globalMap.put(KEY_DB_DATASOURCES_RAW, new java.util.HashMap<String, javax.sql.DataSource>(dataSources));
	}

	public void setDataSourceReferences(List serviceReferences) throws Exception {

		java.util.Map<String, routines.system.TalendDataSource> talendDataSources = new java.util.HashMap<String, routines.system.TalendDataSource>();
		java.util.Map<String, javax.sql.DataSource> dataSources = new java.util.HashMap<String, javax.sql.DataSource>();

		for (java.util.Map.Entry<String, javax.sql.DataSource> entry : BundleUtils
				.getServices(serviceReferences, javax.sql.DataSource.class).entrySet()) {
			dataSources.put(entry.getKey(), entry.getValue());
			talendDataSources.put(entry.getKey(), new routines.system.TalendDataSource(entry.getValue()));
		}

		globalMap.put(KEY_DB_DATASOURCES, talendDataSources);
		globalMap.put(KEY_DB_DATASOURCES_RAW, new java.util.HashMap<String, javax.sql.DataSource>(dataSources));
	}

	private final java.io.ByteArrayOutputStream baos = new java.io.ByteArrayOutputStream();
	private final java.io.PrintStream errorMessagePS = new java.io.PrintStream(new java.io.BufferedOutputStream(baos));

	public String getExceptionStackTrace() {
		if ("failure".equals(this.getStatus())) {
			errorMessagePS.flush();
			return baos.toString();
		}
		return null;
	}

	private Exception exception;

	public Exception getException() {
		if ("failure".equals(this.getStatus())) {
			return this.exception;
		}
		return null;
	}

	private class TalendException extends Exception {

		private static final long serialVersionUID = 1L;

		private java.util.Map<String, Object> globalMap = null;
		private Exception e = null;
		private String currentComponent = null;
		private String virtualComponentName = null;

		public void setVirtualComponentName(String virtualComponentName) {
			this.virtualComponentName = virtualComponentName;
		}

		private TalendException(Exception e, String errorComponent, final java.util.Map<String, Object> globalMap) {
			this.currentComponent = errorComponent;
			this.globalMap = globalMap;
			this.e = e;
		}

		public Exception getException() {
			return this.e;
		}

		public String getCurrentComponent() {
			return this.currentComponent;
		}

		public String getExceptionCauseMessage(Exception e) {
			Throwable cause = e;
			String message = null;
			int i = 10;
			while (null != cause && 0 < i--) {
				message = cause.getMessage();
				if (null == message) {
					cause = cause.getCause();
				} else {
					break;
				}
			}
			if (null == message) {
				message = e.getClass().getName();
			}
			return message;
		}

		@Override
		public void printStackTrace() {
			if (!(e instanceof TalendException || e instanceof TDieException)) {
				if (virtualComponentName != null && currentComponent.indexOf(virtualComponentName + "_") == 0) {
					globalMap.put(virtualComponentName + "_ERROR_MESSAGE", getExceptionCauseMessage(e));
				}
				globalMap.put(currentComponent + "_ERROR_MESSAGE", getExceptionCauseMessage(e));
				System.err.println("Exception in component " + currentComponent + " (" + jobName + ")");
			}
			if (!(e instanceof TDieException)) {
				if (e instanceof TalendException) {
					e.printStackTrace();
				} else {
					e.printStackTrace();
					e.printStackTrace(errorMessagePS);
					Intervention.this.exception = e;
				}
			}
			if (!(e instanceof TalendException)) {
				try {
					for (java.lang.reflect.Method m : this.getClass().getEnclosingClass().getMethods()) {
						if (m.getName().compareTo(currentComponent + "_error") == 0) {
							m.invoke(Intervention.this, new Object[] { e, currentComponent, globalMap });
							break;
						}
					}

					if (!(e instanceof TDieException)) {
					}
				} catch (Exception e) {
					this.e.printStackTrace();
				}
			}
		}
	}

	public void tFileInputDelimited_1_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputDelimited_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tLogRow_1_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputDelimited_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tMap_1_error(Exception exception, String errorComponent, final java.util.Map<String, Object> globalMap)
			throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputDelimited_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tReplicate_1_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputDelimited_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tFileOutputDelimited_1_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputDelimited_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tDBOutput_1_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputDelimited_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tFileInputDelimited_1_onSubJobError(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		resumeUtil.addLog("SYSTEM_LOG", "NODE:" + errorComponent, "", Thread.currentThread().getId() + "", "FATAL", "",
				exception.getMessage(), ResumeUtil.getExceptionStackTrace(exception), "");

	}

	public static class row4Struct implements routines.system.IPersistableRow<row4Struct> {
		final static byte[] commonByteArrayLock_DATAMSPR_Intervention = new byte[0];
		static byte[] commonByteArray_DATAMSPR_Intervention = new byte[0];
		protected static final int DEFAULT_HASHCODE = 1;
		protected static final int PRIME = 31;
		protected int hashCode = DEFAULT_HASHCODE;
		public boolean hashCodeDirty = true;

		public String loopKey;

		public int Id;

		public int getId() {
			return this.Id;
		}

		public Integer Annee;

		public Integer getAnnee() {
			return this.Annee;
		}

		public String Zone;

		public String getZone() {
			return this.Zone;
		}

		public String Region;

		public String getRegion() {
			return this.Region;
		}

		public String Departement;

		public String getDepartement() {
			return this.Departement;
		}

		public String CategorieA;

		public String getCategorieA() {
			return this.CategorieA;
		}

		public Integer FeuxHabitationsBureaux;

		public Integer getFeuxHabitationsBureaux() {
			return this.FeuxHabitationsBureaux;
		}

		public Integer FeuxLocalIndustriels;

		public Integer getFeuxLocalIndustriels() {
			return this.FeuxLocalIndustriels;
		}

		public Integer FeuxLocauxArtisanaux;

		public Integer getFeuxLocauxArtisanaux() {
			return this.FeuxLocauxArtisanaux;
		}

		public Integer FeuxLocauxAgricoles;

		public Integer getFeuxLocauxAgricoles() {
			return this.FeuxLocauxAgricoles;
		}

		public Integer FeuxVoiePublique;

		public Integer getFeuxVoiePublique() {
			return this.FeuxVoiePublique;
		}

		public Integer FeuxVehicules;

		public Integer getFeuxVehicules() {
			return this.FeuxVehicules;
		}

		public Integer FeuxVegetations;

		public Integer getFeuxVegetations() {
			return this.FeuxVegetations;
		}

		public Integer AutresFeux;

		public Integer getAutresFeux() {
			return this.AutresFeux;
		}

		public Integer Incendies;

		public Integer getIncendies() {
			return this.Incendies;
		}

		public Integer AccidentsLieuxTravail;

		public Integer getAccidentsLieuxTravail() {
			return this.AccidentsLieuxTravail;
		}

		public Integer AccidentsVoiePublique;

		public Integer getAccidentsVoiePublique() {
			return this.AccidentsVoiePublique;
		}

		public Integer MalaisesLieuxTravail;

		public Integer getMalaisesLieuxTravail() {
			return this.MalaisesLieuxTravail;
		}

		public Integer MalaisesVoiePublique;

		public Integer getMalaisesVoiePublique() {
			return this.MalaisesVoiePublique;
		}

		public Integer Intoxications;

		public Integer getIntoxications() {
			return this.Intoxications;
		}

		public Integer IntoxicationsCO;

		public Integer getIntoxicationsCO() {
			return this.IntoxicationsCO;
		}

		public Integer SecoursVictime;

		public Integer getSecoursVictime() {
			return this.SecoursVictime;
		}

		public Integer RecherchePersonnes;

		public Integer getRecherchePersonnes() {
			return this.RecherchePersonnes;
		}

		public Integer AidesPersonne;

		public Integer getAidesPersonne() {
			return this.AidesPersonne;
		}

		public Integer SecoursPersonne;

		public Integer getSecoursPersonne() {
			return this.SecoursPersonne;
		}

		public Integer AccientsRoutiers;

		public Integer getAccientsRoutiers() {
			return this.AccientsRoutiers;
		}

		public Integer AccidentsFerroviaires;

		public Integer getAccidentsFerroviaires() {
			return this.AccidentsFerroviaires;
		}

		public Integer AccidentsAeriens;

		public Integer getAccidentsAeriens() {
			return this.AccidentsAeriens;
		}

		public Integer AccidentsNavigation;

		public Integer getAccidentsNavigation() {
			return this.AccidentsNavigation;
		}

		public Integer AccidentsCirculation;

		public Integer getAccidentsCirculation() {
			return this.AccidentsCirculation;
		}

		public Integer OdeursFuiteGaz;

		public Integer getOdeursFuiteGaz() {
			return this.OdeursFuiteGaz;
		}

		public Integer OdeursAutres;

		public Integer getOdeursAutres() {
			return this.OdeursAutres;
		}

		public Integer FaitDusElectricite;

		public Integer getFaitDusElectricite() {
			return this.FaitDusElectricite;
		}

		public Integer PollutionsContaminations;

		public Integer getPollutionsContaminations() {
			return this.PollutionsContaminations;
		}

		public Integer AutresRisquesTechnologiques;

		public Integer getAutresRisquesTechnologiques() {
			return this.AutresRisquesTechnologiques;
		}

		public Integer RisquesTechnologiques;

		public Integer getRisquesTechnologiques() {
			return this.RisquesTechnologiques;
		}

		public Integer FaussesAlertes;

		public Integer getFaussesAlertes() {
			return this.FaussesAlertes;
		}

		public Integer PiquetsSecuriteSurveillances;

		public Integer getPiquetsSecuriteSurveillances() {
			return this.PiquetsSecuriteSurveillances;
		}

		public Integer EnginsExplosifs;

		public Integer getEnginsExplosifs() {
			return this.EnginsExplosifs;
		}

		public Integer TotalInterventions;

		public Integer getTotalInterventions() {
			return this.TotalInterventions;
		}

		@Override
		public int hashCode() {
			if (this.hashCodeDirty) {
				final int prime = PRIME;
				int result = DEFAULT_HASHCODE;

				result = prime * result + (int) this.Id;

				this.hashCode = result;
				this.hashCodeDirty = false;
			}
			return this.hashCode;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			final row4Struct other = (row4Struct) obj;

			if (this.Id != other.Id)
				return false;

			return true;
		}

		public void copyDataTo(row4Struct other) {

			other.Id = this.Id;
			other.Annee = this.Annee;
			other.Zone = this.Zone;
			other.Region = this.Region;
			other.Departement = this.Departement;
			other.CategorieA = this.CategorieA;
			other.FeuxHabitationsBureaux = this.FeuxHabitationsBureaux;
			other.FeuxLocalIndustriels = this.FeuxLocalIndustriels;
			other.FeuxLocauxArtisanaux = this.FeuxLocauxArtisanaux;
			other.FeuxLocauxAgricoles = this.FeuxLocauxAgricoles;
			other.FeuxVoiePublique = this.FeuxVoiePublique;
			other.FeuxVehicules = this.FeuxVehicules;
			other.FeuxVegetations = this.FeuxVegetations;
			other.AutresFeux = this.AutresFeux;
			other.Incendies = this.Incendies;
			other.AccidentsLieuxTravail = this.AccidentsLieuxTravail;
			other.AccidentsVoiePublique = this.AccidentsVoiePublique;
			other.MalaisesLieuxTravail = this.MalaisesLieuxTravail;
			other.MalaisesVoiePublique = this.MalaisesVoiePublique;
			other.Intoxications = this.Intoxications;
			other.IntoxicationsCO = this.IntoxicationsCO;
			other.SecoursVictime = this.SecoursVictime;
			other.RecherchePersonnes = this.RecherchePersonnes;
			other.AidesPersonne = this.AidesPersonne;
			other.SecoursPersonne = this.SecoursPersonne;
			other.AccientsRoutiers = this.AccientsRoutiers;
			other.AccidentsFerroviaires = this.AccidentsFerroviaires;
			other.AccidentsAeriens = this.AccidentsAeriens;
			other.AccidentsNavigation = this.AccidentsNavigation;
			other.AccidentsCirculation = this.AccidentsCirculation;
			other.OdeursFuiteGaz = this.OdeursFuiteGaz;
			other.OdeursAutres = this.OdeursAutres;
			other.FaitDusElectricite = this.FaitDusElectricite;
			other.PollutionsContaminations = this.PollutionsContaminations;
			other.AutresRisquesTechnologiques = this.AutresRisquesTechnologiques;
			other.RisquesTechnologiques = this.RisquesTechnologiques;
			other.FaussesAlertes = this.FaussesAlertes;
			other.PiquetsSecuriteSurveillances = this.PiquetsSecuriteSurveillances;
			other.EnginsExplosifs = this.EnginsExplosifs;
			other.TotalInterventions = this.TotalInterventions;

		}

		public void copyKeysDataTo(row4Struct other) {

			other.Id = this.Id;

		}

		private Integer readInteger(ObjectInputStream dis) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private Integer readInteger(org.jboss.marshalling.Unmarshaller dis) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private void writeInteger(Integer intNum, ObjectOutputStream dos) throws IOException {
			if (intNum == null) {
				dos.writeByte(-1);
			} else {
				dos.writeByte(0);
				dos.writeInt(intNum);
			}
		}

		private void writeInteger(Integer intNum, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (intNum == null) {
				marshaller.writeByte(-1);
			} else {
				marshaller.writeByte(0);
				marshaller.writeInt(intNum);
			}
		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_DATAMSPR_Intervention.length) {
					if (length < 1024 && commonByteArray_DATAMSPR_Intervention.length == 0) {
						commonByteArray_DATAMSPR_Intervention = new byte[1024];
					} else {
						commonByteArray_DATAMSPR_Intervention = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_DATAMSPR_Intervention, 0, length);
				strReturn = new String(commonByteArray_DATAMSPR_Intervention, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private String readString(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			String strReturn = null;
			int length = 0;
			length = unmarshaller.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_DATAMSPR_Intervention.length) {
					if (length < 1024 && commonByteArray_DATAMSPR_Intervention.length == 0) {
						commonByteArray_DATAMSPR_Intervention = new byte[1024];
					} else {
						commonByteArray_DATAMSPR_Intervention = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_DATAMSPR_Intervention, 0, length);
				strReturn = new String(commonByteArray_DATAMSPR_Intervention, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, ObjectOutputStream dos) throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		private void writeString(String str, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (str == null) {
				marshaller.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				marshaller.writeInt(byteArray.length);
				marshaller.write(byteArray);
			}
		}

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_DATAMSPR_Intervention) {

				try {

					int length = 0;

					this.Id = dis.readInt();

					this.Annee = readInteger(dis);

					this.Zone = readString(dis);

					this.Region = readString(dis);

					this.Departement = readString(dis);

					this.CategorieA = readString(dis);

					this.FeuxHabitationsBureaux = readInteger(dis);

					this.FeuxLocalIndustriels = readInteger(dis);

					this.FeuxLocauxArtisanaux = readInteger(dis);

					this.FeuxLocauxAgricoles = readInteger(dis);

					this.FeuxVoiePublique = readInteger(dis);

					this.FeuxVehicules = readInteger(dis);

					this.FeuxVegetations = readInteger(dis);

					this.AutresFeux = readInteger(dis);

					this.Incendies = readInteger(dis);

					this.AccidentsLieuxTravail = readInteger(dis);

					this.AccidentsVoiePublique = readInteger(dis);

					this.MalaisesLieuxTravail = readInteger(dis);

					this.MalaisesVoiePublique = readInteger(dis);

					this.Intoxications = readInteger(dis);

					this.IntoxicationsCO = readInteger(dis);

					this.SecoursVictime = readInteger(dis);

					this.RecherchePersonnes = readInteger(dis);

					this.AidesPersonne = readInteger(dis);

					this.SecoursPersonne = readInteger(dis);

					this.AccientsRoutiers = readInteger(dis);

					this.AccidentsFerroviaires = readInteger(dis);

					this.AccidentsAeriens = readInteger(dis);

					this.AccidentsNavigation = readInteger(dis);

					this.AccidentsCirculation = readInteger(dis);

					this.OdeursFuiteGaz = readInteger(dis);

					this.OdeursAutres = readInteger(dis);

					this.FaitDusElectricite = readInteger(dis);

					this.PollutionsContaminations = readInteger(dis);

					this.AutresRisquesTechnologiques = readInteger(dis);

					this.RisquesTechnologiques = readInteger(dis);

					this.FaussesAlertes = readInteger(dis);

					this.PiquetsSecuriteSurveillances = readInteger(dis);

					this.EnginsExplosifs = readInteger(dis);

					this.TotalInterventions = readInteger(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_DATAMSPR_Intervention) {

				try {

					int length = 0;

					this.Id = dis.readInt();

					this.Annee = readInteger(dis);

					this.Zone = readString(dis);

					this.Region = readString(dis);

					this.Departement = readString(dis);

					this.CategorieA = readString(dis);

					this.FeuxHabitationsBureaux = readInteger(dis);

					this.FeuxLocalIndustriels = readInteger(dis);

					this.FeuxLocauxArtisanaux = readInteger(dis);

					this.FeuxLocauxAgricoles = readInteger(dis);

					this.FeuxVoiePublique = readInteger(dis);

					this.FeuxVehicules = readInteger(dis);

					this.FeuxVegetations = readInteger(dis);

					this.AutresFeux = readInteger(dis);

					this.Incendies = readInteger(dis);

					this.AccidentsLieuxTravail = readInteger(dis);

					this.AccidentsVoiePublique = readInteger(dis);

					this.MalaisesLieuxTravail = readInteger(dis);

					this.MalaisesVoiePublique = readInteger(dis);

					this.Intoxications = readInteger(dis);

					this.IntoxicationsCO = readInteger(dis);

					this.SecoursVictime = readInteger(dis);

					this.RecherchePersonnes = readInteger(dis);

					this.AidesPersonne = readInteger(dis);

					this.SecoursPersonne = readInteger(dis);

					this.AccientsRoutiers = readInteger(dis);

					this.AccidentsFerroviaires = readInteger(dis);

					this.AccidentsAeriens = readInteger(dis);

					this.AccidentsNavigation = readInteger(dis);

					this.AccidentsCirculation = readInteger(dis);

					this.OdeursFuiteGaz = readInteger(dis);

					this.OdeursAutres = readInteger(dis);

					this.FaitDusElectricite = readInteger(dis);

					this.PollutionsContaminations = readInteger(dis);

					this.AutresRisquesTechnologiques = readInteger(dis);

					this.RisquesTechnologiques = readInteger(dis);

					this.FaussesAlertes = readInteger(dis);

					this.PiquetsSecuriteSurveillances = readInteger(dis);

					this.EnginsExplosifs = readInteger(dis);

					this.TotalInterventions = readInteger(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// int

				dos.writeInt(this.Id);

				// Integer

				writeInteger(this.Annee, dos);

				// String

				writeString(this.Zone, dos);

				// String

				writeString(this.Region, dos);

				// String

				writeString(this.Departement, dos);

				// String

				writeString(this.CategorieA, dos);

				// Integer

				writeInteger(this.FeuxHabitationsBureaux, dos);

				// Integer

				writeInteger(this.FeuxLocalIndustriels, dos);

				// Integer

				writeInteger(this.FeuxLocauxArtisanaux, dos);

				// Integer

				writeInteger(this.FeuxLocauxAgricoles, dos);

				// Integer

				writeInteger(this.FeuxVoiePublique, dos);

				// Integer

				writeInteger(this.FeuxVehicules, dos);

				// Integer

				writeInteger(this.FeuxVegetations, dos);

				// Integer

				writeInteger(this.AutresFeux, dos);

				// Integer

				writeInteger(this.Incendies, dos);

				// Integer

				writeInteger(this.AccidentsLieuxTravail, dos);

				// Integer

				writeInteger(this.AccidentsVoiePublique, dos);

				// Integer

				writeInteger(this.MalaisesLieuxTravail, dos);

				// Integer

				writeInteger(this.MalaisesVoiePublique, dos);

				// Integer

				writeInteger(this.Intoxications, dos);

				// Integer

				writeInteger(this.IntoxicationsCO, dos);

				// Integer

				writeInteger(this.SecoursVictime, dos);

				// Integer

				writeInteger(this.RecherchePersonnes, dos);

				// Integer

				writeInteger(this.AidesPersonne, dos);

				// Integer

				writeInteger(this.SecoursPersonne, dos);

				// Integer

				writeInteger(this.AccientsRoutiers, dos);

				// Integer

				writeInteger(this.AccidentsFerroviaires, dos);

				// Integer

				writeInteger(this.AccidentsAeriens, dos);

				// Integer

				writeInteger(this.AccidentsNavigation, dos);

				// Integer

				writeInteger(this.AccidentsCirculation, dos);

				// Integer

				writeInteger(this.OdeursFuiteGaz, dos);

				// Integer

				writeInteger(this.OdeursAutres, dos);

				// Integer

				writeInteger(this.FaitDusElectricite, dos);

				// Integer

				writeInteger(this.PollutionsContaminations, dos);

				// Integer

				writeInteger(this.AutresRisquesTechnologiques, dos);

				// Integer

				writeInteger(this.RisquesTechnologiques, dos);

				// Integer

				writeInteger(this.FaussesAlertes, dos);

				// Integer

				writeInteger(this.PiquetsSecuriteSurveillances, dos);

				// Integer

				writeInteger(this.EnginsExplosifs, dos);

				// Integer

				writeInteger(this.TotalInterventions, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// int

				dos.writeInt(this.Id);

				// Integer

				writeInteger(this.Annee, dos);

				// String

				writeString(this.Zone, dos);

				// String

				writeString(this.Region, dos);

				// String

				writeString(this.Departement, dos);

				// String

				writeString(this.CategorieA, dos);

				// Integer

				writeInteger(this.FeuxHabitationsBureaux, dos);

				// Integer

				writeInteger(this.FeuxLocalIndustriels, dos);

				// Integer

				writeInteger(this.FeuxLocauxArtisanaux, dos);

				// Integer

				writeInteger(this.FeuxLocauxAgricoles, dos);

				// Integer

				writeInteger(this.FeuxVoiePublique, dos);

				// Integer

				writeInteger(this.FeuxVehicules, dos);

				// Integer

				writeInteger(this.FeuxVegetations, dos);

				// Integer

				writeInteger(this.AutresFeux, dos);

				// Integer

				writeInteger(this.Incendies, dos);

				// Integer

				writeInteger(this.AccidentsLieuxTravail, dos);

				// Integer

				writeInteger(this.AccidentsVoiePublique, dos);

				// Integer

				writeInteger(this.MalaisesLieuxTravail, dos);

				// Integer

				writeInteger(this.MalaisesVoiePublique, dos);

				// Integer

				writeInteger(this.Intoxications, dos);

				// Integer

				writeInteger(this.IntoxicationsCO, dos);

				// Integer

				writeInteger(this.SecoursVictime, dos);

				// Integer

				writeInteger(this.RecherchePersonnes, dos);

				// Integer

				writeInteger(this.AidesPersonne, dos);

				// Integer

				writeInteger(this.SecoursPersonne, dos);

				// Integer

				writeInteger(this.AccientsRoutiers, dos);

				// Integer

				writeInteger(this.AccidentsFerroviaires, dos);

				// Integer

				writeInteger(this.AccidentsAeriens, dos);

				// Integer

				writeInteger(this.AccidentsNavigation, dos);

				// Integer

				writeInteger(this.AccidentsCirculation, dos);

				// Integer

				writeInteger(this.OdeursFuiteGaz, dos);

				// Integer

				writeInteger(this.OdeursAutres, dos);

				// Integer

				writeInteger(this.FaitDusElectricite, dos);

				// Integer

				writeInteger(this.PollutionsContaminations, dos);

				// Integer

				writeInteger(this.AutresRisquesTechnologiques, dos);

				// Integer

				writeInteger(this.RisquesTechnologiques, dos);

				// Integer

				writeInteger(this.FaussesAlertes, dos);

				// Integer

				writeInteger(this.PiquetsSecuriteSurveillances, dos);

				// Integer

				writeInteger(this.EnginsExplosifs, dos);

				// Integer

				writeInteger(this.TotalInterventions, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("Id=" + String.valueOf(Id));
			sb.append(",Annee=" + String.valueOf(Annee));
			sb.append(",Zone=" + Zone);
			sb.append(",Region=" + Region);
			sb.append(",Departement=" + Departement);
			sb.append(",CategorieA=" + CategorieA);
			sb.append(",FeuxHabitationsBureaux=" + String.valueOf(FeuxHabitationsBureaux));
			sb.append(",FeuxLocalIndustriels=" + String.valueOf(FeuxLocalIndustriels));
			sb.append(",FeuxLocauxArtisanaux=" + String.valueOf(FeuxLocauxArtisanaux));
			sb.append(",FeuxLocauxAgricoles=" + String.valueOf(FeuxLocauxAgricoles));
			sb.append(",FeuxVoiePublique=" + String.valueOf(FeuxVoiePublique));
			sb.append(",FeuxVehicules=" + String.valueOf(FeuxVehicules));
			sb.append(",FeuxVegetations=" + String.valueOf(FeuxVegetations));
			sb.append(",AutresFeux=" + String.valueOf(AutresFeux));
			sb.append(",Incendies=" + String.valueOf(Incendies));
			sb.append(",AccidentsLieuxTravail=" + String.valueOf(AccidentsLieuxTravail));
			sb.append(",AccidentsVoiePublique=" + String.valueOf(AccidentsVoiePublique));
			sb.append(",MalaisesLieuxTravail=" + String.valueOf(MalaisesLieuxTravail));
			sb.append(",MalaisesVoiePublique=" + String.valueOf(MalaisesVoiePublique));
			sb.append(",Intoxications=" + String.valueOf(Intoxications));
			sb.append(",IntoxicationsCO=" + String.valueOf(IntoxicationsCO));
			sb.append(",SecoursVictime=" + String.valueOf(SecoursVictime));
			sb.append(",RecherchePersonnes=" + String.valueOf(RecherchePersonnes));
			sb.append(",AidesPersonne=" + String.valueOf(AidesPersonne));
			sb.append(",SecoursPersonne=" + String.valueOf(SecoursPersonne));
			sb.append(",AccientsRoutiers=" + String.valueOf(AccientsRoutiers));
			sb.append(",AccidentsFerroviaires=" + String.valueOf(AccidentsFerroviaires));
			sb.append(",AccidentsAeriens=" + String.valueOf(AccidentsAeriens));
			sb.append(",AccidentsNavigation=" + String.valueOf(AccidentsNavigation));
			sb.append(",AccidentsCirculation=" + String.valueOf(AccidentsCirculation));
			sb.append(",OdeursFuiteGaz=" + String.valueOf(OdeursFuiteGaz));
			sb.append(",OdeursAutres=" + String.valueOf(OdeursAutres));
			sb.append(",FaitDusElectricite=" + String.valueOf(FaitDusElectricite));
			sb.append(",PollutionsContaminations=" + String.valueOf(PollutionsContaminations));
			sb.append(",AutresRisquesTechnologiques=" + String.valueOf(AutresRisquesTechnologiques));
			sb.append(",RisquesTechnologiques=" + String.valueOf(RisquesTechnologiques));
			sb.append(",FaussesAlertes=" + String.valueOf(FaussesAlertes));
			sb.append(",PiquetsSecuriteSurveillances=" + String.valueOf(PiquetsSecuriteSurveillances));
			sb.append(",EnginsExplosifs=" + String.valueOf(EnginsExplosifs));
			sb.append(",TotalInterventions=" + String.valueOf(TotalInterventions));
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(row4Struct other) {

			int returnValue = -1;

			returnValue = checkNullsAndCompare(this.Id, other.Id);
			if (returnValue != 0) {
				return returnValue;
			}

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(), object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public static class row5Struct implements routines.system.IPersistableRow<row5Struct> {
		final static byte[] commonByteArrayLock_DATAMSPR_Intervention = new byte[0];
		static byte[] commonByteArray_DATAMSPR_Intervention = new byte[0];
		protected static final int DEFAULT_HASHCODE = 1;
		protected static final int PRIME = 31;
		protected int hashCode = DEFAULT_HASHCODE;
		public boolean hashCodeDirty = true;

		public String loopKey;

		public int Id;

		public int getId() {
			return this.Id;
		}

		public Integer Annee;

		public Integer getAnnee() {
			return this.Annee;
		}

		public String Zone;

		public String getZone() {
			return this.Zone;
		}

		public String Region;

		public String getRegion() {
			return this.Region;
		}

		public String Departement;

		public String getDepartement() {
			return this.Departement;
		}

		public String CategorieA;

		public String getCategorieA() {
			return this.CategorieA;
		}

		public Integer FeuxHabitationsBureaux;

		public Integer getFeuxHabitationsBureaux() {
			return this.FeuxHabitationsBureaux;
		}

		public Integer FeuxLocalIndustriels;

		public Integer getFeuxLocalIndustriels() {
			return this.FeuxLocalIndustriels;
		}

		public Integer FeuxLocauxArtisanaux;

		public Integer getFeuxLocauxArtisanaux() {
			return this.FeuxLocauxArtisanaux;
		}

		public Integer FeuxLocauxAgricoles;

		public Integer getFeuxLocauxAgricoles() {
			return this.FeuxLocauxAgricoles;
		}

		public Integer FeuxVoiePublique;

		public Integer getFeuxVoiePublique() {
			return this.FeuxVoiePublique;
		}

		public Integer FeuxVehicules;

		public Integer getFeuxVehicules() {
			return this.FeuxVehicules;
		}

		public Integer FeuxVegetations;

		public Integer getFeuxVegetations() {
			return this.FeuxVegetations;
		}

		public Integer AutresFeux;

		public Integer getAutresFeux() {
			return this.AutresFeux;
		}

		public Integer Incendies;

		public Integer getIncendies() {
			return this.Incendies;
		}

		public Integer AccidentsLieuxTravail;

		public Integer getAccidentsLieuxTravail() {
			return this.AccidentsLieuxTravail;
		}

		public Integer AccidentsVoiePublique;

		public Integer getAccidentsVoiePublique() {
			return this.AccidentsVoiePublique;
		}

		public Integer MalaisesLieuxTravail;

		public Integer getMalaisesLieuxTravail() {
			return this.MalaisesLieuxTravail;
		}

		public Integer MalaisesVoiePublique;

		public Integer getMalaisesVoiePublique() {
			return this.MalaisesVoiePublique;
		}

		public Integer Intoxications;

		public Integer getIntoxications() {
			return this.Intoxications;
		}

		public Integer IntoxicationsCO;

		public Integer getIntoxicationsCO() {
			return this.IntoxicationsCO;
		}

		public Integer SecoursVictime;

		public Integer getSecoursVictime() {
			return this.SecoursVictime;
		}

		public Integer RecherchePersonnes;

		public Integer getRecherchePersonnes() {
			return this.RecherchePersonnes;
		}

		public Integer AidesPersonne;

		public Integer getAidesPersonne() {
			return this.AidesPersonne;
		}

		public Integer SecoursPersonne;

		public Integer getSecoursPersonne() {
			return this.SecoursPersonne;
		}

		public Integer AccientsRoutiers;

		public Integer getAccientsRoutiers() {
			return this.AccientsRoutiers;
		}

		public Integer AccidentsFerroviaires;

		public Integer getAccidentsFerroviaires() {
			return this.AccidentsFerroviaires;
		}

		public Integer AccidentsAeriens;

		public Integer getAccidentsAeriens() {
			return this.AccidentsAeriens;
		}

		public Integer AccidentsNavigation;

		public Integer getAccidentsNavigation() {
			return this.AccidentsNavigation;
		}

		public Integer AccidentsCirculation;

		public Integer getAccidentsCirculation() {
			return this.AccidentsCirculation;
		}

		public Integer OdeursFuiteGaz;

		public Integer getOdeursFuiteGaz() {
			return this.OdeursFuiteGaz;
		}

		public Integer OdeursAutres;

		public Integer getOdeursAutres() {
			return this.OdeursAutres;
		}

		public Integer FaitDusElectricite;

		public Integer getFaitDusElectricite() {
			return this.FaitDusElectricite;
		}

		public Integer PollutionsContaminations;

		public Integer getPollutionsContaminations() {
			return this.PollutionsContaminations;
		}

		public Integer AutresRisquesTechnologiques;

		public Integer getAutresRisquesTechnologiques() {
			return this.AutresRisquesTechnologiques;
		}

		public Integer RisquesTechnologiques;

		public Integer getRisquesTechnologiques() {
			return this.RisquesTechnologiques;
		}

		public Integer FaussesAlertes;

		public Integer getFaussesAlertes() {
			return this.FaussesAlertes;
		}

		public Integer PiquetsSecuriteSurveillances;

		public Integer getPiquetsSecuriteSurveillances() {
			return this.PiquetsSecuriteSurveillances;
		}

		public Integer EnginsExplosifs;

		public Integer getEnginsExplosifs() {
			return this.EnginsExplosifs;
		}

		public Integer TotalInterventions;

		public Integer getTotalInterventions() {
			return this.TotalInterventions;
		}

		@Override
		public int hashCode() {
			if (this.hashCodeDirty) {
				final int prime = PRIME;
				int result = DEFAULT_HASHCODE;

				result = prime * result + (int) this.Id;

				this.hashCode = result;
				this.hashCodeDirty = false;
			}
			return this.hashCode;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			final row5Struct other = (row5Struct) obj;

			if (this.Id != other.Id)
				return false;

			return true;
		}

		public void copyDataTo(row5Struct other) {

			other.Id = this.Id;
			other.Annee = this.Annee;
			other.Zone = this.Zone;
			other.Region = this.Region;
			other.Departement = this.Departement;
			other.CategorieA = this.CategorieA;
			other.FeuxHabitationsBureaux = this.FeuxHabitationsBureaux;
			other.FeuxLocalIndustriels = this.FeuxLocalIndustriels;
			other.FeuxLocauxArtisanaux = this.FeuxLocauxArtisanaux;
			other.FeuxLocauxAgricoles = this.FeuxLocauxAgricoles;
			other.FeuxVoiePublique = this.FeuxVoiePublique;
			other.FeuxVehicules = this.FeuxVehicules;
			other.FeuxVegetations = this.FeuxVegetations;
			other.AutresFeux = this.AutresFeux;
			other.Incendies = this.Incendies;
			other.AccidentsLieuxTravail = this.AccidentsLieuxTravail;
			other.AccidentsVoiePublique = this.AccidentsVoiePublique;
			other.MalaisesLieuxTravail = this.MalaisesLieuxTravail;
			other.MalaisesVoiePublique = this.MalaisesVoiePublique;
			other.Intoxications = this.Intoxications;
			other.IntoxicationsCO = this.IntoxicationsCO;
			other.SecoursVictime = this.SecoursVictime;
			other.RecherchePersonnes = this.RecherchePersonnes;
			other.AidesPersonne = this.AidesPersonne;
			other.SecoursPersonne = this.SecoursPersonne;
			other.AccientsRoutiers = this.AccientsRoutiers;
			other.AccidentsFerroviaires = this.AccidentsFerroviaires;
			other.AccidentsAeriens = this.AccidentsAeriens;
			other.AccidentsNavigation = this.AccidentsNavigation;
			other.AccidentsCirculation = this.AccidentsCirculation;
			other.OdeursFuiteGaz = this.OdeursFuiteGaz;
			other.OdeursAutres = this.OdeursAutres;
			other.FaitDusElectricite = this.FaitDusElectricite;
			other.PollutionsContaminations = this.PollutionsContaminations;
			other.AutresRisquesTechnologiques = this.AutresRisquesTechnologiques;
			other.RisquesTechnologiques = this.RisquesTechnologiques;
			other.FaussesAlertes = this.FaussesAlertes;
			other.PiquetsSecuriteSurveillances = this.PiquetsSecuriteSurveillances;
			other.EnginsExplosifs = this.EnginsExplosifs;
			other.TotalInterventions = this.TotalInterventions;

		}

		public void copyKeysDataTo(row5Struct other) {

			other.Id = this.Id;

		}

		private Integer readInteger(ObjectInputStream dis) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private Integer readInteger(org.jboss.marshalling.Unmarshaller dis) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private void writeInteger(Integer intNum, ObjectOutputStream dos) throws IOException {
			if (intNum == null) {
				dos.writeByte(-1);
			} else {
				dos.writeByte(0);
				dos.writeInt(intNum);
			}
		}

		private void writeInteger(Integer intNum, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (intNum == null) {
				marshaller.writeByte(-1);
			} else {
				marshaller.writeByte(0);
				marshaller.writeInt(intNum);
			}
		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_DATAMSPR_Intervention.length) {
					if (length < 1024 && commonByteArray_DATAMSPR_Intervention.length == 0) {
						commonByteArray_DATAMSPR_Intervention = new byte[1024];
					} else {
						commonByteArray_DATAMSPR_Intervention = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_DATAMSPR_Intervention, 0, length);
				strReturn = new String(commonByteArray_DATAMSPR_Intervention, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private String readString(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			String strReturn = null;
			int length = 0;
			length = unmarshaller.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_DATAMSPR_Intervention.length) {
					if (length < 1024 && commonByteArray_DATAMSPR_Intervention.length == 0) {
						commonByteArray_DATAMSPR_Intervention = new byte[1024];
					} else {
						commonByteArray_DATAMSPR_Intervention = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_DATAMSPR_Intervention, 0, length);
				strReturn = new String(commonByteArray_DATAMSPR_Intervention, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, ObjectOutputStream dos) throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		private void writeString(String str, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (str == null) {
				marshaller.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				marshaller.writeInt(byteArray.length);
				marshaller.write(byteArray);
			}
		}

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_DATAMSPR_Intervention) {

				try {

					int length = 0;

					this.Id = dis.readInt();

					this.Annee = readInteger(dis);

					this.Zone = readString(dis);

					this.Region = readString(dis);

					this.Departement = readString(dis);

					this.CategorieA = readString(dis);

					this.FeuxHabitationsBureaux = readInteger(dis);

					this.FeuxLocalIndustriels = readInteger(dis);

					this.FeuxLocauxArtisanaux = readInteger(dis);

					this.FeuxLocauxAgricoles = readInteger(dis);

					this.FeuxVoiePublique = readInteger(dis);

					this.FeuxVehicules = readInteger(dis);

					this.FeuxVegetations = readInteger(dis);

					this.AutresFeux = readInteger(dis);

					this.Incendies = readInteger(dis);

					this.AccidentsLieuxTravail = readInteger(dis);

					this.AccidentsVoiePublique = readInteger(dis);

					this.MalaisesLieuxTravail = readInteger(dis);

					this.MalaisesVoiePublique = readInteger(dis);

					this.Intoxications = readInteger(dis);

					this.IntoxicationsCO = readInteger(dis);

					this.SecoursVictime = readInteger(dis);

					this.RecherchePersonnes = readInteger(dis);

					this.AidesPersonne = readInteger(dis);

					this.SecoursPersonne = readInteger(dis);

					this.AccientsRoutiers = readInteger(dis);

					this.AccidentsFerroviaires = readInteger(dis);

					this.AccidentsAeriens = readInteger(dis);

					this.AccidentsNavigation = readInteger(dis);

					this.AccidentsCirculation = readInteger(dis);

					this.OdeursFuiteGaz = readInteger(dis);

					this.OdeursAutres = readInteger(dis);

					this.FaitDusElectricite = readInteger(dis);

					this.PollutionsContaminations = readInteger(dis);

					this.AutresRisquesTechnologiques = readInteger(dis);

					this.RisquesTechnologiques = readInteger(dis);

					this.FaussesAlertes = readInteger(dis);

					this.PiquetsSecuriteSurveillances = readInteger(dis);

					this.EnginsExplosifs = readInteger(dis);

					this.TotalInterventions = readInteger(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_DATAMSPR_Intervention) {

				try {

					int length = 0;

					this.Id = dis.readInt();

					this.Annee = readInteger(dis);

					this.Zone = readString(dis);

					this.Region = readString(dis);

					this.Departement = readString(dis);

					this.CategorieA = readString(dis);

					this.FeuxHabitationsBureaux = readInteger(dis);

					this.FeuxLocalIndustriels = readInteger(dis);

					this.FeuxLocauxArtisanaux = readInteger(dis);

					this.FeuxLocauxAgricoles = readInteger(dis);

					this.FeuxVoiePublique = readInteger(dis);

					this.FeuxVehicules = readInteger(dis);

					this.FeuxVegetations = readInteger(dis);

					this.AutresFeux = readInteger(dis);

					this.Incendies = readInteger(dis);

					this.AccidentsLieuxTravail = readInteger(dis);

					this.AccidentsVoiePublique = readInteger(dis);

					this.MalaisesLieuxTravail = readInteger(dis);

					this.MalaisesVoiePublique = readInteger(dis);

					this.Intoxications = readInteger(dis);

					this.IntoxicationsCO = readInteger(dis);

					this.SecoursVictime = readInteger(dis);

					this.RecherchePersonnes = readInteger(dis);

					this.AidesPersonne = readInteger(dis);

					this.SecoursPersonne = readInteger(dis);

					this.AccientsRoutiers = readInteger(dis);

					this.AccidentsFerroviaires = readInteger(dis);

					this.AccidentsAeriens = readInteger(dis);

					this.AccidentsNavigation = readInteger(dis);

					this.AccidentsCirculation = readInteger(dis);

					this.OdeursFuiteGaz = readInteger(dis);

					this.OdeursAutres = readInteger(dis);

					this.FaitDusElectricite = readInteger(dis);

					this.PollutionsContaminations = readInteger(dis);

					this.AutresRisquesTechnologiques = readInteger(dis);

					this.RisquesTechnologiques = readInteger(dis);

					this.FaussesAlertes = readInteger(dis);

					this.PiquetsSecuriteSurveillances = readInteger(dis);

					this.EnginsExplosifs = readInteger(dis);

					this.TotalInterventions = readInteger(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// int

				dos.writeInt(this.Id);

				// Integer

				writeInteger(this.Annee, dos);

				// String

				writeString(this.Zone, dos);

				// String

				writeString(this.Region, dos);

				// String

				writeString(this.Departement, dos);

				// String

				writeString(this.CategorieA, dos);

				// Integer

				writeInteger(this.FeuxHabitationsBureaux, dos);

				// Integer

				writeInteger(this.FeuxLocalIndustriels, dos);

				// Integer

				writeInteger(this.FeuxLocauxArtisanaux, dos);

				// Integer

				writeInteger(this.FeuxLocauxAgricoles, dos);

				// Integer

				writeInteger(this.FeuxVoiePublique, dos);

				// Integer

				writeInteger(this.FeuxVehicules, dos);

				// Integer

				writeInteger(this.FeuxVegetations, dos);

				// Integer

				writeInteger(this.AutresFeux, dos);

				// Integer

				writeInteger(this.Incendies, dos);

				// Integer

				writeInteger(this.AccidentsLieuxTravail, dos);

				// Integer

				writeInteger(this.AccidentsVoiePublique, dos);

				// Integer

				writeInteger(this.MalaisesLieuxTravail, dos);

				// Integer

				writeInteger(this.MalaisesVoiePublique, dos);

				// Integer

				writeInteger(this.Intoxications, dos);

				// Integer

				writeInteger(this.IntoxicationsCO, dos);

				// Integer

				writeInteger(this.SecoursVictime, dos);

				// Integer

				writeInteger(this.RecherchePersonnes, dos);

				// Integer

				writeInteger(this.AidesPersonne, dos);

				// Integer

				writeInteger(this.SecoursPersonne, dos);

				// Integer

				writeInteger(this.AccientsRoutiers, dos);

				// Integer

				writeInteger(this.AccidentsFerroviaires, dos);

				// Integer

				writeInteger(this.AccidentsAeriens, dos);

				// Integer

				writeInteger(this.AccidentsNavigation, dos);

				// Integer

				writeInteger(this.AccidentsCirculation, dos);

				// Integer

				writeInteger(this.OdeursFuiteGaz, dos);

				// Integer

				writeInteger(this.OdeursAutres, dos);

				// Integer

				writeInteger(this.FaitDusElectricite, dos);

				// Integer

				writeInteger(this.PollutionsContaminations, dos);

				// Integer

				writeInteger(this.AutresRisquesTechnologiques, dos);

				// Integer

				writeInteger(this.RisquesTechnologiques, dos);

				// Integer

				writeInteger(this.FaussesAlertes, dos);

				// Integer

				writeInteger(this.PiquetsSecuriteSurveillances, dos);

				// Integer

				writeInteger(this.EnginsExplosifs, dos);

				// Integer

				writeInteger(this.TotalInterventions, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// int

				dos.writeInt(this.Id);

				// Integer

				writeInteger(this.Annee, dos);

				// String

				writeString(this.Zone, dos);

				// String

				writeString(this.Region, dos);

				// String

				writeString(this.Departement, dos);

				// String

				writeString(this.CategorieA, dos);

				// Integer

				writeInteger(this.FeuxHabitationsBureaux, dos);

				// Integer

				writeInteger(this.FeuxLocalIndustriels, dos);

				// Integer

				writeInteger(this.FeuxLocauxArtisanaux, dos);

				// Integer

				writeInteger(this.FeuxLocauxAgricoles, dos);

				// Integer

				writeInteger(this.FeuxVoiePublique, dos);

				// Integer

				writeInteger(this.FeuxVehicules, dos);

				// Integer

				writeInteger(this.FeuxVegetations, dos);

				// Integer

				writeInteger(this.AutresFeux, dos);

				// Integer

				writeInteger(this.Incendies, dos);

				// Integer

				writeInteger(this.AccidentsLieuxTravail, dos);

				// Integer

				writeInteger(this.AccidentsVoiePublique, dos);

				// Integer

				writeInteger(this.MalaisesLieuxTravail, dos);

				// Integer

				writeInteger(this.MalaisesVoiePublique, dos);

				// Integer

				writeInteger(this.Intoxications, dos);

				// Integer

				writeInteger(this.IntoxicationsCO, dos);

				// Integer

				writeInteger(this.SecoursVictime, dos);

				// Integer

				writeInteger(this.RecherchePersonnes, dos);

				// Integer

				writeInteger(this.AidesPersonne, dos);

				// Integer

				writeInteger(this.SecoursPersonne, dos);

				// Integer

				writeInteger(this.AccientsRoutiers, dos);

				// Integer

				writeInteger(this.AccidentsFerroviaires, dos);

				// Integer

				writeInteger(this.AccidentsAeriens, dos);

				// Integer

				writeInteger(this.AccidentsNavigation, dos);

				// Integer

				writeInteger(this.AccidentsCirculation, dos);

				// Integer

				writeInteger(this.OdeursFuiteGaz, dos);

				// Integer

				writeInteger(this.OdeursAutres, dos);

				// Integer

				writeInteger(this.FaitDusElectricite, dos);

				// Integer

				writeInteger(this.PollutionsContaminations, dos);

				// Integer

				writeInteger(this.AutresRisquesTechnologiques, dos);

				// Integer

				writeInteger(this.RisquesTechnologiques, dos);

				// Integer

				writeInteger(this.FaussesAlertes, dos);

				// Integer

				writeInteger(this.PiquetsSecuriteSurveillances, dos);

				// Integer

				writeInteger(this.EnginsExplosifs, dos);

				// Integer

				writeInteger(this.TotalInterventions, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("Id=" + String.valueOf(Id));
			sb.append(",Annee=" + String.valueOf(Annee));
			sb.append(",Zone=" + Zone);
			sb.append(",Region=" + Region);
			sb.append(",Departement=" + Departement);
			sb.append(",CategorieA=" + CategorieA);
			sb.append(",FeuxHabitationsBureaux=" + String.valueOf(FeuxHabitationsBureaux));
			sb.append(",FeuxLocalIndustriels=" + String.valueOf(FeuxLocalIndustriels));
			sb.append(",FeuxLocauxArtisanaux=" + String.valueOf(FeuxLocauxArtisanaux));
			sb.append(",FeuxLocauxAgricoles=" + String.valueOf(FeuxLocauxAgricoles));
			sb.append(",FeuxVoiePublique=" + String.valueOf(FeuxVoiePublique));
			sb.append(",FeuxVehicules=" + String.valueOf(FeuxVehicules));
			sb.append(",FeuxVegetations=" + String.valueOf(FeuxVegetations));
			sb.append(",AutresFeux=" + String.valueOf(AutresFeux));
			sb.append(",Incendies=" + String.valueOf(Incendies));
			sb.append(",AccidentsLieuxTravail=" + String.valueOf(AccidentsLieuxTravail));
			sb.append(",AccidentsVoiePublique=" + String.valueOf(AccidentsVoiePublique));
			sb.append(",MalaisesLieuxTravail=" + String.valueOf(MalaisesLieuxTravail));
			sb.append(",MalaisesVoiePublique=" + String.valueOf(MalaisesVoiePublique));
			sb.append(",Intoxications=" + String.valueOf(Intoxications));
			sb.append(",IntoxicationsCO=" + String.valueOf(IntoxicationsCO));
			sb.append(",SecoursVictime=" + String.valueOf(SecoursVictime));
			sb.append(",RecherchePersonnes=" + String.valueOf(RecherchePersonnes));
			sb.append(",AidesPersonne=" + String.valueOf(AidesPersonne));
			sb.append(",SecoursPersonne=" + String.valueOf(SecoursPersonne));
			sb.append(",AccientsRoutiers=" + String.valueOf(AccientsRoutiers));
			sb.append(",AccidentsFerroviaires=" + String.valueOf(AccidentsFerroviaires));
			sb.append(",AccidentsAeriens=" + String.valueOf(AccidentsAeriens));
			sb.append(",AccidentsNavigation=" + String.valueOf(AccidentsNavigation));
			sb.append(",AccidentsCirculation=" + String.valueOf(AccidentsCirculation));
			sb.append(",OdeursFuiteGaz=" + String.valueOf(OdeursFuiteGaz));
			sb.append(",OdeursAutres=" + String.valueOf(OdeursAutres));
			sb.append(",FaitDusElectricite=" + String.valueOf(FaitDusElectricite));
			sb.append(",PollutionsContaminations=" + String.valueOf(PollutionsContaminations));
			sb.append(",AutresRisquesTechnologiques=" + String.valueOf(AutresRisquesTechnologiques));
			sb.append(",RisquesTechnologiques=" + String.valueOf(RisquesTechnologiques));
			sb.append(",FaussesAlertes=" + String.valueOf(FaussesAlertes));
			sb.append(",PiquetsSecuriteSurveillances=" + String.valueOf(PiquetsSecuriteSurveillances));
			sb.append(",EnginsExplosifs=" + String.valueOf(EnginsExplosifs));
			sb.append(",TotalInterventions=" + String.valueOf(TotalInterventions));
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(row5Struct other) {

			int returnValue = -1;

			returnValue = checkNullsAndCompare(this.Id, other.Id);
			if (returnValue != 0) {
				return returnValue;
			}

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(), object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public static class out1Struct implements routines.system.IPersistableRow<out1Struct> {
		final static byte[] commonByteArrayLock_DATAMSPR_Intervention = new byte[0];
		static byte[] commonByteArray_DATAMSPR_Intervention = new byte[0];
		protected static final int DEFAULT_HASHCODE = 1;
		protected static final int PRIME = 31;
		protected int hashCode = DEFAULT_HASHCODE;
		public boolean hashCodeDirty = true;

		public String loopKey;

		public int Id;

		public int getId() {
			return this.Id;
		}

		public Integer Annee;

		public Integer getAnnee() {
			return this.Annee;
		}

		public String Zone;

		public String getZone() {
			return this.Zone;
		}

		public String Region;

		public String getRegion() {
			return this.Region;
		}

		public String Departement;

		public String getDepartement() {
			return this.Departement;
		}

		public String CategorieA;

		public String getCategorieA() {
			return this.CategorieA;
		}

		public Integer FeuxHabitationsBureaux;

		public Integer getFeuxHabitationsBureaux() {
			return this.FeuxHabitationsBureaux;
		}

		public Integer FeuxLocalIndustriels;

		public Integer getFeuxLocalIndustriels() {
			return this.FeuxLocalIndustriels;
		}

		public Integer FeuxLocauxArtisanaux;

		public Integer getFeuxLocauxArtisanaux() {
			return this.FeuxLocauxArtisanaux;
		}

		public Integer FeuxLocauxAgricoles;

		public Integer getFeuxLocauxAgricoles() {
			return this.FeuxLocauxAgricoles;
		}

		public Integer FeuxVoiePublique;

		public Integer getFeuxVoiePublique() {
			return this.FeuxVoiePublique;
		}

		public Integer FeuxVehicules;

		public Integer getFeuxVehicules() {
			return this.FeuxVehicules;
		}

		public Integer FeuxVegetations;

		public Integer getFeuxVegetations() {
			return this.FeuxVegetations;
		}

		public Integer AutresFeux;

		public Integer getAutresFeux() {
			return this.AutresFeux;
		}

		public Integer Incendies;

		public Integer getIncendies() {
			return this.Incendies;
		}

		public Integer AccidentsLieuxTravail;

		public Integer getAccidentsLieuxTravail() {
			return this.AccidentsLieuxTravail;
		}

		public Integer AccidentsVoiePublique;

		public Integer getAccidentsVoiePublique() {
			return this.AccidentsVoiePublique;
		}

		public Integer MalaisesLieuxTravail;

		public Integer getMalaisesLieuxTravail() {
			return this.MalaisesLieuxTravail;
		}

		public Integer MalaisesVoiePublique;

		public Integer getMalaisesVoiePublique() {
			return this.MalaisesVoiePublique;
		}

		public Integer Intoxications;

		public Integer getIntoxications() {
			return this.Intoxications;
		}

		public Integer IntoxicationsCO;

		public Integer getIntoxicationsCO() {
			return this.IntoxicationsCO;
		}

		public Integer SecoursVictime;

		public Integer getSecoursVictime() {
			return this.SecoursVictime;
		}

		public Integer RecherchePersonnes;

		public Integer getRecherchePersonnes() {
			return this.RecherchePersonnes;
		}

		public Integer AidesPersonne;

		public Integer getAidesPersonne() {
			return this.AidesPersonne;
		}

		public Integer SecoursPersonne;

		public Integer getSecoursPersonne() {
			return this.SecoursPersonne;
		}

		public Integer AccientsRoutiers;

		public Integer getAccientsRoutiers() {
			return this.AccientsRoutiers;
		}

		public Integer AccidentsFerroviaires;

		public Integer getAccidentsFerroviaires() {
			return this.AccidentsFerroviaires;
		}

		public Integer AccidentsAeriens;

		public Integer getAccidentsAeriens() {
			return this.AccidentsAeriens;
		}

		public Integer AccidentsNavigation;

		public Integer getAccidentsNavigation() {
			return this.AccidentsNavigation;
		}

		public Integer AccidentsCirculation;

		public Integer getAccidentsCirculation() {
			return this.AccidentsCirculation;
		}

		public Integer OdeursFuiteGaz;

		public Integer getOdeursFuiteGaz() {
			return this.OdeursFuiteGaz;
		}

		public Integer OdeursAutres;

		public Integer getOdeursAutres() {
			return this.OdeursAutres;
		}

		public Integer FaitDusElectricite;

		public Integer getFaitDusElectricite() {
			return this.FaitDusElectricite;
		}

		public Integer PollutionsContaminations;

		public Integer getPollutionsContaminations() {
			return this.PollutionsContaminations;
		}

		public Integer AutresRisquesTechnologiques;

		public Integer getAutresRisquesTechnologiques() {
			return this.AutresRisquesTechnologiques;
		}

		public Integer RisquesTechnologiques;

		public Integer getRisquesTechnologiques() {
			return this.RisquesTechnologiques;
		}

		public Integer FaussesAlertes;

		public Integer getFaussesAlertes() {
			return this.FaussesAlertes;
		}

		public Integer PiquetsSecuriteSurveillances;

		public Integer getPiquetsSecuriteSurveillances() {
			return this.PiquetsSecuriteSurveillances;
		}

		public Integer EnginsExplosifs;

		public Integer getEnginsExplosifs() {
			return this.EnginsExplosifs;
		}

		public Integer TotalInterventions;

		public Integer getTotalInterventions() {
			return this.TotalInterventions;
		}

		@Override
		public int hashCode() {
			if (this.hashCodeDirty) {
				final int prime = PRIME;
				int result = DEFAULT_HASHCODE;

				result = prime * result + (int) this.Id;

				this.hashCode = result;
				this.hashCodeDirty = false;
			}
			return this.hashCode;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			final out1Struct other = (out1Struct) obj;

			if (this.Id != other.Id)
				return false;

			return true;
		}

		public void copyDataTo(out1Struct other) {

			other.Id = this.Id;
			other.Annee = this.Annee;
			other.Zone = this.Zone;
			other.Region = this.Region;
			other.Departement = this.Departement;
			other.CategorieA = this.CategorieA;
			other.FeuxHabitationsBureaux = this.FeuxHabitationsBureaux;
			other.FeuxLocalIndustriels = this.FeuxLocalIndustriels;
			other.FeuxLocauxArtisanaux = this.FeuxLocauxArtisanaux;
			other.FeuxLocauxAgricoles = this.FeuxLocauxAgricoles;
			other.FeuxVoiePublique = this.FeuxVoiePublique;
			other.FeuxVehicules = this.FeuxVehicules;
			other.FeuxVegetations = this.FeuxVegetations;
			other.AutresFeux = this.AutresFeux;
			other.Incendies = this.Incendies;
			other.AccidentsLieuxTravail = this.AccidentsLieuxTravail;
			other.AccidentsVoiePublique = this.AccidentsVoiePublique;
			other.MalaisesLieuxTravail = this.MalaisesLieuxTravail;
			other.MalaisesVoiePublique = this.MalaisesVoiePublique;
			other.Intoxications = this.Intoxications;
			other.IntoxicationsCO = this.IntoxicationsCO;
			other.SecoursVictime = this.SecoursVictime;
			other.RecherchePersonnes = this.RecherchePersonnes;
			other.AidesPersonne = this.AidesPersonne;
			other.SecoursPersonne = this.SecoursPersonne;
			other.AccientsRoutiers = this.AccientsRoutiers;
			other.AccidentsFerroviaires = this.AccidentsFerroviaires;
			other.AccidentsAeriens = this.AccidentsAeriens;
			other.AccidentsNavigation = this.AccidentsNavigation;
			other.AccidentsCirculation = this.AccidentsCirculation;
			other.OdeursFuiteGaz = this.OdeursFuiteGaz;
			other.OdeursAutres = this.OdeursAutres;
			other.FaitDusElectricite = this.FaitDusElectricite;
			other.PollutionsContaminations = this.PollutionsContaminations;
			other.AutresRisquesTechnologiques = this.AutresRisquesTechnologiques;
			other.RisquesTechnologiques = this.RisquesTechnologiques;
			other.FaussesAlertes = this.FaussesAlertes;
			other.PiquetsSecuriteSurveillances = this.PiquetsSecuriteSurveillances;
			other.EnginsExplosifs = this.EnginsExplosifs;
			other.TotalInterventions = this.TotalInterventions;

		}

		public void copyKeysDataTo(out1Struct other) {

			other.Id = this.Id;

		}

		private Integer readInteger(ObjectInputStream dis) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private Integer readInteger(org.jboss.marshalling.Unmarshaller dis) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private void writeInteger(Integer intNum, ObjectOutputStream dos) throws IOException {
			if (intNum == null) {
				dos.writeByte(-1);
			} else {
				dos.writeByte(0);
				dos.writeInt(intNum);
			}
		}

		private void writeInteger(Integer intNum, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (intNum == null) {
				marshaller.writeByte(-1);
			} else {
				marshaller.writeByte(0);
				marshaller.writeInt(intNum);
			}
		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_DATAMSPR_Intervention.length) {
					if (length < 1024 && commonByteArray_DATAMSPR_Intervention.length == 0) {
						commonByteArray_DATAMSPR_Intervention = new byte[1024];
					} else {
						commonByteArray_DATAMSPR_Intervention = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_DATAMSPR_Intervention, 0, length);
				strReturn = new String(commonByteArray_DATAMSPR_Intervention, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private String readString(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			String strReturn = null;
			int length = 0;
			length = unmarshaller.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_DATAMSPR_Intervention.length) {
					if (length < 1024 && commonByteArray_DATAMSPR_Intervention.length == 0) {
						commonByteArray_DATAMSPR_Intervention = new byte[1024];
					} else {
						commonByteArray_DATAMSPR_Intervention = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_DATAMSPR_Intervention, 0, length);
				strReturn = new String(commonByteArray_DATAMSPR_Intervention, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, ObjectOutputStream dos) throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		private void writeString(String str, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (str == null) {
				marshaller.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				marshaller.writeInt(byteArray.length);
				marshaller.write(byteArray);
			}
		}

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_DATAMSPR_Intervention) {

				try {

					int length = 0;

					this.Id = dis.readInt();

					this.Annee = readInteger(dis);

					this.Zone = readString(dis);

					this.Region = readString(dis);

					this.Departement = readString(dis);

					this.CategorieA = readString(dis);

					this.FeuxHabitationsBureaux = readInteger(dis);

					this.FeuxLocalIndustriels = readInteger(dis);

					this.FeuxLocauxArtisanaux = readInteger(dis);

					this.FeuxLocauxAgricoles = readInteger(dis);

					this.FeuxVoiePublique = readInteger(dis);

					this.FeuxVehicules = readInteger(dis);

					this.FeuxVegetations = readInteger(dis);

					this.AutresFeux = readInteger(dis);

					this.Incendies = readInteger(dis);

					this.AccidentsLieuxTravail = readInteger(dis);

					this.AccidentsVoiePublique = readInteger(dis);

					this.MalaisesLieuxTravail = readInteger(dis);

					this.MalaisesVoiePublique = readInteger(dis);

					this.Intoxications = readInteger(dis);

					this.IntoxicationsCO = readInteger(dis);

					this.SecoursVictime = readInteger(dis);

					this.RecherchePersonnes = readInteger(dis);

					this.AidesPersonne = readInteger(dis);

					this.SecoursPersonne = readInteger(dis);

					this.AccientsRoutiers = readInteger(dis);

					this.AccidentsFerroviaires = readInteger(dis);

					this.AccidentsAeriens = readInteger(dis);

					this.AccidentsNavigation = readInteger(dis);

					this.AccidentsCirculation = readInteger(dis);

					this.OdeursFuiteGaz = readInteger(dis);

					this.OdeursAutres = readInteger(dis);

					this.FaitDusElectricite = readInteger(dis);

					this.PollutionsContaminations = readInteger(dis);

					this.AutresRisquesTechnologiques = readInteger(dis);

					this.RisquesTechnologiques = readInteger(dis);

					this.FaussesAlertes = readInteger(dis);

					this.PiquetsSecuriteSurveillances = readInteger(dis);

					this.EnginsExplosifs = readInteger(dis);

					this.TotalInterventions = readInteger(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_DATAMSPR_Intervention) {

				try {

					int length = 0;

					this.Id = dis.readInt();

					this.Annee = readInteger(dis);

					this.Zone = readString(dis);

					this.Region = readString(dis);

					this.Departement = readString(dis);

					this.CategorieA = readString(dis);

					this.FeuxHabitationsBureaux = readInteger(dis);

					this.FeuxLocalIndustriels = readInteger(dis);

					this.FeuxLocauxArtisanaux = readInteger(dis);

					this.FeuxLocauxAgricoles = readInteger(dis);

					this.FeuxVoiePublique = readInteger(dis);

					this.FeuxVehicules = readInteger(dis);

					this.FeuxVegetations = readInteger(dis);

					this.AutresFeux = readInteger(dis);

					this.Incendies = readInteger(dis);

					this.AccidentsLieuxTravail = readInteger(dis);

					this.AccidentsVoiePublique = readInteger(dis);

					this.MalaisesLieuxTravail = readInteger(dis);

					this.MalaisesVoiePublique = readInteger(dis);

					this.Intoxications = readInteger(dis);

					this.IntoxicationsCO = readInteger(dis);

					this.SecoursVictime = readInteger(dis);

					this.RecherchePersonnes = readInteger(dis);

					this.AidesPersonne = readInteger(dis);

					this.SecoursPersonne = readInteger(dis);

					this.AccientsRoutiers = readInteger(dis);

					this.AccidentsFerroviaires = readInteger(dis);

					this.AccidentsAeriens = readInteger(dis);

					this.AccidentsNavigation = readInteger(dis);

					this.AccidentsCirculation = readInteger(dis);

					this.OdeursFuiteGaz = readInteger(dis);

					this.OdeursAutres = readInteger(dis);

					this.FaitDusElectricite = readInteger(dis);

					this.PollutionsContaminations = readInteger(dis);

					this.AutresRisquesTechnologiques = readInteger(dis);

					this.RisquesTechnologiques = readInteger(dis);

					this.FaussesAlertes = readInteger(dis);

					this.PiquetsSecuriteSurveillances = readInteger(dis);

					this.EnginsExplosifs = readInteger(dis);

					this.TotalInterventions = readInteger(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// int

				dos.writeInt(this.Id);

				// Integer

				writeInteger(this.Annee, dos);

				// String

				writeString(this.Zone, dos);

				// String

				writeString(this.Region, dos);

				// String

				writeString(this.Departement, dos);

				// String

				writeString(this.CategorieA, dos);

				// Integer

				writeInteger(this.FeuxHabitationsBureaux, dos);

				// Integer

				writeInteger(this.FeuxLocalIndustriels, dos);

				// Integer

				writeInteger(this.FeuxLocauxArtisanaux, dos);

				// Integer

				writeInteger(this.FeuxLocauxAgricoles, dos);

				// Integer

				writeInteger(this.FeuxVoiePublique, dos);

				// Integer

				writeInteger(this.FeuxVehicules, dos);

				// Integer

				writeInteger(this.FeuxVegetations, dos);

				// Integer

				writeInteger(this.AutresFeux, dos);

				// Integer

				writeInteger(this.Incendies, dos);

				// Integer

				writeInteger(this.AccidentsLieuxTravail, dos);

				// Integer

				writeInteger(this.AccidentsVoiePublique, dos);

				// Integer

				writeInteger(this.MalaisesLieuxTravail, dos);

				// Integer

				writeInteger(this.MalaisesVoiePublique, dos);

				// Integer

				writeInteger(this.Intoxications, dos);

				// Integer

				writeInteger(this.IntoxicationsCO, dos);

				// Integer

				writeInteger(this.SecoursVictime, dos);

				// Integer

				writeInteger(this.RecherchePersonnes, dos);

				// Integer

				writeInteger(this.AidesPersonne, dos);

				// Integer

				writeInteger(this.SecoursPersonne, dos);

				// Integer

				writeInteger(this.AccientsRoutiers, dos);

				// Integer

				writeInteger(this.AccidentsFerroviaires, dos);

				// Integer

				writeInteger(this.AccidentsAeriens, dos);

				// Integer

				writeInteger(this.AccidentsNavigation, dos);

				// Integer

				writeInteger(this.AccidentsCirculation, dos);

				// Integer

				writeInteger(this.OdeursFuiteGaz, dos);

				// Integer

				writeInteger(this.OdeursAutres, dos);

				// Integer

				writeInteger(this.FaitDusElectricite, dos);

				// Integer

				writeInteger(this.PollutionsContaminations, dos);

				// Integer

				writeInteger(this.AutresRisquesTechnologiques, dos);

				// Integer

				writeInteger(this.RisquesTechnologiques, dos);

				// Integer

				writeInteger(this.FaussesAlertes, dos);

				// Integer

				writeInteger(this.PiquetsSecuriteSurveillances, dos);

				// Integer

				writeInteger(this.EnginsExplosifs, dos);

				// Integer

				writeInteger(this.TotalInterventions, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// int

				dos.writeInt(this.Id);

				// Integer

				writeInteger(this.Annee, dos);

				// String

				writeString(this.Zone, dos);

				// String

				writeString(this.Region, dos);

				// String

				writeString(this.Departement, dos);

				// String

				writeString(this.CategorieA, dos);

				// Integer

				writeInteger(this.FeuxHabitationsBureaux, dos);

				// Integer

				writeInteger(this.FeuxLocalIndustriels, dos);

				// Integer

				writeInteger(this.FeuxLocauxArtisanaux, dos);

				// Integer

				writeInteger(this.FeuxLocauxAgricoles, dos);

				// Integer

				writeInteger(this.FeuxVoiePublique, dos);

				// Integer

				writeInteger(this.FeuxVehicules, dos);

				// Integer

				writeInteger(this.FeuxVegetations, dos);

				// Integer

				writeInteger(this.AutresFeux, dos);

				// Integer

				writeInteger(this.Incendies, dos);

				// Integer

				writeInteger(this.AccidentsLieuxTravail, dos);

				// Integer

				writeInteger(this.AccidentsVoiePublique, dos);

				// Integer

				writeInteger(this.MalaisesLieuxTravail, dos);

				// Integer

				writeInteger(this.MalaisesVoiePublique, dos);

				// Integer

				writeInteger(this.Intoxications, dos);

				// Integer

				writeInteger(this.IntoxicationsCO, dos);

				// Integer

				writeInteger(this.SecoursVictime, dos);

				// Integer

				writeInteger(this.RecherchePersonnes, dos);

				// Integer

				writeInteger(this.AidesPersonne, dos);

				// Integer

				writeInteger(this.SecoursPersonne, dos);

				// Integer

				writeInteger(this.AccientsRoutiers, dos);

				// Integer

				writeInteger(this.AccidentsFerroviaires, dos);

				// Integer

				writeInteger(this.AccidentsAeriens, dos);

				// Integer

				writeInteger(this.AccidentsNavigation, dos);

				// Integer

				writeInteger(this.AccidentsCirculation, dos);

				// Integer

				writeInteger(this.OdeursFuiteGaz, dos);

				// Integer

				writeInteger(this.OdeursAutres, dos);

				// Integer

				writeInteger(this.FaitDusElectricite, dos);

				// Integer

				writeInteger(this.PollutionsContaminations, dos);

				// Integer

				writeInteger(this.AutresRisquesTechnologiques, dos);

				// Integer

				writeInteger(this.RisquesTechnologiques, dos);

				// Integer

				writeInteger(this.FaussesAlertes, dos);

				// Integer

				writeInteger(this.PiquetsSecuriteSurveillances, dos);

				// Integer

				writeInteger(this.EnginsExplosifs, dos);

				// Integer

				writeInteger(this.TotalInterventions, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("Id=" + String.valueOf(Id));
			sb.append(",Annee=" + String.valueOf(Annee));
			sb.append(",Zone=" + Zone);
			sb.append(",Region=" + Region);
			sb.append(",Departement=" + Departement);
			sb.append(",CategorieA=" + CategorieA);
			sb.append(",FeuxHabitationsBureaux=" + String.valueOf(FeuxHabitationsBureaux));
			sb.append(",FeuxLocalIndustriels=" + String.valueOf(FeuxLocalIndustriels));
			sb.append(",FeuxLocauxArtisanaux=" + String.valueOf(FeuxLocauxArtisanaux));
			sb.append(",FeuxLocauxAgricoles=" + String.valueOf(FeuxLocauxAgricoles));
			sb.append(",FeuxVoiePublique=" + String.valueOf(FeuxVoiePublique));
			sb.append(",FeuxVehicules=" + String.valueOf(FeuxVehicules));
			sb.append(",FeuxVegetations=" + String.valueOf(FeuxVegetations));
			sb.append(",AutresFeux=" + String.valueOf(AutresFeux));
			sb.append(",Incendies=" + String.valueOf(Incendies));
			sb.append(",AccidentsLieuxTravail=" + String.valueOf(AccidentsLieuxTravail));
			sb.append(",AccidentsVoiePublique=" + String.valueOf(AccidentsVoiePublique));
			sb.append(",MalaisesLieuxTravail=" + String.valueOf(MalaisesLieuxTravail));
			sb.append(",MalaisesVoiePublique=" + String.valueOf(MalaisesVoiePublique));
			sb.append(",Intoxications=" + String.valueOf(Intoxications));
			sb.append(",IntoxicationsCO=" + String.valueOf(IntoxicationsCO));
			sb.append(",SecoursVictime=" + String.valueOf(SecoursVictime));
			sb.append(",RecherchePersonnes=" + String.valueOf(RecherchePersonnes));
			sb.append(",AidesPersonne=" + String.valueOf(AidesPersonne));
			sb.append(",SecoursPersonne=" + String.valueOf(SecoursPersonne));
			sb.append(",AccientsRoutiers=" + String.valueOf(AccientsRoutiers));
			sb.append(",AccidentsFerroviaires=" + String.valueOf(AccidentsFerroviaires));
			sb.append(",AccidentsAeriens=" + String.valueOf(AccidentsAeriens));
			sb.append(",AccidentsNavigation=" + String.valueOf(AccidentsNavigation));
			sb.append(",AccidentsCirculation=" + String.valueOf(AccidentsCirculation));
			sb.append(",OdeursFuiteGaz=" + String.valueOf(OdeursFuiteGaz));
			sb.append(",OdeursAutres=" + String.valueOf(OdeursAutres));
			sb.append(",FaitDusElectricite=" + String.valueOf(FaitDusElectricite));
			sb.append(",PollutionsContaminations=" + String.valueOf(PollutionsContaminations));
			sb.append(",AutresRisquesTechnologiques=" + String.valueOf(AutresRisquesTechnologiques));
			sb.append(",RisquesTechnologiques=" + String.valueOf(RisquesTechnologiques));
			sb.append(",FaussesAlertes=" + String.valueOf(FaussesAlertes));
			sb.append(",PiquetsSecuriteSurveillances=" + String.valueOf(PiquetsSecuriteSurveillances));
			sb.append(",EnginsExplosifs=" + String.valueOf(EnginsExplosifs));
			sb.append(",TotalInterventions=" + String.valueOf(TotalInterventions));
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(out1Struct other) {

			int returnValue = -1;

			returnValue = checkNullsAndCompare(this.Id, other.Id);
			if (returnValue != 0) {
				return returnValue;
			}

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(), object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public static class row2Struct implements routines.system.IPersistableRow<row2Struct> {
		final static byte[] commonByteArrayLock_DATAMSPR_Intervention = new byte[0];
		static byte[] commonByteArray_DATAMSPR_Intervention = new byte[0];

		public Integer Annee;

		public Integer getAnnee() {
			return this.Annee;
		}

		public String Zone;

		public String getZone() {
			return this.Zone;
		}

		public String Region;

		public String getRegion() {
			return this.Region;
		}

		public String Numero;

		public String getNumero() {
			return this.Numero;
		}

		public String Departement;

		public String getDepartement() {
			return this.Departement;
		}

		public String CategorieA;

		public String getCategorieA() {
			return this.CategorieA;
		}

		public Integer FeuxHabitationsBureaux;

		public Integer getFeuxHabitationsBureaux() {
			return this.FeuxHabitationsBureaux;
		}

		public Integer FeuxCheminees;

		public Integer getFeuxCheminees() {
			return this.FeuxCheminees;
		}

		public Integer FeuxAvecLocalSommeil;

		public Integer getFeuxAvecLocalSommeil() {
			return this.FeuxAvecLocalSommeil;
		}

		public Integer FeuxSansLocalSommeil;

		public Integer getFeuxSansLocalSommeil() {
			return this.FeuxSansLocalSommeil;
		}

		public Integer FeuxLocalIndustriels;

		public Integer getFeuxLocalIndustriels() {
			return this.FeuxLocalIndustriels;
		}

		public Integer FeuxLocauxArtisanaux;

		public Integer getFeuxLocauxArtisanaux() {
			return this.FeuxLocauxArtisanaux;
		}

		public Integer FeuxLocauxAgricoles;

		public Integer getFeuxLocauxAgricoles() {
			return this.FeuxLocauxAgricoles;
		}

		public Integer FeuxVoiePublique;

		public Integer getFeuxVoiePublique() {
			return this.FeuxVoiePublique;
		}

		public Integer FeuxVehicules;

		public Integer getFeuxVehicules() {
			return this.FeuxVehicules;
		}

		public Integer FeuxVegetations;

		public Integer getFeuxVegetations() {
			return this.FeuxVegetations;
		}

		public Integer AutresFeux;

		public Integer getAutresFeux() {
			return this.AutresFeux;
		}

		public Integer Incendies;

		public Integer getIncendies() {
			return this.Incendies;
		}

		public Integer AccidentsLieuxTravail;

		public Integer getAccidentsLieuxTravail() {
			return this.AccidentsLieuxTravail;
		}

		public Integer AccidentsDomicile;

		public Integer getAccidentsDomicile() {
			return this.AccidentsDomicile;
		}

		public Integer AccidentsSport;

		public Integer getAccidentsSport() {
			return this.AccidentsSport;
		}

		public Integer AccidentsVoiePublique;

		public Integer getAccidentsVoiePublique() {
			return this.AccidentsVoiePublique;
		}

		public Integer SecoursMontagne;

		public Integer getSecoursMontagne() {
			return this.SecoursMontagne;
		}

		public Integer MalaisesLieuxTravail;

		public Integer getMalaisesLieuxTravail() {
			return this.MalaisesLieuxTravail;
		}

		public Integer MalaisesDomicileVitale;

		public Integer getMalaisesDomicileVitale() {
			return this.MalaisesDomicileVitale;
		}

		public Integer MalaisesDomicileCarence;

		public Integer getMalaisesDomicileCarence() {
			return this.MalaisesDomicileCarence;
		}

		public Integer MalaisesSport;

		public Integer getMalaisesSport() {
			return this.MalaisesSport;
		}

		public Integer MalaisesVoiePublique;

		public Integer getMalaisesVoiePublique() {
			return this.MalaisesVoiePublique;
		}

		public Integer Autolyses;

		public Integer getAutolyses() {
			return this.Autolyses;
		}

		public Integer SecoursEauxInterieuresPiscines;

		public Integer getSecoursEauxInterieuresPiscines() {
			return this.SecoursEauxInterieuresPiscines;
		}

		public Integer SecoursMer;

		public Integer getSecoursMer() {
			return this.SecoursMer;
		}

		public Integer Intoxications;

		public Integer getIntoxications() {
			return this.Intoxications;
		}

		public Integer IntoxicationsCO;

		public Integer getIntoxicationsCO() {
			return this.IntoxicationsCO;
		}

		public Integer AutresSAV;

		public Integer getAutresSAV() {
			return this.AutresSAV;
		}

		public Integer SecoursVictime;

		public Integer getSecoursVictime() {
			return this.SecoursVictime;
		}

		public Integer RelevagePersonnes;

		public Integer getRelevagePersonnes() {
			return this.RelevagePersonnes;
		}

		public Integer RecherchePersonnes;

		public Integer getRecherchePersonnes() {
			return this.RecherchePersonnes;
		}

		public Integer AidesPersonne;

		public Integer getAidesPersonne() {
			return this.AidesPersonne;
		}

		public Integer SecoursPersonne;

		public Integer getSecoursPersonne() {
			return this.SecoursPersonne;
		}

		public Integer AccientsRoutiers;

		public Integer getAccientsRoutiers() {
			return this.AccientsRoutiers;
		}

		public Integer AccidentsFerroviaires;

		public Integer getAccidentsFerroviaires() {
			return this.AccidentsFerroviaires;
		}

		public Integer AccidentsAeriens;

		public Integer getAccidentsAeriens() {
			return this.AccidentsAeriens;
		}

		public Integer AccidentsNavigation;

		public Integer getAccidentsNavigation() {
			return this.AccidentsNavigation;
		}

		public Integer AccidentsTeleportage;

		public Integer getAccidentsTeleportage() {
			return this.AccidentsTeleportage;
		}

		public Integer AccidentsCirculation;

		public Integer getAccidentsCirculation() {
			return this.AccidentsCirculation;
		}

		public Integer OdeursFuiteGaz;

		public Integer getOdeursFuiteGaz() {
			return this.OdeursFuiteGaz;
		}

		public Integer OdeursAutres;

		public Integer getOdeursAutres() {
			return this.OdeursAutres;
		}

		public Integer FaitDusElectricite;

		public Integer getFaitDusElectricite() {
			return this.FaitDusElectricite;
		}

		public Integer PollutionsContaminations;

		public Integer getPollutionsContaminations() {
			return this.PollutionsContaminations;
		}

		public Integer AutresRisquesTechnologiques;

		public Integer getAutresRisquesTechnologiques() {
			return this.AutresRisquesTechnologiques;
		}

		public Integer RisquesTechnologiques;

		public Integer getRisquesTechnologiques() {
			return this.RisquesTechnologiques;
		}

		public Integer FuitesEau;

		public Integer getFuitesEau() {
			return this.FuitesEau;
		}

		public Integer Inondations;

		public Integer getInondations() {
			return this.Inondations;
		}

		public Integer OuverturesPortes;

		public Integer getOuverturesPortes() {
			return this.OuverturesPortes;
		}

		public Integer RecherchesObjets;

		public Integer getRecherchesObjets() {
			return this.RecherchesObjets;
		}

		public Integer BruitsSuspects;

		public Integer getBruitsSuspects() {
			return this.BruitsSuspects;
		}

		public Integer ProtectionBiens;

		public Integer getProtectionBiens() {
			return this.ProtectionBiens;
		}

		public Integer FaussesAlertes;

		public Integer getFaussesAlertes() {
			return this.FaussesAlertes;
		}

		public Integer FaussesAlertesDAAF;

		public Integer getFaussesAlertesDAAF() {
			return this.FaussesAlertesDAAF;
		}

		public Integer FaitsAnimaux;

		public Integer getFaitsAnimaux() {
			return this.FaitsAnimaux;
		}

		public Integer FaitsHymenopteres;

		public Integer getFaitsHymenopteres() {
			return this.FaitsHymenopteres;
		}

		public Integer DegagementsVoiePublique;

		public Integer getDegagementsVoiePublique() {
			return this.DegagementsVoiePublique;
		}

		public Integer NettoyagesVoiePublique;

		public Integer getNettoyagesVoiePublique() {
			return this.NettoyagesVoiePublique;
		}

		public Integer Eboulements;

		public Integer getEboulements() {
			return this.Eboulements;
		}

		public Integer DeposesObjets;

		public Integer getDeposesObjets() {
			return this.DeposesObjets;
		}

		public Integer PiquetsSecuriteSurveillances;

		public Integer getPiquetsSecuriteSurveillances() {
			return this.PiquetsSecuriteSurveillances;
		}

		public Integer EnginsExplosifs;

		public Integer getEnginsExplosifs() {
			return this.EnginsExplosifs;
		}

		public Integer AutresOperationsDiverses;

		public Integer getAutresOperationsDiverses() {
			return this.AutresOperationsDiverses;
		}

		public Integer Divers;

		public Integer getDivers() {
			return this.Divers;
		}

		public Integer OperationsDiverses;

		public Integer getOperationsDiverses() {
			return this.OperationsDiverses;
		}

		public Integer TotalInterventions;

		public Integer getTotalInterventions() {
			return this.TotalInterventions;
		}

		private Integer readInteger(ObjectInputStream dis) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private Integer readInteger(org.jboss.marshalling.Unmarshaller dis) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private void writeInteger(Integer intNum, ObjectOutputStream dos) throws IOException {
			if (intNum == null) {
				dos.writeByte(-1);
			} else {
				dos.writeByte(0);
				dos.writeInt(intNum);
			}
		}

		private void writeInteger(Integer intNum, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (intNum == null) {
				marshaller.writeByte(-1);
			} else {
				marshaller.writeByte(0);
				marshaller.writeInt(intNum);
			}
		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_DATAMSPR_Intervention.length) {
					if (length < 1024 && commonByteArray_DATAMSPR_Intervention.length == 0) {
						commonByteArray_DATAMSPR_Intervention = new byte[1024];
					} else {
						commonByteArray_DATAMSPR_Intervention = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_DATAMSPR_Intervention, 0, length);
				strReturn = new String(commonByteArray_DATAMSPR_Intervention, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private String readString(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			String strReturn = null;
			int length = 0;
			length = unmarshaller.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_DATAMSPR_Intervention.length) {
					if (length < 1024 && commonByteArray_DATAMSPR_Intervention.length == 0) {
						commonByteArray_DATAMSPR_Intervention = new byte[1024];
					} else {
						commonByteArray_DATAMSPR_Intervention = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_DATAMSPR_Intervention, 0, length);
				strReturn = new String(commonByteArray_DATAMSPR_Intervention, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, ObjectOutputStream dos) throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		private void writeString(String str, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (str == null) {
				marshaller.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				marshaller.writeInt(byteArray.length);
				marshaller.write(byteArray);
			}
		}

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_DATAMSPR_Intervention) {

				try {

					int length = 0;

					this.Annee = readInteger(dis);

					this.Zone = readString(dis);

					this.Region = readString(dis);

					this.Numero = readString(dis);

					this.Departement = readString(dis);

					this.CategorieA = readString(dis);

					this.FeuxHabitationsBureaux = readInteger(dis);

					this.FeuxCheminees = readInteger(dis);

					this.FeuxAvecLocalSommeil = readInteger(dis);

					this.FeuxSansLocalSommeil = readInteger(dis);

					this.FeuxLocalIndustriels = readInteger(dis);

					this.FeuxLocauxArtisanaux = readInteger(dis);

					this.FeuxLocauxAgricoles = readInteger(dis);

					this.FeuxVoiePublique = readInteger(dis);

					this.FeuxVehicules = readInteger(dis);

					this.FeuxVegetations = readInteger(dis);

					this.AutresFeux = readInteger(dis);

					this.Incendies = readInteger(dis);

					this.AccidentsLieuxTravail = readInteger(dis);

					this.AccidentsDomicile = readInteger(dis);

					this.AccidentsSport = readInteger(dis);

					this.AccidentsVoiePublique = readInteger(dis);

					this.SecoursMontagne = readInteger(dis);

					this.MalaisesLieuxTravail = readInteger(dis);

					this.MalaisesDomicileVitale = readInteger(dis);

					this.MalaisesDomicileCarence = readInteger(dis);

					this.MalaisesSport = readInteger(dis);

					this.MalaisesVoiePublique = readInteger(dis);

					this.Autolyses = readInteger(dis);

					this.SecoursEauxInterieuresPiscines = readInteger(dis);

					this.SecoursMer = readInteger(dis);

					this.Intoxications = readInteger(dis);

					this.IntoxicationsCO = readInteger(dis);

					this.AutresSAV = readInteger(dis);

					this.SecoursVictime = readInteger(dis);

					this.RelevagePersonnes = readInteger(dis);

					this.RecherchePersonnes = readInteger(dis);

					this.AidesPersonne = readInteger(dis);

					this.SecoursPersonne = readInteger(dis);

					this.AccientsRoutiers = readInteger(dis);

					this.AccidentsFerroviaires = readInteger(dis);

					this.AccidentsAeriens = readInteger(dis);

					this.AccidentsNavigation = readInteger(dis);

					this.AccidentsTeleportage = readInteger(dis);

					this.AccidentsCirculation = readInteger(dis);

					this.OdeursFuiteGaz = readInteger(dis);

					this.OdeursAutres = readInteger(dis);

					this.FaitDusElectricite = readInteger(dis);

					this.PollutionsContaminations = readInteger(dis);

					this.AutresRisquesTechnologiques = readInteger(dis);

					this.RisquesTechnologiques = readInteger(dis);

					this.FuitesEau = readInteger(dis);

					this.Inondations = readInteger(dis);

					this.OuverturesPortes = readInteger(dis);

					this.RecherchesObjets = readInteger(dis);

					this.BruitsSuspects = readInteger(dis);

					this.ProtectionBiens = readInteger(dis);

					this.FaussesAlertes = readInteger(dis);

					this.FaussesAlertesDAAF = readInteger(dis);

					this.FaitsAnimaux = readInteger(dis);

					this.FaitsHymenopteres = readInteger(dis);

					this.DegagementsVoiePublique = readInteger(dis);

					this.NettoyagesVoiePublique = readInteger(dis);

					this.Eboulements = readInteger(dis);

					this.DeposesObjets = readInteger(dis);

					this.PiquetsSecuriteSurveillances = readInteger(dis);

					this.EnginsExplosifs = readInteger(dis);

					this.AutresOperationsDiverses = readInteger(dis);

					this.Divers = readInteger(dis);

					this.OperationsDiverses = readInteger(dis);

					this.TotalInterventions = readInteger(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_DATAMSPR_Intervention) {

				try {

					int length = 0;

					this.Annee = readInteger(dis);

					this.Zone = readString(dis);

					this.Region = readString(dis);

					this.Numero = readString(dis);

					this.Departement = readString(dis);

					this.CategorieA = readString(dis);

					this.FeuxHabitationsBureaux = readInteger(dis);

					this.FeuxCheminees = readInteger(dis);

					this.FeuxAvecLocalSommeil = readInteger(dis);

					this.FeuxSansLocalSommeil = readInteger(dis);

					this.FeuxLocalIndustriels = readInteger(dis);

					this.FeuxLocauxArtisanaux = readInteger(dis);

					this.FeuxLocauxAgricoles = readInteger(dis);

					this.FeuxVoiePublique = readInteger(dis);

					this.FeuxVehicules = readInteger(dis);

					this.FeuxVegetations = readInteger(dis);

					this.AutresFeux = readInteger(dis);

					this.Incendies = readInteger(dis);

					this.AccidentsLieuxTravail = readInteger(dis);

					this.AccidentsDomicile = readInteger(dis);

					this.AccidentsSport = readInteger(dis);

					this.AccidentsVoiePublique = readInteger(dis);

					this.SecoursMontagne = readInteger(dis);

					this.MalaisesLieuxTravail = readInteger(dis);

					this.MalaisesDomicileVitale = readInteger(dis);

					this.MalaisesDomicileCarence = readInteger(dis);

					this.MalaisesSport = readInteger(dis);

					this.MalaisesVoiePublique = readInteger(dis);

					this.Autolyses = readInteger(dis);

					this.SecoursEauxInterieuresPiscines = readInteger(dis);

					this.SecoursMer = readInteger(dis);

					this.Intoxications = readInteger(dis);

					this.IntoxicationsCO = readInteger(dis);

					this.AutresSAV = readInteger(dis);

					this.SecoursVictime = readInteger(dis);

					this.RelevagePersonnes = readInteger(dis);

					this.RecherchePersonnes = readInteger(dis);

					this.AidesPersonne = readInteger(dis);

					this.SecoursPersonne = readInteger(dis);

					this.AccientsRoutiers = readInteger(dis);

					this.AccidentsFerroviaires = readInteger(dis);

					this.AccidentsAeriens = readInteger(dis);

					this.AccidentsNavigation = readInteger(dis);

					this.AccidentsTeleportage = readInteger(dis);

					this.AccidentsCirculation = readInteger(dis);

					this.OdeursFuiteGaz = readInteger(dis);

					this.OdeursAutres = readInteger(dis);

					this.FaitDusElectricite = readInteger(dis);

					this.PollutionsContaminations = readInteger(dis);

					this.AutresRisquesTechnologiques = readInteger(dis);

					this.RisquesTechnologiques = readInteger(dis);

					this.FuitesEau = readInteger(dis);

					this.Inondations = readInteger(dis);

					this.OuverturesPortes = readInteger(dis);

					this.RecherchesObjets = readInteger(dis);

					this.BruitsSuspects = readInteger(dis);

					this.ProtectionBiens = readInteger(dis);

					this.FaussesAlertes = readInteger(dis);

					this.FaussesAlertesDAAF = readInteger(dis);

					this.FaitsAnimaux = readInteger(dis);

					this.FaitsHymenopteres = readInteger(dis);

					this.DegagementsVoiePublique = readInteger(dis);

					this.NettoyagesVoiePublique = readInteger(dis);

					this.Eboulements = readInteger(dis);

					this.DeposesObjets = readInteger(dis);

					this.PiquetsSecuriteSurveillances = readInteger(dis);

					this.EnginsExplosifs = readInteger(dis);

					this.AutresOperationsDiverses = readInteger(dis);

					this.Divers = readInteger(dis);

					this.OperationsDiverses = readInteger(dis);

					this.TotalInterventions = readInteger(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// Integer

				writeInteger(this.Annee, dos);

				// String

				writeString(this.Zone, dos);

				// String

				writeString(this.Region, dos);

				// String

				writeString(this.Numero, dos);

				// String

				writeString(this.Departement, dos);

				// String

				writeString(this.CategorieA, dos);

				// Integer

				writeInteger(this.FeuxHabitationsBureaux, dos);

				// Integer

				writeInteger(this.FeuxCheminees, dos);

				// Integer

				writeInteger(this.FeuxAvecLocalSommeil, dos);

				// Integer

				writeInteger(this.FeuxSansLocalSommeil, dos);

				// Integer

				writeInteger(this.FeuxLocalIndustriels, dos);

				// Integer

				writeInteger(this.FeuxLocauxArtisanaux, dos);

				// Integer

				writeInteger(this.FeuxLocauxAgricoles, dos);

				// Integer

				writeInteger(this.FeuxVoiePublique, dos);

				// Integer

				writeInteger(this.FeuxVehicules, dos);

				// Integer

				writeInteger(this.FeuxVegetations, dos);

				// Integer

				writeInteger(this.AutresFeux, dos);

				// Integer

				writeInteger(this.Incendies, dos);

				// Integer

				writeInteger(this.AccidentsLieuxTravail, dos);

				// Integer

				writeInteger(this.AccidentsDomicile, dos);

				// Integer

				writeInteger(this.AccidentsSport, dos);

				// Integer

				writeInteger(this.AccidentsVoiePublique, dos);

				// Integer

				writeInteger(this.SecoursMontagne, dos);

				// Integer

				writeInteger(this.MalaisesLieuxTravail, dos);

				// Integer

				writeInteger(this.MalaisesDomicileVitale, dos);

				// Integer

				writeInteger(this.MalaisesDomicileCarence, dos);

				// Integer

				writeInteger(this.MalaisesSport, dos);

				// Integer

				writeInteger(this.MalaisesVoiePublique, dos);

				// Integer

				writeInteger(this.Autolyses, dos);

				// Integer

				writeInteger(this.SecoursEauxInterieuresPiscines, dos);

				// Integer

				writeInteger(this.SecoursMer, dos);

				// Integer

				writeInteger(this.Intoxications, dos);

				// Integer

				writeInteger(this.IntoxicationsCO, dos);

				// Integer

				writeInteger(this.AutresSAV, dos);

				// Integer

				writeInteger(this.SecoursVictime, dos);

				// Integer

				writeInteger(this.RelevagePersonnes, dos);

				// Integer

				writeInteger(this.RecherchePersonnes, dos);

				// Integer

				writeInteger(this.AidesPersonne, dos);

				// Integer

				writeInteger(this.SecoursPersonne, dos);

				// Integer

				writeInteger(this.AccientsRoutiers, dos);

				// Integer

				writeInteger(this.AccidentsFerroviaires, dos);

				// Integer

				writeInteger(this.AccidentsAeriens, dos);

				// Integer

				writeInteger(this.AccidentsNavigation, dos);

				// Integer

				writeInteger(this.AccidentsTeleportage, dos);

				// Integer

				writeInteger(this.AccidentsCirculation, dos);

				// Integer

				writeInteger(this.OdeursFuiteGaz, dos);

				// Integer

				writeInteger(this.OdeursAutres, dos);

				// Integer

				writeInteger(this.FaitDusElectricite, dos);

				// Integer

				writeInteger(this.PollutionsContaminations, dos);

				// Integer

				writeInteger(this.AutresRisquesTechnologiques, dos);

				// Integer

				writeInteger(this.RisquesTechnologiques, dos);

				// Integer

				writeInteger(this.FuitesEau, dos);

				// Integer

				writeInteger(this.Inondations, dos);

				// Integer

				writeInteger(this.OuverturesPortes, dos);

				// Integer

				writeInteger(this.RecherchesObjets, dos);

				// Integer

				writeInteger(this.BruitsSuspects, dos);

				// Integer

				writeInteger(this.ProtectionBiens, dos);

				// Integer

				writeInteger(this.FaussesAlertes, dos);

				// Integer

				writeInteger(this.FaussesAlertesDAAF, dos);

				// Integer

				writeInteger(this.FaitsAnimaux, dos);

				// Integer

				writeInteger(this.FaitsHymenopteres, dos);

				// Integer

				writeInteger(this.DegagementsVoiePublique, dos);

				// Integer

				writeInteger(this.NettoyagesVoiePublique, dos);

				// Integer

				writeInteger(this.Eboulements, dos);

				// Integer

				writeInteger(this.DeposesObjets, dos);

				// Integer

				writeInteger(this.PiquetsSecuriteSurveillances, dos);

				// Integer

				writeInteger(this.EnginsExplosifs, dos);

				// Integer

				writeInteger(this.AutresOperationsDiverses, dos);

				// Integer

				writeInteger(this.Divers, dos);

				// Integer

				writeInteger(this.OperationsDiverses, dos);

				// Integer

				writeInteger(this.TotalInterventions, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// Integer

				writeInteger(this.Annee, dos);

				// String

				writeString(this.Zone, dos);

				// String

				writeString(this.Region, dos);

				// String

				writeString(this.Numero, dos);

				// String

				writeString(this.Departement, dos);

				// String

				writeString(this.CategorieA, dos);

				// Integer

				writeInteger(this.FeuxHabitationsBureaux, dos);

				// Integer

				writeInteger(this.FeuxCheminees, dos);

				// Integer

				writeInteger(this.FeuxAvecLocalSommeil, dos);

				// Integer

				writeInteger(this.FeuxSansLocalSommeil, dos);

				// Integer

				writeInteger(this.FeuxLocalIndustriels, dos);

				// Integer

				writeInteger(this.FeuxLocauxArtisanaux, dos);

				// Integer

				writeInteger(this.FeuxLocauxAgricoles, dos);

				// Integer

				writeInteger(this.FeuxVoiePublique, dos);

				// Integer

				writeInteger(this.FeuxVehicules, dos);

				// Integer

				writeInteger(this.FeuxVegetations, dos);

				// Integer

				writeInteger(this.AutresFeux, dos);

				// Integer

				writeInteger(this.Incendies, dos);

				// Integer

				writeInteger(this.AccidentsLieuxTravail, dos);

				// Integer

				writeInteger(this.AccidentsDomicile, dos);

				// Integer

				writeInteger(this.AccidentsSport, dos);

				// Integer

				writeInteger(this.AccidentsVoiePublique, dos);

				// Integer

				writeInteger(this.SecoursMontagne, dos);

				// Integer

				writeInteger(this.MalaisesLieuxTravail, dos);

				// Integer

				writeInteger(this.MalaisesDomicileVitale, dos);

				// Integer

				writeInteger(this.MalaisesDomicileCarence, dos);

				// Integer

				writeInteger(this.MalaisesSport, dos);

				// Integer

				writeInteger(this.MalaisesVoiePublique, dos);

				// Integer

				writeInteger(this.Autolyses, dos);

				// Integer

				writeInteger(this.SecoursEauxInterieuresPiscines, dos);

				// Integer

				writeInteger(this.SecoursMer, dos);

				// Integer

				writeInteger(this.Intoxications, dos);

				// Integer

				writeInteger(this.IntoxicationsCO, dos);

				// Integer

				writeInteger(this.AutresSAV, dos);

				// Integer

				writeInteger(this.SecoursVictime, dos);

				// Integer

				writeInteger(this.RelevagePersonnes, dos);

				// Integer

				writeInteger(this.RecherchePersonnes, dos);

				// Integer

				writeInteger(this.AidesPersonne, dos);

				// Integer

				writeInteger(this.SecoursPersonne, dos);

				// Integer

				writeInteger(this.AccientsRoutiers, dos);

				// Integer

				writeInteger(this.AccidentsFerroviaires, dos);

				// Integer

				writeInteger(this.AccidentsAeriens, dos);

				// Integer

				writeInteger(this.AccidentsNavigation, dos);

				// Integer

				writeInteger(this.AccidentsTeleportage, dos);

				// Integer

				writeInteger(this.AccidentsCirculation, dos);

				// Integer

				writeInteger(this.OdeursFuiteGaz, dos);

				// Integer

				writeInteger(this.OdeursAutres, dos);

				// Integer

				writeInteger(this.FaitDusElectricite, dos);

				// Integer

				writeInteger(this.PollutionsContaminations, dos);

				// Integer

				writeInteger(this.AutresRisquesTechnologiques, dos);

				// Integer

				writeInteger(this.RisquesTechnologiques, dos);

				// Integer

				writeInteger(this.FuitesEau, dos);

				// Integer

				writeInteger(this.Inondations, dos);

				// Integer

				writeInteger(this.OuverturesPortes, dos);

				// Integer

				writeInteger(this.RecherchesObjets, dos);

				// Integer

				writeInteger(this.BruitsSuspects, dos);

				// Integer

				writeInteger(this.ProtectionBiens, dos);

				// Integer

				writeInteger(this.FaussesAlertes, dos);

				// Integer

				writeInteger(this.FaussesAlertesDAAF, dos);

				// Integer

				writeInteger(this.FaitsAnimaux, dos);

				// Integer

				writeInteger(this.FaitsHymenopteres, dos);

				// Integer

				writeInteger(this.DegagementsVoiePublique, dos);

				// Integer

				writeInteger(this.NettoyagesVoiePublique, dos);

				// Integer

				writeInteger(this.Eboulements, dos);

				// Integer

				writeInteger(this.DeposesObjets, dos);

				// Integer

				writeInteger(this.PiquetsSecuriteSurveillances, dos);

				// Integer

				writeInteger(this.EnginsExplosifs, dos);

				// Integer

				writeInteger(this.AutresOperationsDiverses, dos);

				// Integer

				writeInteger(this.Divers, dos);

				// Integer

				writeInteger(this.OperationsDiverses, dos);

				// Integer

				writeInteger(this.TotalInterventions, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("Annee=" + String.valueOf(Annee));
			sb.append(",Zone=" + Zone);
			sb.append(",Region=" + Region);
			sb.append(",Numero=" + Numero);
			sb.append(",Departement=" + Departement);
			sb.append(",CategorieA=" + CategorieA);
			sb.append(",FeuxHabitationsBureaux=" + String.valueOf(FeuxHabitationsBureaux));
			sb.append(",FeuxCheminees=" + String.valueOf(FeuxCheminees));
			sb.append(",FeuxAvecLocalSommeil=" + String.valueOf(FeuxAvecLocalSommeil));
			sb.append(",FeuxSansLocalSommeil=" + String.valueOf(FeuxSansLocalSommeil));
			sb.append(",FeuxLocalIndustriels=" + String.valueOf(FeuxLocalIndustriels));
			sb.append(",FeuxLocauxArtisanaux=" + String.valueOf(FeuxLocauxArtisanaux));
			sb.append(",FeuxLocauxAgricoles=" + String.valueOf(FeuxLocauxAgricoles));
			sb.append(",FeuxVoiePublique=" + String.valueOf(FeuxVoiePublique));
			sb.append(",FeuxVehicules=" + String.valueOf(FeuxVehicules));
			sb.append(",FeuxVegetations=" + String.valueOf(FeuxVegetations));
			sb.append(",AutresFeux=" + String.valueOf(AutresFeux));
			sb.append(",Incendies=" + String.valueOf(Incendies));
			sb.append(",AccidentsLieuxTravail=" + String.valueOf(AccidentsLieuxTravail));
			sb.append(",AccidentsDomicile=" + String.valueOf(AccidentsDomicile));
			sb.append(",AccidentsSport=" + String.valueOf(AccidentsSport));
			sb.append(",AccidentsVoiePublique=" + String.valueOf(AccidentsVoiePublique));
			sb.append(",SecoursMontagne=" + String.valueOf(SecoursMontagne));
			sb.append(",MalaisesLieuxTravail=" + String.valueOf(MalaisesLieuxTravail));
			sb.append(",MalaisesDomicileVitale=" + String.valueOf(MalaisesDomicileVitale));
			sb.append(",MalaisesDomicileCarence=" + String.valueOf(MalaisesDomicileCarence));
			sb.append(",MalaisesSport=" + String.valueOf(MalaisesSport));
			sb.append(",MalaisesVoiePublique=" + String.valueOf(MalaisesVoiePublique));
			sb.append(",Autolyses=" + String.valueOf(Autolyses));
			sb.append(",SecoursEauxInterieuresPiscines=" + String.valueOf(SecoursEauxInterieuresPiscines));
			sb.append(",SecoursMer=" + String.valueOf(SecoursMer));
			sb.append(",Intoxications=" + String.valueOf(Intoxications));
			sb.append(",IntoxicationsCO=" + String.valueOf(IntoxicationsCO));
			sb.append(",AutresSAV=" + String.valueOf(AutresSAV));
			sb.append(",SecoursVictime=" + String.valueOf(SecoursVictime));
			sb.append(",RelevagePersonnes=" + String.valueOf(RelevagePersonnes));
			sb.append(",RecherchePersonnes=" + String.valueOf(RecherchePersonnes));
			sb.append(",AidesPersonne=" + String.valueOf(AidesPersonne));
			sb.append(",SecoursPersonne=" + String.valueOf(SecoursPersonne));
			sb.append(",AccientsRoutiers=" + String.valueOf(AccientsRoutiers));
			sb.append(",AccidentsFerroviaires=" + String.valueOf(AccidentsFerroviaires));
			sb.append(",AccidentsAeriens=" + String.valueOf(AccidentsAeriens));
			sb.append(",AccidentsNavigation=" + String.valueOf(AccidentsNavigation));
			sb.append(",AccidentsTeleportage=" + String.valueOf(AccidentsTeleportage));
			sb.append(",AccidentsCirculation=" + String.valueOf(AccidentsCirculation));
			sb.append(",OdeursFuiteGaz=" + String.valueOf(OdeursFuiteGaz));
			sb.append(",OdeursAutres=" + String.valueOf(OdeursAutres));
			sb.append(",FaitDusElectricite=" + String.valueOf(FaitDusElectricite));
			sb.append(",PollutionsContaminations=" + String.valueOf(PollutionsContaminations));
			sb.append(",AutresRisquesTechnologiques=" + String.valueOf(AutresRisquesTechnologiques));
			sb.append(",RisquesTechnologiques=" + String.valueOf(RisquesTechnologiques));
			sb.append(",FuitesEau=" + String.valueOf(FuitesEau));
			sb.append(",Inondations=" + String.valueOf(Inondations));
			sb.append(",OuverturesPortes=" + String.valueOf(OuverturesPortes));
			sb.append(",RecherchesObjets=" + String.valueOf(RecherchesObjets));
			sb.append(",BruitsSuspects=" + String.valueOf(BruitsSuspects));
			sb.append(",ProtectionBiens=" + String.valueOf(ProtectionBiens));
			sb.append(",FaussesAlertes=" + String.valueOf(FaussesAlertes));
			sb.append(",FaussesAlertesDAAF=" + String.valueOf(FaussesAlertesDAAF));
			sb.append(",FaitsAnimaux=" + String.valueOf(FaitsAnimaux));
			sb.append(",FaitsHymenopteres=" + String.valueOf(FaitsHymenopteres));
			sb.append(",DegagementsVoiePublique=" + String.valueOf(DegagementsVoiePublique));
			sb.append(",NettoyagesVoiePublique=" + String.valueOf(NettoyagesVoiePublique));
			sb.append(",Eboulements=" + String.valueOf(Eboulements));
			sb.append(",DeposesObjets=" + String.valueOf(DeposesObjets));
			sb.append(",PiquetsSecuriteSurveillances=" + String.valueOf(PiquetsSecuriteSurveillances));
			sb.append(",EnginsExplosifs=" + String.valueOf(EnginsExplosifs));
			sb.append(",AutresOperationsDiverses=" + String.valueOf(AutresOperationsDiverses));
			sb.append(",Divers=" + String.valueOf(Divers));
			sb.append(",OperationsDiverses=" + String.valueOf(OperationsDiverses));
			sb.append(",TotalInterventions=" + String.valueOf(TotalInterventions));
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(row2Struct other) {

			int returnValue = -1;

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(), object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public static class row1Struct implements routines.system.IPersistableRow<row1Struct> {
		final static byte[] commonByteArrayLock_DATAMSPR_Intervention = new byte[0];
		static byte[] commonByteArray_DATAMSPR_Intervention = new byte[0];

		public Integer Annee;

		public Integer getAnnee() {
			return this.Annee;
		}

		public String Zone;

		public String getZone() {
			return this.Zone;
		}

		public String Region;

		public String getRegion() {
			return this.Region;
		}

		public String Numero;

		public String getNumero() {
			return this.Numero;
		}

		public String Departement;

		public String getDepartement() {
			return this.Departement;
		}

		public String CategorieA;

		public String getCategorieA() {
			return this.CategorieA;
		}

		public Integer FeuxHabitationsBureaux;

		public Integer getFeuxHabitationsBureaux() {
			return this.FeuxHabitationsBureaux;
		}

		public Integer FeuxCheminees;

		public Integer getFeuxCheminees() {
			return this.FeuxCheminees;
		}

		public Integer FeuxAvecLocalSommeil;

		public Integer getFeuxAvecLocalSommeil() {
			return this.FeuxAvecLocalSommeil;
		}

		public Integer FeuxSansLocalSommeil;

		public Integer getFeuxSansLocalSommeil() {
			return this.FeuxSansLocalSommeil;
		}

		public Integer FeuxLocalIndustriels;

		public Integer getFeuxLocalIndustriels() {
			return this.FeuxLocalIndustriels;
		}

		public Integer FeuxLocauxArtisanaux;

		public Integer getFeuxLocauxArtisanaux() {
			return this.FeuxLocauxArtisanaux;
		}

		public Integer FeuxLocauxAgricoles;

		public Integer getFeuxLocauxAgricoles() {
			return this.FeuxLocauxAgricoles;
		}

		public Integer FeuxVoiePublique;

		public Integer getFeuxVoiePublique() {
			return this.FeuxVoiePublique;
		}

		public Integer FeuxVehicules;

		public Integer getFeuxVehicules() {
			return this.FeuxVehicules;
		}

		public Integer FeuxVegetations;

		public Integer getFeuxVegetations() {
			return this.FeuxVegetations;
		}

		public Integer AutresFeux;

		public Integer getAutresFeux() {
			return this.AutresFeux;
		}

		public Integer Incendies;

		public Integer getIncendies() {
			return this.Incendies;
		}

		public Integer AccidentsLieuxTravail;

		public Integer getAccidentsLieuxTravail() {
			return this.AccidentsLieuxTravail;
		}

		public Integer AccidentsDomicile;

		public Integer getAccidentsDomicile() {
			return this.AccidentsDomicile;
		}

		public Integer AccidentsSport;

		public Integer getAccidentsSport() {
			return this.AccidentsSport;
		}

		public Integer AccidentsVoiePublique;

		public Integer getAccidentsVoiePublique() {
			return this.AccidentsVoiePublique;
		}

		public Integer SecoursMontagne;

		public Integer getSecoursMontagne() {
			return this.SecoursMontagne;
		}

		public Integer MalaisesLieuxTravail;

		public Integer getMalaisesLieuxTravail() {
			return this.MalaisesLieuxTravail;
		}

		public Integer MalaisesDomicileVitale;

		public Integer getMalaisesDomicileVitale() {
			return this.MalaisesDomicileVitale;
		}

		public Integer MalaisesDomicileCarence;

		public Integer getMalaisesDomicileCarence() {
			return this.MalaisesDomicileCarence;
		}

		public Integer MalaisesSport;

		public Integer getMalaisesSport() {
			return this.MalaisesSport;
		}

		public Integer MalaisesVoiePublique;

		public Integer getMalaisesVoiePublique() {
			return this.MalaisesVoiePublique;
		}

		public Integer Autolyses;

		public Integer getAutolyses() {
			return this.Autolyses;
		}

		public Integer SecoursEauxInterieuresPiscines;

		public Integer getSecoursEauxInterieuresPiscines() {
			return this.SecoursEauxInterieuresPiscines;
		}

		public Integer SecoursMer;

		public Integer getSecoursMer() {
			return this.SecoursMer;
		}

		public Integer Intoxications;

		public Integer getIntoxications() {
			return this.Intoxications;
		}

		public Integer IntoxicationsCO;

		public Integer getIntoxicationsCO() {
			return this.IntoxicationsCO;
		}

		public Integer AutresSAV;

		public Integer getAutresSAV() {
			return this.AutresSAV;
		}

		public Integer SecoursVictime;

		public Integer getSecoursVictime() {
			return this.SecoursVictime;
		}

		public Integer RelevagePersonnes;

		public Integer getRelevagePersonnes() {
			return this.RelevagePersonnes;
		}

		public Integer RecherchePersonnes;

		public Integer getRecherchePersonnes() {
			return this.RecherchePersonnes;
		}

		public Integer AidesPersonne;

		public Integer getAidesPersonne() {
			return this.AidesPersonne;
		}

		public Integer SecoursPersonne;

		public Integer getSecoursPersonne() {
			return this.SecoursPersonne;
		}

		public Integer AccientsRoutiers;

		public Integer getAccientsRoutiers() {
			return this.AccientsRoutiers;
		}

		public Integer AccidentsFerroviaires;

		public Integer getAccidentsFerroviaires() {
			return this.AccidentsFerroviaires;
		}

		public Integer AccidentsAeriens;

		public Integer getAccidentsAeriens() {
			return this.AccidentsAeriens;
		}

		public Integer AccidentsNavigation;

		public Integer getAccidentsNavigation() {
			return this.AccidentsNavigation;
		}

		public Integer AccidentsTeleportage;

		public Integer getAccidentsTeleportage() {
			return this.AccidentsTeleportage;
		}

		public Integer AccidentsCirculation;

		public Integer getAccidentsCirculation() {
			return this.AccidentsCirculation;
		}

		public Integer OdeursFuiteGaz;

		public Integer getOdeursFuiteGaz() {
			return this.OdeursFuiteGaz;
		}

		public Integer OdeursAutres;

		public Integer getOdeursAutres() {
			return this.OdeursAutres;
		}

		public Integer FaitDusElectricite;

		public Integer getFaitDusElectricite() {
			return this.FaitDusElectricite;
		}

		public Integer PollutionsContaminations;

		public Integer getPollutionsContaminations() {
			return this.PollutionsContaminations;
		}

		public Integer AutresRisquesTechnologiques;

		public Integer getAutresRisquesTechnologiques() {
			return this.AutresRisquesTechnologiques;
		}

		public Integer RisquesTechnologiques;

		public Integer getRisquesTechnologiques() {
			return this.RisquesTechnologiques;
		}

		public Integer FuitesEau;

		public Integer getFuitesEau() {
			return this.FuitesEau;
		}

		public Integer Inondations;

		public Integer getInondations() {
			return this.Inondations;
		}

		public Integer OuverturesPortes;

		public Integer getOuverturesPortes() {
			return this.OuverturesPortes;
		}

		public Integer RecherchesObjets;

		public Integer getRecherchesObjets() {
			return this.RecherchesObjets;
		}

		public Integer BruitsSuspects;

		public Integer getBruitsSuspects() {
			return this.BruitsSuspects;
		}

		public Integer ProtectionBiens;

		public Integer getProtectionBiens() {
			return this.ProtectionBiens;
		}

		public Integer FaussesAlertes;

		public Integer getFaussesAlertes() {
			return this.FaussesAlertes;
		}

		public Integer FaussesAlertesDAAF;

		public Integer getFaussesAlertesDAAF() {
			return this.FaussesAlertesDAAF;
		}

		public Integer FaitsAnimaux;

		public Integer getFaitsAnimaux() {
			return this.FaitsAnimaux;
		}

		public Integer FaitsHymenopteres;

		public Integer getFaitsHymenopteres() {
			return this.FaitsHymenopteres;
		}

		public Integer DegagementsVoiePublique;

		public Integer getDegagementsVoiePublique() {
			return this.DegagementsVoiePublique;
		}

		public Integer NettoyagesVoiePublique;

		public Integer getNettoyagesVoiePublique() {
			return this.NettoyagesVoiePublique;
		}

		public Integer Eboulements;

		public Integer getEboulements() {
			return this.Eboulements;
		}

		public Integer DeposesObjets;

		public Integer getDeposesObjets() {
			return this.DeposesObjets;
		}

		public Integer PiquetsSecuriteSurveillances;

		public Integer getPiquetsSecuriteSurveillances() {
			return this.PiquetsSecuriteSurveillances;
		}

		public Integer EnginsExplosifs;

		public Integer getEnginsExplosifs() {
			return this.EnginsExplosifs;
		}

		public Integer AutresOperationsDiverses;

		public Integer getAutresOperationsDiverses() {
			return this.AutresOperationsDiverses;
		}

		public Integer Divers;

		public Integer getDivers() {
			return this.Divers;
		}

		public Integer OperationsDiverses;

		public Integer getOperationsDiverses() {
			return this.OperationsDiverses;
		}

		public Integer TotalInterventions;

		public Integer getTotalInterventions() {
			return this.TotalInterventions;
		}

		private Integer readInteger(ObjectInputStream dis) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private Integer readInteger(org.jboss.marshalling.Unmarshaller dis) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private void writeInteger(Integer intNum, ObjectOutputStream dos) throws IOException {
			if (intNum == null) {
				dos.writeByte(-1);
			} else {
				dos.writeByte(0);
				dos.writeInt(intNum);
			}
		}

		private void writeInteger(Integer intNum, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (intNum == null) {
				marshaller.writeByte(-1);
			} else {
				marshaller.writeByte(0);
				marshaller.writeInt(intNum);
			}
		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_DATAMSPR_Intervention.length) {
					if (length < 1024 && commonByteArray_DATAMSPR_Intervention.length == 0) {
						commonByteArray_DATAMSPR_Intervention = new byte[1024];
					} else {
						commonByteArray_DATAMSPR_Intervention = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_DATAMSPR_Intervention, 0, length);
				strReturn = new String(commonByteArray_DATAMSPR_Intervention, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private String readString(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			String strReturn = null;
			int length = 0;
			length = unmarshaller.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_DATAMSPR_Intervention.length) {
					if (length < 1024 && commonByteArray_DATAMSPR_Intervention.length == 0) {
						commonByteArray_DATAMSPR_Intervention = new byte[1024];
					} else {
						commonByteArray_DATAMSPR_Intervention = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_DATAMSPR_Intervention, 0, length);
				strReturn = new String(commonByteArray_DATAMSPR_Intervention, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, ObjectOutputStream dos) throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		private void writeString(String str, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (str == null) {
				marshaller.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				marshaller.writeInt(byteArray.length);
				marshaller.write(byteArray);
			}
		}

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_DATAMSPR_Intervention) {

				try {

					int length = 0;

					this.Annee = readInteger(dis);

					this.Zone = readString(dis);

					this.Region = readString(dis);

					this.Numero = readString(dis);

					this.Departement = readString(dis);

					this.CategorieA = readString(dis);

					this.FeuxHabitationsBureaux = readInteger(dis);

					this.FeuxCheminees = readInteger(dis);

					this.FeuxAvecLocalSommeil = readInteger(dis);

					this.FeuxSansLocalSommeil = readInteger(dis);

					this.FeuxLocalIndustriels = readInteger(dis);

					this.FeuxLocauxArtisanaux = readInteger(dis);

					this.FeuxLocauxAgricoles = readInteger(dis);

					this.FeuxVoiePublique = readInteger(dis);

					this.FeuxVehicules = readInteger(dis);

					this.FeuxVegetations = readInteger(dis);

					this.AutresFeux = readInteger(dis);

					this.Incendies = readInteger(dis);

					this.AccidentsLieuxTravail = readInteger(dis);

					this.AccidentsDomicile = readInteger(dis);

					this.AccidentsSport = readInteger(dis);

					this.AccidentsVoiePublique = readInteger(dis);

					this.SecoursMontagne = readInteger(dis);

					this.MalaisesLieuxTravail = readInteger(dis);

					this.MalaisesDomicileVitale = readInteger(dis);

					this.MalaisesDomicileCarence = readInteger(dis);

					this.MalaisesSport = readInteger(dis);

					this.MalaisesVoiePublique = readInteger(dis);

					this.Autolyses = readInteger(dis);

					this.SecoursEauxInterieuresPiscines = readInteger(dis);

					this.SecoursMer = readInteger(dis);

					this.Intoxications = readInteger(dis);

					this.IntoxicationsCO = readInteger(dis);

					this.AutresSAV = readInteger(dis);

					this.SecoursVictime = readInteger(dis);

					this.RelevagePersonnes = readInteger(dis);

					this.RecherchePersonnes = readInteger(dis);

					this.AidesPersonne = readInteger(dis);

					this.SecoursPersonne = readInteger(dis);

					this.AccientsRoutiers = readInteger(dis);

					this.AccidentsFerroviaires = readInteger(dis);

					this.AccidentsAeriens = readInteger(dis);

					this.AccidentsNavigation = readInteger(dis);

					this.AccidentsTeleportage = readInteger(dis);

					this.AccidentsCirculation = readInteger(dis);

					this.OdeursFuiteGaz = readInteger(dis);

					this.OdeursAutres = readInteger(dis);

					this.FaitDusElectricite = readInteger(dis);

					this.PollutionsContaminations = readInteger(dis);

					this.AutresRisquesTechnologiques = readInteger(dis);

					this.RisquesTechnologiques = readInteger(dis);

					this.FuitesEau = readInteger(dis);

					this.Inondations = readInteger(dis);

					this.OuverturesPortes = readInteger(dis);

					this.RecherchesObjets = readInteger(dis);

					this.BruitsSuspects = readInteger(dis);

					this.ProtectionBiens = readInteger(dis);

					this.FaussesAlertes = readInteger(dis);

					this.FaussesAlertesDAAF = readInteger(dis);

					this.FaitsAnimaux = readInteger(dis);

					this.FaitsHymenopteres = readInteger(dis);

					this.DegagementsVoiePublique = readInteger(dis);

					this.NettoyagesVoiePublique = readInteger(dis);

					this.Eboulements = readInteger(dis);

					this.DeposesObjets = readInteger(dis);

					this.PiquetsSecuriteSurveillances = readInteger(dis);

					this.EnginsExplosifs = readInteger(dis);

					this.AutresOperationsDiverses = readInteger(dis);

					this.Divers = readInteger(dis);

					this.OperationsDiverses = readInteger(dis);

					this.TotalInterventions = readInteger(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_DATAMSPR_Intervention) {

				try {

					int length = 0;

					this.Annee = readInteger(dis);

					this.Zone = readString(dis);

					this.Region = readString(dis);

					this.Numero = readString(dis);

					this.Departement = readString(dis);

					this.CategorieA = readString(dis);

					this.FeuxHabitationsBureaux = readInteger(dis);

					this.FeuxCheminees = readInteger(dis);

					this.FeuxAvecLocalSommeil = readInteger(dis);

					this.FeuxSansLocalSommeil = readInteger(dis);

					this.FeuxLocalIndustriels = readInteger(dis);

					this.FeuxLocauxArtisanaux = readInteger(dis);

					this.FeuxLocauxAgricoles = readInteger(dis);

					this.FeuxVoiePublique = readInteger(dis);

					this.FeuxVehicules = readInteger(dis);

					this.FeuxVegetations = readInteger(dis);

					this.AutresFeux = readInteger(dis);

					this.Incendies = readInteger(dis);

					this.AccidentsLieuxTravail = readInteger(dis);

					this.AccidentsDomicile = readInteger(dis);

					this.AccidentsSport = readInteger(dis);

					this.AccidentsVoiePublique = readInteger(dis);

					this.SecoursMontagne = readInteger(dis);

					this.MalaisesLieuxTravail = readInteger(dis);

					this.MalaisesDomicileVitale = readInteger(dis);

					this.MalaisesDomicileCarence = readInteger(dis);

					this.MalaisesSport = readInteger(dis);

					this.MalaisesVoiePublique = readInteger(dis);

					this.Autolyses = readInteger(dis);

					this.SecoursEauxInterieuresPiscines = readInteger(dis);

					this.SecoursMer = readInteger(dis);

					this.Intoxications = readInteger(dis);

					this.IntoxicationsCO = readInteger(dis);

					this.AutresSAV = readInteger(dis);

					this.SecoursVictime = readInteger(dis);

					this.RelevagePersonnes = readInteger(dis);

					this.RecherchePersonnes = readInteger(dis);

					this.AidesPersonne = readInteger(dis);

					this.SecoursPersonne = readInteger(dis);

					this.AccientsRoutiers = readInteger(dis);

					this.AccidentsFerroviaires = readInteger(dis);

					this.AccidentsAeriens = readInteger(dis);

					this.AccidentsNavigation = readInteger(dis);

					this.AccidentsTeleportage = readInteger(dis);

					this.AccidentsCirculation = readInteger(dis);

					this.OdeursFuiteGaz = readInteger(dis);

					this.OdeursAutres = readInteger(dis);

					this.FaitDusElectricite = readInteger(dis);

					this.PollutionsContaminations = readInteger(dis);

					this.AutresRisquesTechnologiques = readInteger(dis);

					this.RisquesTechnologiques = readInteger(dis);

					this.FuitesEau = readInteger(dis);

					this.Inondations = readInteger(dis);

					this.OuverturesPortes = readInteger(dis);

					this.RecherchesObjets = readInteger(dis);

					this.BruitsSuspects = readInteger(dis);

					this.ProtectionBiens = readInteger(dis);

					this.FaussesAlertes = readInteger(dis);

					this.FaussesAlertesDAAF = readInteger(dis);

					this.FaitsAnimaux = readInteger(dis);

					this.FaitsHymenopteres = readInteger(dis);

					this.DegagementsVoiePublique = readInteger(dis);

					this.NettoyagesVoiePublique = readInteger(dis);

					this.Eboulements = readInteger(dis);

					this.DeposesObjets = readInteger(dis);

					this.PiquetsSecuriteSurveillances = readInteger(dis);

					this.EnginsExplosifs = readInteger(dis);

					this.AutresOperationsDiverses = readInteger(dis);

					this.Divers = readInteger(dis);

					this.OperationsDiverses = readInteger(dis);

					this.TotalInterventions = readInteger(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// Integer

				writeInteger(this.Annee, dos);

				// String

				writeString(this.Zone, dos);

				// String

				writeString(this.Region, dos);

				// String

				writeString(this.Numero, dos);

				// String

				writeString(this.Departement, dos);

				// String

				writeString(this.CategorieA, dos);

				// Integer

				writeInteger(this.FeuxHabitationsBureaux, dos);

				// Integer

				writeInteger(this.FeuxCheminees, dos);

				// Integer

				writeInteger(this.FeuxAvecLocalSommeil, dos);

				// Integer

				writeInteger(this.FeuxSansLocalSommeil, dos);

				// Integer

				writeInteger(this.FeuxLocalIndustriels, dos);

				// Integer

				writeInteger(this.FeuxLocauxArtisanaux, dos);

				// Integer

				writeInteger(this.FeuxLocauxAgricoles, dos);

				// Integer

				writeInteger(this.FeuxVoiePublique, dos);

				// Integer

				writeInteger(this.FeuxVehicules, dos);

				// Integer

				writeInteger(this.FeuxVegetations, dos);

				// Integer

				writeInteger(this.AutresFeux, dos);

				// Integer

				writeInteger(this.Incendies, dos);

				// Integer

				writeInteger(this.AccidentsLieuxTravail, dos);

				// Integer

				writeInteger(this.AccidentsDomicile, dos);

				// Integer

				writeInteger(this.AccidentsSport, dos);

				// Integer

				writeInteger(this.AccidentsVoiePublique, dos);

				// Integer

				writeInteger(this.SecoursMontagne, dos);

				// Integer

				writeInteger(this.MalaisesLieuxTravail, dos);

				// Integer

				writeInteger(this.MalaisesDomicileVitale, dos);

				// Integer

				writeInteger(this.MalaisesDomicileCarence, dos);

				// Integer

				writeInteger(this.MalaisesSport, dos);

				// Integer

				writeInteger(this.MalaisesVoiePublique, dos);

				// Integer

				writeInteger(this.Autolyses, dos);

				// Integer

				writeInteger(this.SecoursEauxInterieuresPiscines, dos);

				// Integer

				writeInteger(this.SecoursMer, dos);

				// Integer

				writeInteger(this.Intoxications, dos);

				// Integer

				writeInteger(this.IntoxicationsCO, dos);

				// Integer

				writeInteger(this.AutresSAV, dos);

				// Integer

				writeInteger(this.SecoursVictime, dos);

				// Integer

				writeInteger(this.RelevagePersonnes, dos);

				// Integer

				writeInteger(this.RecherchePersonnes, dos);

				// Integer

				writeInteger(this.AidesPersonne, dos);

				// Integer

				writeInteger(this.SecoursPersonne, dos);

				// Integer

				writeInteger(this.AccientsRoutiers, dos);

				// Integer

				writeInteger(this.AccidentsFerroviaires, dos);

				// Integer

				writeInteger(this.AccidentsAeriens, dos);

				// Integer

				writeInteger(this.AccidentsNavigation, dos);

				// Integer

				writeInteger(this.AccidentsTeleportage, dos);

				// Integer

				writeInteger(this.AccidentsCirculation, dos);

				// Integer

				writeInteger(this.OdeursFuiteGaz, dos);

				// Integer

				writeInteger(this.OdeursAutres, dos);

				// Integer

				writeInteger(this.FaitDusElectricite, dos);

				// Integer

				writeInteger(this.PollutionsContaminations, dos);

				// Integer

				writeInteger(this.AutresRisquesTechnologiques, dos);

				// Integer

				writeInteger(this.RisquesTechnologiques, dos);

				// Integer

				writeInteger(this.FuitesEau, dos);

				// Integer

				writeInteger(this.Inondations, dos);

				// Integer

				writeInteger(this.OuverturesPortes, dos);

				// Integer

				writeInteger(this.RecherchesObjets, dos);

				// Integer

				writeInteger(this.BruitsSuspects, dos);

				// Integer

				writeInteger(this.ProtectionBiens, dos);

				// Integer

				writeInteger(this.FaussesAlertes, dos);

				// Integer

				writeInteger(this.FaussesAlertesDAAF, dos);

				// Integer

				writeInteger(this.FaitsAnimaux, dos);

				// Integer

				writeInteger(this.FaitsHymenopteres, dos);

				// Integer

				writeInteger(this.DegagementsVoiePublique, dos);

				// Integer

				writeInteger(this.NettoyagesVoiePublique, dos);

				// Integer

				writeInteger(this.Eboulements, dos);

				// Integer

				writeInteger(this.DeposesObjets, dos);

				// Integer

				writeInteger(this.PiquetsSecuriteSurveillances, dos);

				// Integer

				writeInteger(this.EnginsExplosifs, dos);

				// Integer

				writeInteger(this.AutresOperationsDiverses, dos);

				// Integer

				writeInteger(this.Divers, dos);

				// Integer

				writeInteger(this.OperationsDiverses, dos);

				// Integer

				writeInteger(this.TotalInterventions, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// Integer

				writeInteger(this.Annee, dos);

				// String

				writeString(this.Zone, dos);

				// String

				writeString(this.Region, dos);

				// String

				writeString(this.Numero, dos);

				// String

				writeString(this.Departement, dos);

				// String

				writeString(this.CategorieA, dos);

				// Integer

				writeInteger(this.FeuxHabitationsBureaux, dos);

				// Integer

				writeInteger(this.FeuxCheminees, dos);

				// Integer

				writeInteger(this.FeuxAvecLocalSommeil, dos);

				// Integer

				writeInteger(this.FeuxSansLocalSommeil, dos);

				// Integer

				writeInteger(this.FeuxLocalIndustriels, dos);

				// Integer

				writeInteger(this.FeuxLocauxArtisanaux, dos);

				// Integer

				writeInteger(this.FeuxLocauxAgricoles, dos);

				// Integer

				writeInteger(this.FeuxVoiePublique, dos);

				// Integer

				writeInteger(this.FeuxVehicules, dos);

				// Integer

				writeInteger(this.FeuxVegetations, dos);

				// Integer

				writeInteger(this.AutresFeux, dos);

				// Integer

				writeInteger(this.Incendies, dos);

				// Integer

				writeInteger(this.AccidentsLieuxTravail, dos);

				// Integer

				writeInteger(this.AccidentsDomicile, dos);

				// Integer

				writeInteger(this.AccidentsSport, dos);

				// Integer

				writeInteger(this.AccidentsVoiePublique, dos);

				// Integer

				writeInteger(this.SecoursMontagne, dos);

				// Integer

				writeInteger(this.MalaisesLieuxTravail, dos);

				// Integer

				writeInteger(this.MalaisesDomicileVitale, dos);

				// Integer

				writeInteger(this.MalaisesDomicileCarence, dos);

				// Integer

				writeInteger(this.MalaisesSport, dos);

				// Integer

				writeInteger(this.MalaisesVoiePublique, dos);

				// Integer

				writeInteger(this.Autolyses, dos);

				// Integer

				writeInteger(this.SecoursEauxInterieuresPiscines, dos);

				// Integer

				writeInteger(this.SecoursMer, dos);

				// Integer

				writeInteger(this.Intoxications, dos);

				// Integer

				writeInteger(this.IntoxicationsCO, dos);

				// Integer

				writeInteger(this.AutresSAV, dos);

				// Integer

				writeInteger(this.SecoursVictime, dos);

				// Integer

				writeInteger(this.RelevagePersonnes, dos);

				// Integer

				writeInteger(this.RecherchePersonnes, dos);

				// Integer

				writeInteger(this.AidesPersonne, dos);

				// Integer

				writeInteger(this.SecoursPersonne, dos);

				// Integer

				writeInteger(this.AccientsRoutiers, dos);

				// Integer

				writeInteger(this.AccidentsFerroviaires, dos);

				// Integer

				writeInteger(this.AccidentsAeriens, dos);

				// Integer

				writeInteger(this.AccidentsNavigation, dos);

				// Integer

				writeInteger(this.AccidentsTeleportage, dos);

				// Integer

				writeInteger(this.AccidentsCirculation, dos);

				// Integer

				writeInteger(this.OdeursFuiteGaz, dos);

				// Integer

				writeInteger(this.OdeursAutres, dos);

				// Integer

				writeInteger(this.FaitDusElectricite, dos);

				// Integer

				writeInteger(this.PollutionsContaminations, dos);

				// Integer

				writeInteger(this.AutresRisquesTechnologiques, dos);

				// Integer

				writeInteger(this.RisquesTechnologiques, dos);

				// Integer

				writeInteger(this.FuitesEau, dos);

				// Integer

				writeInteger(this.Inondations, dos);

				// Integer

				writeInteger(this.OuverturesPortes, dos);

				// Integer

				writeInteger(this.RecherchesObjets, dos);

				// Integer

				writeInteger(this.BruitsSuspects, dos);

				// Integer

				writeInteger(this.ProtectionBiens, dos);

				// Integer

				writeInteger(this.FaussesAlertes, dos);

				// Integer

				writeInteger(this.FaussesAlertesDAAF, dos);

				// Integer

				writeInteger(this.FaitsAnimaux, dos);

				// Integer

				writeInteger(this.FaitsHymenopteres, dos);

				// Integer

				writeInteger(this.DegagementsVoiePublique, dos);

				// Integer

				writeInteger(this.NettoyagesVoiePublique, dos);

				// Integer

				writeInteger(this.Eboulements, dos);

				// Integer

				writeInteger(this.DeposesObjets, dos);

				// Integer

				writeInteger(this.PiquetsSecuriteSurveillances, dos);

				// Integer

				writeInteger(this.EnginsExplosifs, dos);

				// Integer

				writeInteger(this.AutresOperationsDiverses, dos);

				// Integer

				writeInteger(this.Divers, dos);

				// Integer

				writeInteger(this.OperationsDiverses, dos);

				// Integer

				writeInteger(this.TotalInterventions, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("Annee=" + String.valueOf(Annee));
			sb.append(",Zone=" + Zone);
			sb.append(",Region=" + Region);
			sb.append(",Numero=" + Numero);
			sb.append(",Departement=" + Departement);
			sb.append(",CategorieA=" + CategorieA);
			sb.append(",FeuxHabitationsBureaux=" + String.valueOf(FeuxHabitationsBureaux));
			sb.append(",FeuxCheminees=" + String.valueOf(FeuxCheminees));
			sb.append(",FeuxAvecLocalSommeil=" + String.valueOf(FeuxAvecLocalSommeil));
			sb.append(",FeuxSansLocalSommeil=" + String.valueOf(FeuxSansLocalSommeil));
			sb.append(",FeuxLocalIndustriels=" + String.valueOf(FeuxLocalIndustriels));
			sb.append(",FeuxLocauxArtisanaux=" + String.valueOf(FeuxLocauxArtisanaux));
			sb.append(",FeuxLocauxAgricoles=" + String.valueOf(FeuxLocauxAgricoles));
			sb.append(",FeuxVoiePublique=" + String.valueOf(FeuxVoiePublique));
			sb.append(",FeuxVehicules=" + String.valueOf(FeuxVehicules));
			sb.append(",FeuxVegetations=" + String.valueOf(FeuxVegetations));
			sb.append(",AutresFeux=" + String.valueOf(AutresFeux));
			sb.append(",Incendies=" + String.valueOf(Incendies));
			sb.append(",AccidentsLieuxTravail=" + String.valueOf(AccidentsLieuxTravail));
			sb.append(",AccidentsDomicile=" + String.valueOf(AccidentsDomicile));
			sb.append(",AccidentsSport=" + String.valueOf(AccidentsSport));
			sb.append(",AccidentsVoiePublique=" + String.valueOf(AccidentsVoiePublique));
			sb.append(",SecoursMontagne=" + String.valueOf(SecoursMontagne));
			sb.append(",MalaisesLieuxTravail=" + String.valueOf(MalaisesLieuxTravail));
			sb.append(",MalaisesDomicileVitale=" + String.valueOf(MalaisesDomicileVitale));
			sb.append(",MalaisesDomicileCarence=" + String.valueOf(MalaisesDomicileCarence));
			sb.append(",MalaisesSport=" + String.valueOf(MalaisesSport));
			sb.append(",MalaisesVoiePublique=" + String.valueOf(MalaisesVoiePublique));
			sb.append(",Autolyses=" + String.valueOf(Autolyses));
			sb.append(",SecoursEauxInterieuresPiscines=" + String.valueOf(SecoursEauxInterieuresPiscines));
			sb.append(",SecoursMer=" + String.valueOf(SecoursMer));
			sb.append(",Intoxications=" + String.valueOf(Intoxications));
			sb.append(",IntoxicationsCO=" + String.valueOf(IntoxicationsCO));
			sb.append(",AutresSAV=" + String.valueOf(AutresSAV));
			sb.append(",SecoursVictime=" + String.valueOf(SecoursVictime));
			sb.append(",RelevagePersonnes=" + String.valueOf(RelevagePersonnes));
			sb.append(",RecherchePersonnes=" + String.valueOf(RecherchePersonnes));
			sb.append(",AidesPersonne=" + String.valueOf(AidesPersonne));
			sb.append(",SecoursPersonne=" + String.valueOf(SecoursPersonne));
			sb.append(",AccientsRoutiers=" + String.valueOf(AccientsRoutiers));
			sb.append(",AccidentsFerroviaires=" + String.valueOf(AccidentsFerroviaires));
			sb.append(",AccidentsAeriens=" + String.valueOf(AccidentsAeriens));
			sb.append(",AccidentsNavigation=" + String.valueOf(AccidentsNavigation));
			sb.append(",AccidentsTeleportage=" + String.valueOf(AccidentsTeleportage));
			sb.append(",AccidentsCirculation=" + String.valueOf(AccidentsCirculation));
			sb.append(",OdeursFuiteGaz=" + String.valueOf(OdeursFuiteGaz));
			sb.append(",OdeursAutres=" + String.valueOf(OdeursAutres));
			sb.append(",FaitDusElectricite=" + String.valueOf(FaitDusElectricite));
			sb.append(",PollutionsContaminations=" + String.valueOf(PollutionsContaminations));
			sb.append(",AutresRisquesTechnologiques=" + String.valueOf(AutresRisquesTechnologiques));
			sb.append(",RisquesTechnologiques=" + String.valueOf(RisquesTechnologiques));
			sb.append(",FuitesEau=" + String.valueOf(FuitesEau));
			sb.append(",Inondations=" + String.valueOf(Inondations));
			sb.append(",OuverturesPortes=" + String.valueOf(OuverturesPortes));
			sb.append(",RecherchesObjets=" + String.valueOf(RecherchesObjets));
			sb.append(",BruitsSuspects=" + String.valueOf(BruitsSuspects));
			sb.append(",ProtectionBiens=" + String.valueOf(ProtectionBiens));
			sb.append(",FaussesAlertes=" + String.valueOf(FaussesAlertes));
			sb.append(",FaussesAlertesDAAF=" + String.valueOf(FaussesAlertesDAAF));
			sb.append(",FaitsAnimaux=" + String.valueOf(FaitsAnimaux));
			sb.append(",FaitsHymenopteres=" + String.valueOf(FaitsHymenopteres));
			sb.append(",DegagementsVoiePublique=" + String.valueOf(DegagementsVoiePublique));
			sb.append(",NettoyagesVoiePublique=" + String.valueOf(NettoyagesVoiePublique));
			sb.append(",Eboulements=" + String.valueOf(Eboulements));
			sb.append(",DeposesObjets=" + String.valueOf(DeposesObjets));
			sb.append(",PiquetsSecuriteSurveillances=" + String.valueOf(PiquetsSecuriteSurveillances));
			sb.append(",EnginsExplosifs=" + String.valueOf(EnginsExplosifs));
			sb.append(",AutresOperationsDiverses=" + String.valueOf(AutresOperationsDiverses));
			sb.append(",Divers=" + String.valueOf(Divers));
			sb.append(",OperationsDiverses=" + String.valueOf(OperationsDiverses));
			sb.append(",TotalInterventions=" + String.valueOf(TotalInterventions));
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(row1Struct other) {

			int returnValue = -1;

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(), object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public void tFileInputDelimited_1Process(final java.util.Map<String, Object> globalMap) throws TalendException {
		globalMap.put("tFileInputDelimited_1_SUBPROCESS_STATE", 0);

		final boolean execStat = this.execStat;

		String iterateId = "";

		String currentComponent = "";
		java.util.Map<String, Object> resourceMap = new java.util.HashMap<String, Object>();

		try {
			// TDI-39566 avoid throwing an useless Exception
			boolean resumeIt = true;
			if (globalResumeTicket == false && resumeEntryMethodName != null) {
				String currentMethodName = new java.lang.Exception().getStackTrace()[0].getMethodName();
				resumeIt = resumeEntryMethodName.equals(currentMethodName);
			}
			if (resumeIt || globalResumeTicket) { // start the resume
				globalResumeTicket = true;

				row1Struct row1 = new row1Struct();
				row1Struct row2 = row1;
				out1Struct out1 = new out1Struct();
				row4Struct row4 = new row4Struct();
				row5Struct row5 = new row5Struct();

				/**
				 * [tFileOutputDelimited_1 begin ] start
				 */

				ok_Hash.put("tFileOutputDelimited_1", false);
				start_Hash.put("tFileOutputDelimited_1", System.currentTimeMillis());

				currentComponent = "tFileOutputDelimited_1";

				if (execStat) {
					runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "row4");
				}

				int tos_count_tFileOutputDelimited_1 = 0;

				String fileName_tFileOutputDelimited_1 = "";
				fileName_tFileOutputDelimited_1 = (new java.io.File(
						"C:/Program Files (x86)/TOS_DI-8.0.1/studio/workspace/DATAMSPR/_output/Intervention.csv"))
								.getAbsolutePath().replace("\\", "/");
				String fullName_tFileOutputDelimited_1 = null;
				String extension_tFileOutputDelimited_1 = null;
				String directory_tFileOutputDelimited_1 = null;
				if ((fileName_tFileOutputDelimited_1.indexOf("/") != -1)) {
					if (fileName_tFileOutputDelimited_1.lastIndexOf(".") < fileName_tFileOutputDelimited_1
							.lastIndexOf("/")) {
						fullName_tFileOutputDelimited_1 = fileName_tFileOutputDelimited_1;
						extension_tFileOutputDelimited_1 = "";
					} else {
						fullName_tFileOutputDelimited_1 = fileName_tFileOutputDelimited_1.substring(0,
								fileName_tFileOutputDelimited_1.lastIndexOf("."));
						extension_tFileOutputDelimited_1 = fileName_tFileOutputDelimited_1
								.substring(fileName_tFileOutputDelimited_1.lastIndexOf("."));
					}
					directory_tFileOutputDelimited_1 = fileName_tFileOutputDelimited_1.substring(0,
							fileName_tFileOutputDelimited_1.lastIndexOf("/"));
				} else {
					if (fileName_tFileOutputDelimited_1.lastIndexOf(".") != -1) {
						fullName_tFileOutputDelimited_1 = fileName_tFileOutputDelimited_1.substring(0,
								fileName_tFileOutputDelimited_1.lastIndexOf("."));
						extension_tFileOutputDelimited_1 = fileName_tFileOutputDelimited_1
								.substring(fileName_tFileOutputDelimited_1.lastIndexOf("."));
					} else {
						fullName_tFileOutputDelimited_1 = fileName_tFileOutputDelimited_1;
						extension_tFileOutputDelimited_1 = "";
					}
					directory_tFileOutputDelimited_1 = "";
				}
				boolean isFileGenerated_tFileOutputDelimited_1 = true;
				java.io.File filetFileOutputDelimited_1 = new java.io.File(fileName_tFileOutputDelimited_1);
				globalMap.put("tFileOutputDelimited_1_FILE_NAME", fileName_tFileOutputDelimited_1);
				int nb_line_tFileOutputDelimited_1 = 0;
				int splitedFileNo_tFileOutputDelimited_1 = 0;
				int currentRow_tFileOutputDelimited_1 = 0;

				final String OUT_DELIM_tFileOutputDelimited_1 = /** Start field tFileOutputDelimited_1:FIELDSEPARATOR */
						";"/** End field tFileOutputDelimited_1:FIELDSEPARATOR */
				;

				final String OUT_DELIM_ROWSEP_tFileOutputDelimited_1 = /**
																		 * Start field
																		 * tFileOutputDelimited_1:ROWSEPARATOR
																		 */
						"\n"/** End field tFileOutputDelimited_1:ROWSEPARATOR */
				;

				// create directory only if not exists
				if (directory_tFileOutputDelimited_1 != null && directory_tFileOutputDelimited_1.trim().length() != 0) {
					java.io.File dir_tFileOutputDelimited_1 = new java.io.File(directory_tFileOutputDelimited_1);
					if (!dir_tFileOutputDelimited_1.exists()) {
						dir_tFileOutputDelimited_1.mkdirs();
					}
				}

				// routines.system.Row
				java.io.Writer outtFileOutputDelimited_1 = null;

				java.io.File fileToDelete_tFileOutputDelimited_1 = new java.io.File(fileName_tFileOutputDelimited_1);
				if (fileToDelete_tFileOutputDelimited_1.exists()) {
					fileToDelete_tFileOutputDelimited_1.delete();
				}
				outtFileOutputDelimited_1 = new java.io.BufferedWriter(new java.io.OutputStreamWriter(
						new java.io.FileOutputStream(fileName_tFileOutputDelimited_1, false), "UTF-8"));
				if (filetFileOutputDelimited_1.length() == 0) {
					outtFileOutputDelimited_1.write("Id");
					outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
					outtFileOutputDelimited_1.write("Annee");
					outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
					outtFileOutputDelimited_1.write("Zone");
					outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
					outtFileOutputDelimited_1.write("Region");
					outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
					outtFileOutputDelimited_1.write("Departement");
					outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
					outtFileOutputDelimited_1.write("CategorieA");
					outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
					outtFileOutputDelimited_1.write("FeuxHabitationsBureaux");
					outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
					outtFileOutputDelimited_1.write("FeuxLocalIndustriels");
					outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
					outtFileOutputDelimited_1.write("FeuxLocauxArtisanaux");
					outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
					outtFileOutputDelimited_1.write("FeuxLocauxAgricoles");
					outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
					outtFileOutputDelimited_1.write("FeuxVoiePublique");
					outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
					outtFileOutputDelimited_1.write("FeuxVehicules");
					outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
					outtFileOutputDelimited_1.write("FeuxVegetations");
					outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
					outtFileOutputDelimited_1.write("AutresFeux");
					outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
					outtFileOutputDelimited_1.write("Incendies");
					outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
					outtFileOutputDelimited_1.write("AccidentsLieuxTravail");
					outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
					outtFileOutputDelimited_1.write("AccidentsVoiePublique");
					outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
					outtFileOutputDelimited_1.write("MalaisesLieuxTravail");
					outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
					outtFileOutputDelimited_1.write("MalaisesVoiePublique");
					outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
					outtFileOutputDelimited_1.write("Intoxications");
					outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
					outtFileOutputDelimited_1.write("IntoxicationsCO");
					outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
					outtFileOutputDelimited_1.write("SecoursVictime");
					outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
					outtFileOutputDelimited_1.write("RecherchePersonnes");
					outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
					outtFileOutputDelimited_1.write("AidesPersonne");
					outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
					outtFileOutputDelimited_1.write("SecoursPersonne");
					outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
					outtFileOutputDelimited_1.write("AccientsRoutiers");
					outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
					outtFileOutputDelimited_1.write("AccidentsFerroviaires");
					outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
					outtFileOutputDelimited_1.write("AccidentsAeriens");
					outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
					outtFileOutputDelimited_1.write("AccidentsNavigation");
					outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
					outtFileOutputDelimited_1.write("AccidentsCirculation");
					outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
					outtFileOutputDelimited_1.write("OdeursFuiteGaz");
					outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
					outtFileOutputDelimited_1.write("OdeursAutres");
					outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
					outtFileOutputDelimited_1.write("FaitDusElectricite");
					outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
					outtFileOutputDelimited_1.write("PollutionsContaminations");
					outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
					outtFileOutputDelimited_1.write("AutresRisquesTechnologiques");
					outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
					outtFileOutputDelimited_1.write("RisquesTechnologiques");
					outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
					outtFileOutputDelimited_1.write("FaussesAlertes");
					outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
					outtFileOutputDelimited_1.write("PiquetsSecuriteSurveillances");
					outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
					outtFileOutputDelimited_1.write("EnginsExplosifs");
					outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
					outtFileOutputDelimited_1.write("TotalInterventions");
					outtFileOutputDelimited_1.write(OUT_DELIM_ROWSEP_tFileOutputDelimited_1);
					outtFileOutputDelimited_1.flush();
				}

				resourceMap.put("out_tFileOutputDelimited_1", outtFileOutputDelimited_1);
				resourceMap.put("nb_line_tFileOutputDelimited_1", nb_line_tFileOutputDelimited_1);

				/**
				 * [tFileOutputDelimited_1 begin ] stop
				 */

				/**
				 * [tDBOutput_1 begin ] start
				 */

				ok_Hash.put("tDBOutput_1", false);
				start_Hash.put("tDBOutput_1", System.currentTimeMillis());

				currentComponent = "tDBOutput_1";

				if (execStat) {
					runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "row5");
				}

				int tos_count_tDBOutput_1 = 0;

				int updateKeyCount_tDBOutput_1 = 1;
				if (updateKeyCount_tDBOutput_1 < 1) {
					throw new RuntimeException("For update, Schema must have a key");
				} else if (updateKeyCount_tDBOutput_1 == 40 && true) {
					System.err.println("For update, every Schema column can not be a key");
				}

				int nb_line_tDBOutput_1 = 0;
				int nb_line_update_tDBOutput_1 = 0;
				int nb_line_inserted_tDBOutput_1 = 0;
				int nb_line_deleted_tDBOutput_1 = 0;
				int nb_line_rejected_tDBOutput_1 = 0;

				int deletedCount_tDBOutput_1 = 0;
				int updatedCount_tDBOutput_1 = 0;
				int insertedCount_tDBOutput_1 = 0;
				int rowsToCommitCount_tDBOutput_1 = 0;
				int rejectedCount_tDBOutput_1 = 0;

				String tableName_tDBOutput_1 = "Intervention";
				boolean whetherReject_tDBOutput_1 = false;

				java.util.Calendar calendar_tDBOutput_1 = java.util.Calendar.getInstance();
				calendar_tDBOutput_1.set(1, 0, 1, 0, 0, 0);
				long year1_tDBOutput_1 = calendar_tDBOutput_1.getTime().getTime();
				calendar_tDBOutput_1.set(10000, 0, 1, 0, 0, 0);
				long year10000_tDBOutput_1 = calendar_tDBOutput_1.getTime().getTime();
				long date_tDBOutput_1;

				java.sql.Connection conn_tDBOutput_1 = null;

				String properties_tDBOutput_1 = "noDatetimeStringSync=true&enabledTLSProtocols=TLSv1.2,TLSv1.1,TLSv1";
				if (properties_tDBOutput_1 == null || properties_tDBOutput_1.trim().length() == 0) {
					properties_tDBOutput_1 = "rewriteBatchedStatements=true&allowLoadLocalInfile=true";
				} else {
					if (!properties_tDBOutput_1.contains("rewriteBatchedStatements=")) {
						properties_tDBOutput_1 += "&rewriteBatchedStatements=true";
					}

					if (!properties_tDBOutput_1.contains("allowLoadLocalInfile=")) {
						properties_tDBOutput_1 += "&allowLoadLocalInfile=true";
					}
				}

				String url_tDBOutput_1 = "jdbc:mysql://" + "localhost" + ":" + "3306" + "/" + "datamspr" + "?"
						+ properties_tDBOutput_1;

				String driverClass_tDBOutput_1 = "com.mysql.cj.jdbc.Driver";

				String dbUser_tDBOutput_1 = "root";

				final String decryptedPassword_tDBOutput_1 = routines.system.PasswordEncryptUtil
						.decryptPassword("enc:routine.encryption.key.v1:VznZgcKW+kIQ7o7kK/SEeQjNeiCI0OHur8ODuqVw2So=");

				String dbPwd_tDBOutput_1 = decryptedPassword_tDBOutput_1;
				java.lang.Class.forName(driverClass_tDBOutput_1);

				conn_tDBOutput_1 = java.sql.DriverManager.getConnection(url_tDBOutput_1, dbUser_tDBOutput_1,
						dbPwd_tDBOutput_1);

				resourceMap.put("conn_tDBOutput_1", conn_tDBOutput_1);
				conn_tDBOutput_1.setAutoCommit(false);
				int commitEvery_tDBOutput_1 = 10000;
				int commitCounter_tDBOutput_1 = 0;

				int count_tDBOutput_1 = 0;

				java.sql.DatabaseMetaData dbMetaData_tDBOutput_1 = conn_tDBOutput_1.getMetaData();
				java.sql.ResultSet rsTable_tDBOutput_1 = dbMetaData_tDBOutput_1.getTables("datamspr", null, null,
						new String[] { "TABLE" });
				boolean whetherExist_tDBOutput_1 = false;
				while (rsTable_tDBOutput_1.next()) {
					String table_tDBOutput_1 = rsTable_tDBOutput_1.getString("TABLE_NAME");
					if (table_tDBOutput_1.equalsIgnoreCase("Intervention")) {
						whetherExist_tDBOutput_1 = true;
						break;
					}
				}
				if (whetherExist_tDBOutput_1) {
					try (java.sql.Statement stmtDrop_tDBOutput_1 = conn_tDBOutput_1.createStatement()) {
						stmtDrop_tDBOutput_1.execute("DROP TABLE `" + tableName_tDBOutput_1 + "`");
					}
				}
				try (java.sql.Statement stmtCreate_tDBOutput_1 = conn_tDBOutput_1.createStatement()) {
					stmtCreate_tDBOutput_1.execute("CREATE TABLE `" + tableName_tDBOutput_1
							+ "`(`Id` INT(0)   not null ,`Annee` INT(255)  ,`Zone` VARCHAR(255)  ,`Region` VARCHAR(255)  ,`Departement` VARCHAR(255)  ,`CategorieA` VARCHAR(255)  ,`FeuxHabitationsBureaux` INT(255)  ,`FeuxLocalIndustriels` INT(255)  ,`FeuxLocauxArtisanaux` INT(0)  ,`FeuxLocauxAgricoles` INT(0)  ,`FeuxVoiePublique` INT(0)  ,`FeuxVehicules` INT(0)  ,`FeuxVegetations` INT(0)  ,`AutresFeux` INT(0)  ,`Incendies` INT(0)  ,`AccidentsLieuxTravail` INT(0)  ,`AccidentsVoiePublique` INT(0)  ,`MalaisesLieuxTravail` INT(0)  ,`MalaisesVoiePublique` INT(0)  ,`Intoxications` INT(0)  ,`IntoxicationsCO` INT(0)  ,`SecoursVictime` INT(0)  ,`RecherchePersonnes` INT(0)  ,`AidesPersonne` INT(0)  ,`SecoursPersonne` INT(0)  ,`AccientsRoutiers` INT(0)  ,`AccidentsFerroviaires` INT(0)  ,`AccidentsAeriens` INT(0)  ,`AccidentsNavigation` INT(0)  ,`AccidentsCirculation` INT(0)  ,`OdeursFuiteGaz` INT(0)  ,`OdeursAutres` INT(0)  ,`FaitDusElectricite` INT(0)  ,`PollutionsContaminations` INT(0)  ,`AutresRisquesTechnologiques` INT(0)  ,`RisquesTechnologiques` INT(0)  ,`FaussesAlertes` INT(0)  ,`PiquetsSecuriteSurveillances` INT(0)  ,`EnginsExplosifs` INT(0)  ,`TotalInterventions` INT(0)  ,primary key(`Id`))");
				}
				java.sql.PreparedStatement pstmt_tDBOutput_1 = conn_tDBOutput_1
						.prepareStatement("SELECT COUNT(1) FROM `" + "Intervention" + "` WHERE `Id` = ?");
				resourceMap.put("pstmt_tDBOutput_1", pstmt_tDBOutput_1);
				String insert_tDBOutput_1 = "INSERT INTO `" + "Intervention"
						+ "` (`Id`,`Annee`,`Zone`,`Region`,`Departement`,`CategorieA`,`FeuxHabitationsBureaux`,`FeuxLocalIndustriels`,`FeuxLocauxArtisanaux`,`FeuxLocauxAgricoles`,`FeuxVoiePublique`,`FeuxVehicules`,`FeuxVegetations`,`AutresFeux`,`Incendies`,`AccidentsLieuxTravail`,`AccidentsVoiePublique`,`MalaisesLieuxTravail`,`MalaisesVoiePublique`,`Intoxications`,`IntoxicationsCO`,`SecoursVictime`,`RecherchePersonnes`,`AidesPersonne`,`SecoursPersonne`,`AccientsRoutiers`,`AccidentsFerroviaires`,`AccidentsAeriens`,`AccidentsNavigation`,`AccidentsCirculation`,`OdeursFuiteGaz`,`OdeursAutres`,`FaitDusElectricite`,`PollutionsContaminations`,`AutresRisquesTechnologiques`,`RisquesTechnologiques`,`FaussesAlertes`,`PiquetsSecuriteSurveillances`,`EnginsExplosifs`,`TotalInterventions`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

				java.sql.PreparedStatement pstmtInsert_tDBOutput_1 = conn_tDBOutput_1
						.prepareStatement(insert_tDBOutput_1);
				resourceMap.put("pstmtInsert_tDBOutput_1", pstmtInsert_tDBOutput_1);
				String update_tDBOutput_1 = "UPDATE `" + "Intervention"
						+ "` SET `Annee` = ?,`Zone` = ?,`Region` = ?,`Departement` = ?,`CategorieA` = ?,`FeuxHabitationsBureaux` = ?,`FeuxLocalIndustriels` = ?,`FeuxLocauxArtisanaux` = ?,`FeuxLocauxAgricoles` = ?,`FeuxVoiePublique` = ?,`FeuxVehicules` = ?,`FeuxVegetations` = ?,`AutresFeux` = ?,`Incendies` = ?,`AccidentsLieuxTravail` = ?,`AccidentsVoiePublique` = ?,`MalaisesLieuxTravail` = ?,`MalaisesVoiePublique` = ?,`Intoxications` = ?,`IntoxicationsCO` = ?,`SecoursVictime` = ?,`RecherchePersonnes` = ?,`AidesPersonne` = ?,`SecoursPersonne` = ?,`AccientsRoutiers` = ?,`AccidentsFerroviaires` = ?,`AccidentsAeriens` = ?,`AccidentsNavigation` = ?,`AccidentsCirculation` = ?,`OdeursFuiteGaz` = ?,`OdeursAutres` = ?,`FaitDusElectricite` = ?,`PollutionsContaminations` = ?,`AutresRisquesTechnologiques` = ?,`RisquesTechnologiques` = ?,`FaussesAlertes` = ?,`PiquetsSecuriteSurveillances` = ?,`EnginsExplosifs` = ?,`TotalInterventions` = ? WHERE `Id` = ?";

				java.sql.PreparedStatement pstmtUpdate_tDBOutput_1 = conn_tDBOutput_1
						.prepareStatement(update_tDBOutput_1);
				resourceMap.put("pstmtUpdate_tDBOutput_1", pstmtUpdate_tDBOutput_1);

				/**
				 * [tDBOutput_1 begin ] stop
				 */

				/**
				 * [tReplicate_1 begin ] start
				 */

				ok_Hash.put("tReplicate_1", false);
				start_Hash.put("tReplicate_1", System.currentTimeMillis());

				currentComponent = "tReplicate_1";

				if (execStat) {
					runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "out1");
				}

				int tos_count_tReplicate_1 = 0;

				/**
				 * [tReplicate_1 begin ] stop
				 */

				/**
				 * [tMap_1 begin ] start
				 */

				ok_Hash.put("tMap_1", false);
				start_Hash.put("tMap_1", System.currentTimeMillis());

				currentComponent = "tMap_1";

				if (execStat) {
					runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "row2");
				}

				int tos_count_tMap_1 = 0;

// ###############################
// # Lookup's keys initialization
// ###############################        

// ###############################
// # Vars initialization
				class Var__tMap_1__Struct {
					int Id;
				}
				Var__tMap_1__Struct Var__tMap_1 = new Var__tMap_1__Struct();
// ###############################

// ###############################
// # Outputs initialization
				out1Struct out1_tmp = new out1Struct();
// ###############################

				/**
				 * [tMap_1 begin ] stop
				 */

				/**
				 * [tLogRow_1 begin ] start
				 */

				ok_Hash.put("tLogRow_1", false);
				start_Hash.put("tLogRow_1", System.currentTimeMillis());

				currentComponent = "tLogRow_1";

				if (execStat) {
					runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "row1");
				}

				int tos_count_tLogRow_1 = 0;

				///////////////////////

				class Util_tLogRow_1 {

					String[] des_top = { ".", ".", "-", "+" };

					String[] des_head = { "|=", "=|", "-", "+" };

					String[] des_bottom = { "'", "'", "-", "+" };

					String name = "";

					java.util.List<String[]> list = new java.util.ArrayList<String[]>();

					int[] colLengths = new int[71];

					public void addRow(String[] row) {

						for (int i = 0; i < 71; i++) {
							if (row[i] != null) {
								colLengths[i] = Math.max(colLengths[i], row[i].length());
							}
						}
						list.add(row);
					}

					public void setTableName(String name) {

						this.name = name;
					}

					public StringBuilder format() {

						StringBuilder sb = new StringBuilder();

						sb.append(print(des_top));

						int totals = 0;
						for (int i = 0; i < colLengths.length; i++) {
							totals = totals + colLengths[i];
						}

						// name
						sb.append("|");
						int k = 0;
						for (k = 0; k < (totals + 70 - name.length()) / 2; k++) {
							sb.append(' ');
						}
						sb.append(name);
						for (int i = 0; i < totals + 70 - name.length() - k; i++) {
							sb.append(' ');
						}
						sb.append("|\n");

						// head and rows
						sb.append(print(des_head));
						for (int i = 0; i < list.size(); i++) {

							String[] row = list.get(i);

							java.util.Formatter formatter = new java.util.Formatter(new StringBuilder());

							StringBuilder sbformat = new StringBuilder();
							sbformat.append("|%1$-");
							sbformat.append(colLengths[0]);
							sbformat.append("s");

							sbformat.append("|%2$-");
							sbformat.append(colLengths[1]);
							sbformat.append("s");

							sbformat.append("|%3$-");
							sbformat.append(colLengths[2]);
							sbformat.append("s");

							sbformat.append("|%4$-");
							sbformat.append(colLengths[3]);
							sbformat.append("s");

							sbformat.append("|%5$-");
							sbformat.append(colLengths[4]);
							sbformat.append("s");

							sbformat.append("|%6$-");
							sbformat.append(colLengths[5]);
							sbformat.append("s");

							sbformat.append("|%7$-");
							sbformat.append(colLengths[6]);
							sbformat.append("s");

							sbformat.append("|%8$-");
							sbformat.append(colLengths[7]);
							sbformat.append("s");

							sbformat.append("|%9$-");
							sbformat.append(colLengths[8]);
							sbformat.append("s");

							sbformat.append("|%10$-");
							sbformat.append(colLengths[9]);
							sbformat.append("s");

							sbformat.append("|%11$-");
							sbformat.append(colLengths[10]);
							sbformat.append("s");

							sbformat.append("|%12$-");
							sbformat.append(colLengths[11]);
							sbformat.append("s");

							sbformat.append("|%13$-");
							sbformat.append(colLengths[12]);
							sbformat.append("s");

							sbformat.append("|%14$-");
							sbformat.append(colLengths[13]);
							sbformat.append("s");

							sbformat.append("|%15$-");
							sbformat.append(colLengths[14]);
							sbformat.append("s");

							sbformat.append("|%16$-");
							sbformat.append(colLengths[15]);
							sbformat.append("s");

							sbformat.append("|%17$-");
							sbformat.append(colLengths[16]);
							sbformat.append("s");

							sbformat.append("|%18$-");
							sbformat.append(colLengths[17]);
							sbformat.append("s");

							sbformat.append("|%19$-");
							sbformat.append(colLengths[18]);
							sbformat.append("s");

							sbformat.append("|%20$-");
							sbformat.append(colLengths[19]);
							sbformat.append("s");

							sbformat.append("|%21$-");
							sbformat.append(colLengths[20]);
							sbformat.append("s");

							sbformat.append("|%22$-");
							sbformat.append(colLengths[21]);
							sbformat.append("s");

							sbformat.append("|%23$-");
							sbformat.append(colLengths[22]);
							sbformat.append("s");

							sbformat.append("|%24$-");
							sbformat.append(colLengths[23]);
							sbformat.append("s");

							sbformat.append("|%25$-");
							sbformat.append(colLengths[24]);
							sbformat.append("s");

							sbformat.append("|%26$-");
							sbformat.append(colLengths[25]);
							sbformat.append("s");

							sbformat.append("|%27$-");
							sbformat.append(colLengths[26]);
							sbformat.append("s");

							sbformat.append("|%28$-");
							sbformat.append(colLengths[27]);
							sbformat.append("s");

							sbformat.append("|%29$-");
							sbformat.append(colLengths[28]);
							sbformat.append("s");

							sbformat.append("|%30$-");
							sbformat.append(colLengths[29]);
							sbformat.append("s");

							sbformat.append("|%31$-");
							sbformat.append(colLengths[30]);
							sbformat.append("s");

							sbformat.append("|%32$-");
							sbformat.append(colLengths[31]);
							sbformat.append("s");

							sbformat.append("|%33$-");
							sbformat.append(colLengths[32]);
							sbformat.append("s");

							sbformat.append("|%34$-");
							sbformat.append(colLengths[33]);
							sbformat.append("s");

							sbformat.append("|%35$-");
							sbformat.append(colLengths[34]);
							sbformat.append("s");

							sbformat.append("|%36$-");
							sbformat.append(colLengths[35]);
							sbformat.append("s");

							sbformat.append("|%37$-");
							sbformat.append(colLengths[36]);
							sbformat.append("s");

							sbformat.append("|%38$-");
							sbformat.append(colLengths[37]);
							sbformat.append("s");

							sbformat.append("|%39$-");
							sbformat.append(colLengths[38]);
							sbformat.append("s");

							sbformat.append("|%40$-");
							sbformat.append(colLengths[39]);
							sbformat.append("s");

							sbformat.append("|%41$-");
							sbformat.append(colLengths[40]);
							sbformat.append("s");

							sbformat.append("|%42$-");
							sbformat.append(colLengths[41]);
							sbformat.append("s");

							sbformat.append("|%43$-");
							sbformat.append(colLengths[42]);
							sbformat.append("s");

							sbformat.append("|%44$-");
							sbformat.append(colLengths[43]);
							sbformat.append("s");

							sbformat.append("|%45$-");
							sbformat.append(colLengths[44]);
							sbformat.append("s");

							sbformat.append("|%46$-");
							sbformat.append(colLengths[45]);
							sbformat.append("s");

							sbformat.append("|%47$-");
							sbformat.append(colLengths[46]);
							sbformat.append("s");

							sbformat.append("|%48$-");
							sbformat.append(colLengths[47]);
							sbformat.append("s");

							sbformat.append("|%49$-");
							sbformat.append(colLengths[48]);
							sbformat.append("s");

							sbformat.append("|%50$-");
							sbformat.append(colLengths[49]);
							sbformat.append("s");

							sbformat.append("|%51$-");
							sbformat.append(colLengths[50]);
							sbformat.append("s");

							sbformat.append("|%52$-");
							sbformat.append(colLengths[51]);
							sbformat.append("s");

							sbformat.append("|%53$-");
							sbformat.append(colLengths[52]);
							sbformat.append("s");

							sbformat.append("|%54$-");
							sbformat.append(colLengths[53]);
							sbformat.append("s");

							sbformat.append("|%55$-");
							sbformat.append(colLengths[54]);
							sbformat.append("s");

							sbformat.append("|%56$-");
							sbformat.append(colLengths[55]);
							sbformat.append("s");

							sbformat.append("|%57$-");
							sbformat.append(colLengths[56]);
							sbformat.append("s");

							sbformat.append("|%58$-");
							sbformat.append(colLengths[57]);
							sbformat.append("s");

							sbformat.append("|%59$-");
							sbformat.append(colLengths[58]);
							sbformat.append("s");

							sbformat.append("|%60$-");
							sbformat.append(colLengths[59]);
							sbformat.append("s");

							sbformat.append("|%61$-");
							sbformat.append(colLengths[60]);
							sbformat.append("s");

							sbformat.append("|%62$-");
							sbformat.append(colLengths[61]);
							sbformat.append("s");

							sbformat.append("|%63$-");
							sbformat.append(colLengths[62]);
							sbformat.append("s");

							sbformat.append("|%64$-");
							sbformat.append(colLengths[63]);
							sbformat.append("s");

							sbformat.append("|%65$-");
							sbformat.append(colLengths[64]);
							sbformat.append("s");

							sbformat.append("|%66$-");
							sbformat.append(colLengths[65]);
							sbformat.append("s");

							sbformat.append("|%67$-");
							sbformat.append(colLengths[66]);
							sbformat.append("s");

							sbformat.append("|%68$-");
							sbformat.append(colLengths[67]);
							sbformat.append("s");

							sbformat.append("|%69$-");
							sbformat.append(colLengths[68]);
							sbformat.append("s");

							sbformat.append("|%70$-");
							sbformat.append(colLengths[69]);
							sbformat.append("s");

							sbformat.append("|%71$-");
							sbformat.append(colLengths[70]);
							sbformat.append("s");

							sbformat.append("|\n");

							formatter.format(sbformat.toString(), (Object[]) row);

							sb.append(formatter.toString());
							if (i == 0)
								sb.append(print(des_head)); // print the head
						}

						// end
						sb.append(print(des_bottom));
						return sb;
					}

					private StringBuilder print(String[] fillChars) {
						StringBuilder sb = new StringBuilder();
						// first column
						sb.append(fillChars[0]);
						for (int i = 0; i < colLengths[0] - fillChars[0].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);

						for (int i = 0; i < colLengths[1] - fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[2] - fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[3] - fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[4] - fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[5] - fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[6] - fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[7] - fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[8] - fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[9] - fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[10] - fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[11] - fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[12] - fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[13] - fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[14] - fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[15] - fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[16] - fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[17] - fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[18] - fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[19] - fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[20] - fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[21] - fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[22] - fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[23] - fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[24] - fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[25] - fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[26] - fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[27] - fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[28] - fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[29] - fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[30] - fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[31] - fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[32] - fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[33] - fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[34] - fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[35] - fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[36] - fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[37] - fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[38] - fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[39] - fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[40] - fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[41] - fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[42] - fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[43] - fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[44] - fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[45] - fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[46] - fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[47] - fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[48] - fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[49] - fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[50] - fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[51] - fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[52] - fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[53] - fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[54] - fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[55] - fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[56] - fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[57] - fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[58] - fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[59] - fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[60] - fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[61] - fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[62] - fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[63] - fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[64] - fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[65] - fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[66] - fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[67] - fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[68] - fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[69] - fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);

						// last column
						for (int i = 0; i < colLengths[70] - fillChars[1].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[1]);
						sb.append("\n");
						return sb;
					}

					public boolean isTableEmpty() {
						if (list.size() > 1)
							return false;
						return true;
					}
				}
				Util_tLogRow_1 util_tLogRow_1 = new Util_tLogRow_1();
				util_tLogRow_1.setTableName("tLogRow_1");
				util_tLogRow_1.addRow(new String[] { "Annee", "Zone", "Region", "Numero", "Departement", "CategorieA",
						"FeuxHabitationsBureaux", "FeuxCheminees", "FeuxAvecLocalSommeil", "FeuxSansLocalSommeil",
						"FeuxLocalIndustriels", "FeuxLocauxArtisanaux", "FeuxLocauxAgricoles", "FeuxVoiePublique",
						"FeuxVehicules", "FeuxVegetations", "AutresFeux", "Incendies", "AccidentsLieuxTravail",
						"AccidentsDomicile", "AccidentsSport", "AccidentsVoiePublique", "SecoursMontagne",
						"MalaisesLieuxTravail", "MalaisesDomicileVitale", "MalaisesDomicileCarence", "MalaisesSport",
						"MalaisesVoiePublique", "Autolyses", "SecoursEauxInterieuresPiscines", "SecoursMer",
						"Intoxications", "IntoxicationsCO", "AutresSAV", "SecoursVictime", "RelevagePersonnes",
						"RecherchePersonnes", "AidesPersonne", "SecoursPersonne", "AccientsRoutiers",
						"AccidentsFerroviaires", "AccidentsAeriens", "AccidentsNavigation", "AccidentsTeleportage",
						"AccidentsCirculation", "OdeursFuiteGaz", "OdeursAutres", "FaitDusElectricite",
						"PollutionsContaminations", "AutresRisquesTechnologiques", "RisquesTechnologiques", "FuitesEau",
						"Inondations", "OuverturesPortes", "RecherchesObjets", "BruitsSuspects", "ProtectionBiens",
						"FaussesAlertes", "FaussesAlertesDAAF", "FaitsAnimaux", "FaitsHymenopteres",
						"DegagementsVoiePublique", "NettoyagesVoiePublique", "Eboulements", "DeposesObjets",
						"PiquetsSecuriteSurveillances", "EnginsExplosifs", "AutresOperationsDiverses", "Divers",
						"OperationsDiverses", "TotalInterventions", });
				StringBuilder strBuffer_tLogRow_1 = null;
				int nb_line_tLogRow_1 = 0;
///////////////////////    			

				/**
				 * [tLogRow_1 begin ] stop
				 */

				/**
				 * [tFileInputDelimited_1 begin ] start
				 */

				ok_Hash.put("tFileInputDelimited_1", false);
				start_Hash.put("tFileInputDelimited_1", System.currentTimeMillis());

				currentComponent = "tFileInputDelimited_1";

				int tos_count_tFileInputDelimited_1 = 0;

				final routines.system.RowState rowstate_tFileInputDelimited_1 = new routines.system.RowState();

				int nb_line_tFileInputDelimited_1 = 0;
				org.talend.fileprocess.FileInputDelimited fid_tFileInputDelimited_1 = null;
				int limit_tFileInputDelimited_1 = -1;
				try {

					Object filename_tFileInputDelimited_1 = "C:/Program Files (x86)/TOS_DI-8.0.1/studio/workspace/DATAMSPR/_input/interventions2021.csv";
					if (filename_tFileInputDelimited_1 instanceof java.io.InputStream) {

						int footer_value_tFileInputDelimited_1 = 0, random_value_tFileInputDelimited_1 = -1;
						if (footer_value_tFileInputDelimited_1 > 0 || random_value_tFileInputDelimited_1 > 0) {
							throw new java.lang.Exception(
									"When the input source is a stream,footer and random shouldn't be bigger than 0.");
						}

					}
					try {
						fid_tFileInputDelimited_1 = new org.talend.fileprocess.FileInputDelimited(
								"C:/Program Files (x86)/TOS_DI-8.0.1/studio/workspace/DATAMSPR/_input/interventions2021.csv",
								"ISO-8859-15", ";", "\n", true, 1, 0, limit_tFileInputDelimited_1, -1, false);
					} catch (java.lang.Exception e) {
						globalMap.put("tFileInputDelimited_1_ERROR_MESSAGE", e.getMessage());

						System.err.println(e.getMessage());

					}

					while (fid_tFileInputDelimited_1 != null && fid_tFileInputDelimited_1.nextRecord()) {
						rowstate_tFileInputDelimited_1.reset();

						row1 = null;

						boolean whetherReject_tFileInputDelimited_1 = false;
						row1 = new row1Struct();
						try {

							int columnIndexWithD_tFileInputDelimited_1 = 0;

							String temp = "";

							columnIndexWithD_tFileInputDelimited_1 = 0;

							temp = fid_tFileInputDelimited_1.get(columnIndexWithD_tFileInputDelimited_1);
							if (temp.length() > 0) {

								try {

									row1.Annee = ParserUtils
											.parseTo_Integer(ParserUtils.parseTo_Number(temp, ' ', '.'));

								} catch (java.lang.Exception ex_tFileInputDelimited_1) {
									globalMap.put("tFileInputDelimited_1_ERROR_MESSAGE",
											ex_tFileInputDelimited_1.getMessage());
									rowstate_tFileInputDelimited_1.setException(new RuntimeException(String.format(
											"Couldn't parse value for column '%s' in '%s', value is '%s'. Details: %s",
											"Annee", "row1", temp, ex_tFileInputDelimited_1),
											ex_tFileInputDelimited_1));
								}

							} else {

								row1.Annee = null;

							}

							columnIndexWithD_tFileInputDelimited_1 = 1;

							row1.Zone = fid_tFileInputDelimited_1.get(columnIndexWithD_tFileInputDelimited_1);

							columnIndexWithD_tFileInputDelimited_1 = 2;

							row1.Region = fid_tFileInputDelimited_1.get(columnIndexWithD_tFileInputDelimited_1);

							columnIndexWithD_tFileInputDelimited_1 = 3;

							row1.Numero = fid_tFileInputDelimited_1.get(columnIndexWithD_tFileInputDelimited_1);

							columnIndexWithD_tFileInputDelimited_1 = 4;

							row1.Departement = fid_tFileInputDelimited_1.get(columnIndexWithD_tFileInputDelimited_1);

							columnIndexWithD_tFileInputDelimited_1 = 5;

							row1.CategorieA = fid_tFileInputDelimited_1.get(columnIndexWithD_tFileInputDelimited_1);

							columnIndexWithD_tFileInputDelimited_1 = 6;

							temp = fid_tFileInputDelimited_1.get(columnIndexWithD_tFileInputDelimited_1);
							if (temp.length() > 0) {

								try {

									row1.FeuxHabitationsBureaux = ParserUtils
											.parseTo_Integer(ParserUtils.parseTo_Number(temp, ' ', '.'));

								} catch (java.lang.Exception ex_tFileInputDelimited_1) {
									globalMap.put("tFileInputDelimited_1_ERROR_MESSAGE",
											ex_tFileInputDelimited_1.getMessage());
									rowstate_tFileInputDelimited_1.setException(new RuntimeException(String.format(
											"Couldn't parse value for column '%s' in '%s', value is '%s'. Details: %s",
											"FeuxHabitationsBureaux", "row1", temp, ex_tFileInputDelimited_1),
											ex_tFileInputDelimited_1));
								}

							} else {

								row1.FeuxHabitationsBureaux = null;

							}

							columnIndexWithD_tFileInputDelimited_1 = 7;

							temp = fid_tFileInputDelimited_1.get(columnIndexWithD_tFileInputDelimited_1);
							if (temp.length() > 0) {

								try {

									row1.FeuxCheminees = ParserUtils
											.parseTo_Integer(ParserUtils.parseTo_Number(temp, ' ', '.'));

								} catch (java.lang.Exception ex_tFileInputDelimited_1) {
									globalMap.put("tFileInputDelimited_1_ERROR_MESSAGE",
											ex_tFileInputDelimited_1.getMessage());
									rowstate_tFileInputDelimited_1.setException(new RuntimeException(String.format(
											"Couldn't parse value for column '%s' in '%s', value is '%s'. Details: %s",
											"FeuxCheminees", "row1", temp, ex_tFileInputDelimited_1),
											ex_tFileInputDelimited_1));
								}

							} else {

								row1.FeuxCheminees = null;

							}

							columnIndexWithD_tFileInputDelimited_1 = 8;

							temp = fid_tFileInputDelimited_1.get(columnIndexWithD_tFileInputDelimited_1);
							if (temp.length() > 0) {

								try {

									row1.FeuxAvecLocalSommeil = ParserUtils
											.parseTo_Integer(ParserUtils.parseTo_Number(temp, ' ', '.'));

								} catch (java.lang.Exception ex_tFileInputDelimited_1) {
									globalMap.put("tFileInputDelimited_1_ERROR_MESSAGE",
											ex_tFileInputDelimited_1.getMessage());
									rowstate_tFileInputDelimited_1.setException(new RuntimeException(String.format(
											"Couldn't parse value for column '%s' in '%s', value is '%s'. Details: %s",
											"FeuxAvecLocalSommeil", "row1", temp, ex_tFileInputDelimited_1),
											ex_tFileInputDelimited_1));
								}

							} else {

								row1.FeuxAvecLocalSommeil = null;

							}

							columnIndexWithD_tFileInputDelimited_1 = 9;

							temp = fid_tFileInputDelimited_1.get(columnIndexWithD_tFileInputDelimited_1);
							if (temp.length() > 0) {

								try {

									row1.FeuxSansLocalSommeil = ParserUtils
											.parseTo_Integer(ParserUtils.parseTo_Number(temp, ' ', '.'));

								} catch (java.lang.Exception ex_tFileInputDelimited_1) {
									globalMap.put("tFileInputDelimited_1_ERROR_MESSAGE",
											ex_tFileInputDelimited_1.getMessage());
									rowstate_tFileInputDelimited_1.setException(new RuntimeException(String.format(
											"Couldn't parse value for column '%s' in '%s', value is '%s'. Details: %s",
											"FeuxSansLocalSommeil", "row1", temp, ex_tFileInputDelimited_1),
											ex_tFileInputDelimited_1));
								}

							} else {

								row1.FeuxSansLocalSommeil = null;

							}

							columnIndexWithD_tFileInputDelimited_1 = 10;

							temp = fid_tFileInputDelimited_1.get(columnIndexWithD_tFileInputDelimited_1);
							if (temp.length() > 0) {

								try {

									row1.FeuxLocalIndustriels = ParserUtils
											.parseTo_Integer(ParserUtils.parseTo_Number(temp, ' ', '.'));

								} catch (java.lang.Exception ex_tFileInputDelimited_1) {
									globalMap.put("tFileInputDelimited_1_ERROR_MESSAGE",
											ex_tFileInputDelimited_1.getMessage());
									rowstate_tFileInputDelimited_1.setException(new RuntimeException(String.format(
											"Couldn't parse value for column '%s' in '%s', value is '%s'. Details: %s",
											"FeuxLocalIndustriels", "row1", temp, ex_tFileInputDelimited_1),
											ex_tFileInputDelimited_1));
								}

							} else {

								row1.FeuxLocalIndustriels = null;

							}

							columnIndexWithD_tFileInputDelimited_1 = 11;

							temp = fid_tFileInputDelimited_1.get(columnIndexWithD_tFileInputDelimited_1);
							if (temp.length() > 0) {

								try {

									row1.FeuxLocauxArtisanaux = ParserUtils
											.parseTo_Integer(ParserUtils.parseTo_Number(temp, ' ', '.'));

								} catch (java.lang.Exception ex_tFileInputDelimited_1) {
									globalMap.put("tFileInputDelimited_1_ERROR_MESSAGE",
											ex_tFileInputDelimited_1.getMessage());
									rowstate_tFileInputDelimited_1.setException(new RuntimeException(String.format(
											"Couldn't parse value for column '%s' in '%s', value is '%s'. Details: %s",
											"FeuxLocauxArtisanaux", "row1", temp, ex_tFileInputDelimited_1),
											ex_tFileInputDelimited_1));
								}

							} else {

								row1.FeuxLocauxArtisanaux = null;

							}

							columnIndexWithD_tFileInputDelimited_1 = 12;

							temp = fid_tFileInputDelimited_1.get(columnIndexWithD_tFileInputDelimited_1);
							if (temp.length() > 0) {

								try {

									row1.FeuxLocauxAgricoles = ParserUtils
											.parseTo_Integer(ParserUtils.parseTo_Number(temp, ' ', '.'));

								} catch (java.lang.Exception ex_tFileInputDelimited_1) {
									globalMap.put("tFileInputDelimited_1_ERROR_MESSAGE",
											ex_tFileInputDelimited_1.getMessage());
									rowstate_tFileInputDelimited_1.setException(new RuntimeException(String.format(
											"Couldn't parse value for column '%s' in '%s', value is '%s'. Details: %s",
											"FeuxLocauxAgricoles", "row1", temp, ex_tFileInputDelimited_1),
											ex_tFileInputDelimited_1));
								}

							} else {

								row1.FeuxLocauxAgricoles = null;

							}

							columnIndexWithD_tFileInputDelimited_1 = 13;

							temp = fid_tFileInputDelimited_1.get(columnIndexWithD_tFileInputDelimited_1);
							if (temp.length() > 0) {

								try {

									row1.FeuxVoiePublique = ParserUtils
											.parseTo_Integer(ParserUtils.parseTo_Number(temp, ' ', '.'));

								} catch (java.lang.Exception ex_tFileInputDelimited_1) {
									globalMap.put("tFileInputDelimited_1_ERROR_MESSAGE",
											ex_tFileInputDelimited_1.getMessage());
									rowstate_tFileInputDelimited_1.setException(new RuntimeException(String.format(
											"Couldn't parse value for column '%s' in '%s', value is '%s'. Details: %s",
											"FeuxVoiePublique", "row1", temp, ex_tFileInputDelimited_1),
											ex_tFileInputDelimited_1));
								}

							} else {

								row1.FeuxVoiePublique = null;

							}

							columnIndexWithD_tFileInputDelimited_1 = 14;

							temp = fid_tFileInputDelimited_1.get(columnIndexWithD_tFileInputDelimited_1);
							if (temp.length() > 0) {

								try {

									row1.FeuxVehicules = ParserUtils
											.parseTo_Integer(ParserUtils.parseTo_Number(temp, ' ', '.'));

								} catch (java.lang.Exception ex_tFileInputDelimited_1) {
									globalMap.put("tFileInputDelimited_1_ERROR_MESSAGE",
											ex_tFileInputDelimited_1.getMessage());
									rowstate_tFileInputDelimited_1.setException(new RuntimeException(String.format(
											"Couldn't parse value for column '%s' in '%s', value is '%s'. Details: %s",
											"FeuxVehicules", "row1", temp, ex_tFileInputDelimited_1),
											ex_tFileInputDelimited_1));
								}

							} else {

								row1.FeuxVehicules = null;

							}

							columnIndexWithD_tFileInputDelimited_1 = 15;

							temp = fid_tFileInputDelimited_1.get(columnIndexWithD_tFileInputDelimited_1);
							if (temp.length() > 0) {

								try {

									row1.FeuxVegetations = ParserUtils
											.parseTo_Integer(ParserUtils.parseTo_Number(temp, ' ', '.'));

								} catch (java.lang.Exception ex_tFileInputDelimited_1) {
									globalMap.put("tFileInputDelimited_1_ERROR_MESSAGE",
											ex_tFileInputDelimited_1.getMessage());
									rowstate_tFileInputDelimited_1.setException(new RuntimeException(String.format(
											"Couldn't parse value for column '%s' in '%s', value is '%s'. Details: %s",
											"FeuxVegetations", "row1", temp, ex_tFileInputDelimited_1),
											ex_tFileInputDelimited_1));
								}

							} else {

								row1.FeuxVegetations = null;

							}

							columnIndexWithD_tFileInputDelimited_1 = 16;

							temp = fid_tFileInputDelimited_1.get(columnIndexWithD_tFileInputDelimited_1);
							if (temp.length() > 0) {

								try {

									row1.AutresFeux = ParserUtils
											.parseTo_Integer(ParserUtils.parseTo_Number(temp, ' ', '.'));

								} catch (java.lang.Exception ex_tFileInputDelimited_1) {
									globalMap.put("tFileInputDelimited_1_ERROR_MESSAGE",
											ex_tFileInputDelimited_1.getMessage());
									rowstate_tFileInputDelimited_1.setException(new RuntimeException(String.format(
											"Couldn't parse value for column '%s' in '%s', value is '%s'. Details: %s",
											"AutresFeux", "row1", temp, ex_tFileInputDelimited_1),
											ex_tFileInputDelimited_1));
								}

							} else {

								row1.AutresFeux = null;

							}

							columnIndexWithD_tFileInputDelimited_1 = 17;

							temp = fid_tFileInputDelimited_1.get(columnIndexWithD_tFileInputDelimited_1);
							if (temp.length() > 0) {

								try {

									row1.Incendies = ParserUtils
											.parseTo_Integer(ParserUtils.parseTo_Number(temp, ' ', '.'));

								} catch (java.lang.Exception ex_tFileInputDelimited_1) {
									globalMap.put("tFileInputDelimited_1_ERROR_MESSAGE",
											ex_tFileInputDelimited_1.getMessage());
									rowstate_tFileInputDelimited_1.setException(new RuntimeException(String.format(
											"Couldn't parse value for column '%s' in '%s', value is '%s'. Details: %s",
											"Incendies", "row1", temp, ex_tFileInputDelimited_1),
											ex_tFileInputDelimited_1));
								}

							} else {

								row1.Incendies = null;

							}

							columnIndexWithD_tFileInputDelimited_1 = 18;

							temp = fid_tFileInputDelimited_1.get(columnIndexWithD_tFileInputDelimited_1);
							if (temp.length() > 0) {

								try {

									row1.AccidentsLieuxTravail = ParserUtils
											.parseTo_Integer(ParserUtils.parseTo_Number(temp, ' ', '.'));

								} catch (java.lang.Exception ex_tFileInputDelimited_1) {
									globalMap.put("tFileInputDelimited_1_ERROR_MESSAGE",
											ex_tFileInputDelimited_1.getMessage());
									rowstate_tFileInputDelimited_1.setException(new RuntimeException(String.format(
											"Couldn't parse value for column '%s' in '%s', value is '%s'. Details: %s",
											"AccidentsLieuxTravail", "row1", temp, ex_tFileInputDelimited_1),
											ex_tFileInputDelimited_1));
								}

							} else {

								row1.AccidentsLieuxTravail = null;

							}

							columnIndexWithD_tFileInputDelimited_1 = 19;

							temp = fid_tFileInputDelimited_1.get(columnIndexWithD_tFileInputDelimited_1);
							if (temp.length() > 0) {

								try {

									row1.AccidentsDomicile = ParserUtils
											.parseTo_Integer(ParserUtils.parseTo_Number(temp, ' ', '.'));

								} catch (java.lang.Exception ex_tFileInputDelimited_1) {
									globalMap.put("tFileInputDelimited_1_ERROR_MESSAGE",
											ex_tFileInputDelimited_1.getMessage());
									rowstate_tFileInputDelimited_1.setException(new RuntimeException(String.format(
											"Couldn't parse value for column '%s' in '%s', value is '%s'. Details: %s",
											"AccidentsDomicile", "row1", temp, ex_tFileInputDelimited_1),
											ex_tFileInputDelimited_1));
								}

							} else {

								row1.AccidentsDomicile = null;

							}

							columnIndexWithD_tFileInputDelimited_1 = 20;

							temp = fid_tFileInputDelimited_1.get(columnIndexWithD_tFileInputDelimited_1);
							if (temp.length() > 0) {

								try {

									row1.AccidentsSport = ParserUtils
											.parseTo_Integer(ParserUtils.parseTo_Number(temp, ' ', '.'));

								} catch (java.lang.Exception ex_tFileInputDelimited_1) {
									globalMap.put("tFileInputDelimited_1_ERROR_MESSAGE",
											ex_tFileInputDelimited_1.getMessage());
									rowstate_tFileInputDelimited_1.setException(new RuntimeException(String.format(
											"Couldn't parse value for column '%s' in '%s', value is '%s'. Details: %s",
											"AccidentsSport", "row1", temp, ex_tFileInputDelimited_1),
											ex_tFileInputDelimited_1));
								}

							} else {

								row1.AccidentsSport = null;

							}

							columnIndexWithD_tFileInputDelimited_1 = 21;

							temp = fid_tFileInputDelimited_1.get(columnIndexWithD_tFileInputDelimited_1);
							if (temp.length() > 0) {

								try {

									row1.AccidentsVoiePublique = ParserUtils
											.parseTo_Integer(ParserUtils.parseTo_Number(temp, ' ', '.'));

								} catch (java.lang.Exception ex_tFileInputDelimited_1) {
									globalMap.put("tFileInputDelimited_1_ERROR_MESSAGE",
											ex_tFileInputDelimited_1.getMessage());
									rowstate_tFileInputDelimited_1.setException(new RuntimeException(String.format(
											"Couldn't parse value for column '%s' in '%s', value is '%s'. Details: %s",
											"AccidentsVoiePublique", "row1", temp, ex_tFileInputDelimited_1),
											ex_tFileInputDelimited_1));
								}

							} else {

								row1.AccidentsVoiePublique = null;

							}

							columnIndexWithD_tFileInputDelimited_1 = 22;

							temp = fid_tFileInputDelimited_1.get(columnIndexWithD_tFileInputDelimited_1);
							if (temp.length() > 0) {

								try {

									row1.SecoursMontagne = ParserUtils
											.parseTo_Integer(ParserUtils.parseTo_Number(temp, ' ', '.'));

								} catch (java.lang.Exception ex_tFileInputDelimited_1) {
									globalMap.put("tFileInputDelimited_1_ERROR_MESSAGE",
											ex_tFileInputDelimited_1.getMessage());
									rowstate_tFileInputDelimited_1.setException(new RuntimeException(String.format(
											"Couldn't parse value for column '%s' in '%s', value is '%s'. Details: %s",
											"SecoursMontagne", "row1", temp, ex_tFileInputDelimited_1),
											ex_tFileInputDelimited_1));
								}

							} else {

								row1.SecoursMontagne = null;

							}

							columnIndexWithD_tFileInputDelimited_1 = 23;

							temp = fid_tFileInputDelimited_1.get(columnIndexWithD_tFileInputDelimited_1);
							if (temp.length() > 0) {

								try {

									row1.MalaisesLieuxTravail = ParserUtils
											.parseTo_Integer(ParserUtils.parseTo_Number(temp, ' ', '.'));

								} catch (java.lang.Exception ex_tFileInputDelimited_1) {
									globalMap.put("tFileInputDelimited_1_ERROR_MESSAGE",
											ex_tFileInputDelimited_1.getMessage());
									rowstate_tFileInputDelimited_1.setException(new RuntimeException(String.format(
											"Couldn't parse value for column '%s' in '%s', value is '%s'. Details: %s",
											"MalaisesLieuxTravail", "row1", temp, ex_tFileInputDelimited_1),
											ex_tFileInputDelimited_1));
								}

							} else {

								row1.MalaisesLieuxTravail = null;

							}

							columnIndexWithD_tFileInputDelimited_1 = 24;

							temp = fid_tFileInputDelimited_1.get(columnIndexWithD_tFileInputDelimited_1);
							if (temp.length() > 0) {

								try {

									row1.MalaisesDomicileVitale = ParserUtils
											.parseTo_Integer(ParserUtils.parseTo_Number(temp, ' ', '.'));

								} catch (java.lang.Exception ex_tFileInputDelimited_1) {
									globalMap.put("tFileInputDelimited_1_ERROR_MESSAGE",
											ex_tFileInputDelimited_1.getMessage());
									rowstate_tFileInputDelimited_1.setException(new RuntimeException(String.format(
											"Couldn't parse value for column '%s' in '%s', value is '%s'. Details: %s",
											"MalaisesDomicileVitale", "row1", temp, ex_tFileInputDelimited_1),
											ex_tFileInputDelimited_1));
								}

							} else {

								row1.MalaisesDomicileVitale = null;

							}

							columnIndexWithD_tFileInputDelimited_1 = 25;

							temp = fid_tFileInputDelimited_1.get(columnIndexWithD_tFileInputDelimited_1);
							if (temp.length() > 0) {

								try {

									row1.MalaisesDomicileCarence = ParserUtils
											.parseTo_Integer(ParserUtils.parseTo_Number(temp, ' ', '.'));

								} catch (java.lang.Exception ex_tFileInputDelimited_1) {
									globalMap.put("tFileInputDelimited_1_ERROR_MESSAGE",
											ex_tFileInputDelimited_1.getMessage());
									rowstate_tFileInputDelimited_1.setException(new RuntimeException(String.format(
											"Couldn't parse value for column '%s' in '%s', value is '%s'. Details: %s",
											"MalaisesDomicileCarence", "row1", temp, ex_tFileInputDelimited_1),
											ex_tFileInputDelimited_1));
								}

							} else {

								row1.MalaisesDomicileCarence = null;

							}

							columnIndexWithD_tFileInputDelimited_1 = 26;

							temp = fid_tFileInputDelimited_1.get(columnIndexWithD_tFileInputDelimited_1);
							if (temp.length() > 0) {

								try {

									row1.MalaisesSport = ParserUtils
											.parseTo_Integer(ParserUtils.parseTo_Number(temp, ' ', '.'));

								} catch (java.lang.Exception ex_tFileInputDelimited_1) {
									globalMap.put("tFileInputDelimited_1_ERROR_MESSAGE",
											ex_tFileInputDelimited_1.getMessage());
									rowstate_tFileInputDelimited_1.setException(new RuntimeException(String.format(
											"Couldn't parse value for column '%s' in '%s', value is '%s'. Details: %s",
											"MalaisesSport", "row1", temp, ex_tFileInputDelimited_1),
											ex_tFileInputDelimited_1));
								}

							} else {

								row1.MalaisesSport = null;

							}

							columnIndexWithD_tFileInputDelimited_1 = 27;

							temp = fid_tFileInputDelimited_1.get(columnIndexWithD_tFileInputDelimited_1);
							if (temp.length() > 0) {

								try {

									row1.MalaisesVoiePublique = ParserUtils
											.parseTo_Integer(ParserUtils.parseTo_Number(temp, ' ', '.'));

								} catch (java.lang.Exception ex_tFileInputDelimited_1) {
									globalMap.put("tFileInputDelimited_1_ERROR_MESSAGE",
											ex_tFileInputDelimited_1.getMessage());
									rowstate_tFileInputDelimited_1.setException(new RuntimeException(String.format(
											"Couldn't parse value for column '%s' in '%s', value is '%s'. Details: %s",
											"MalaisesVoiePublique", "row1", temp, ex_tFileInputDelimited_1),
											ex_tFileInputDelimited_1));
								}

							} else {

								row1.MalaisesVoiePublique = null;

							}

							columnIndexWithD_tFileInputDelimited_1 = 28;

							temp = fid_tFileInputDelimited_1.get(columnIndexWithD_tFileInputDelimited_1);
							if (temp.length() > 0) {

								try {

									row1.Autolyses = ParserUtils
											.parseTo_Integer(ParserUtils.parseTo_Number(temp, ' ', '.'));

								} catch (java.lang.Exception ex_tFileInputDelimited_1) {
									globalMap.put("tFileInputDelimited_1_ERROR_MESSAGE",
											ex_tFileInputDelimited_1.getMessage());
									rowstate_tFileInputDelimited_1.setException(new RuntimeException(String.format(
											"Couldn't parse value for column '%s' in '%s', value is '%s'. Details: %s",
											"Autolyses", "row1", temp, ex_tFileInputDelimited_1),
											ex_tFileInputDelimited_1));
								}

							} else {

								row1.Autolyses = null;

							}

							columnIndexWithD_tFileInputDelimited_1 = 29;

							temp = fid_tFileInputDelimited_1.get(columnIndexWithD_tFileInputDelimited_1);
							if (temp.length() > 0) {

								try {

									row1.SecoursEauxInterieuresPiscines = ParserUtils
											.parseTo_Integer(ParserUtils.parseTo_Number(temp, ' ', '.'));

								} catch (java.lang.Exception ex_tFileInputDelimited_1) {
									globalMap.put("tFileInputDelimited_1_ERROR_MESSAGE",
											ex_tFileInputDelimited_1.getMessage());
									rowstate_tFileInputDelimited_1.setException(new RuntimeException(String.format(
											"Couldn't parse value for column '%s' in '%s', value is '%s'. Details: %s",
											"SecoursEauxInterieuresPiscines", "row1", temp, ex_tFileInputDelimited_1),
											ex_tFileInputDelimited_1));
								}

							} else {

								row1.SecoursEauxInterieuresPiscines = null;

							}

							columnIndexWithD_tFileInputDelimited_1 = 30;

							temp = fid_tFileInputDelimited_1.get(columnIndexWithD_tFileInputDelimited_1);
							if (temp.length() > 0) {

								try {

									row1.SecoursMer = ParserUtils
											.parseTo_Integer(ParserUtils.parseTo_Number(temp, ' ', '.'));

								} catch (java.lang.Exception ex_tFileInputDelimited_1) {
									globalMap.put("tFileInputDelimited_1_ERROR_MESSAGE",
											ex_tFileInputDelimited_1.getMessage());
									rowstate_tFileInputDelimited_1.setException(new RuntimeException(String.format(
											"Couldn't parse value for column '%s' in '%s', value is '%s'. Details: %s",
											"SecoursMer", "row1", temp, ex_tFileInputDelimited_1),
											ex_tFileInputDelimited_1));
								}

							} else {

								row1.SecoursMer = null;

							}

							columnIndexWithD_tFileInputDelimited_1 = 31;

							temp = fid_tFileInputDelimited_1.get(columnIndexWithD_tFileInputDelimited_1);
							if (temp.length() > 0) {

								try {

									row1.Intoxications = ParserUtils
											.parseTo_Integer(ParserUtils.parseTo_Number(temp, ' ', '.'));

								} catch (java.lang.Exception ex_tFileInputDelimited_1) {
									globalMap.put("tFileInputDelimited_1_ERROR_MESSAGE",
											ex_tFileInputDelimited_1.getMessage());
									rowstate_tFileInputDelimited_1.setException(new RuntimeException(String.format(
											"Couldn't parse value for column '%s' in '%s', value is '%s'. Details: %s",
											"Intoxications", "row1", temp, ex_tFileInputDelimited_1),
											ex_tFileInputDelimited_1));
								}

							} else {

								row1.Intoxications = null;

							}

							columnIndexWithD_tFileInputDelimited_1 = 32;

							temp = fid_tFileInputDelimited_1.get(columnIndexWithD_tFileInputDelimited_1);
							if (temp.length() > 0) {

								try {

									row1.IntoxicationsCO = ParserUtils
											.parseTo_Integer(ParserUtils.parseTo_Number(temp, ' ', '.'));

								} catch (java.lang.Exception ex_tFileInputDelimited_1) {
									globalMap.put("tFileInputDelimited_1_ERROR_MESSAGE",
											ex_tFileInputDelimited_1.getMessage());
									rowstate_tFileInputDelimited_1.setException(new RuntimeException(String.format(
											"Couldn't parse value for column '%s' in '%s', value is '%s'. Details: %s",
											"IntoxicationsCO", "row1", temp, ex_tFileInputDelimited_1),
											ex_tFileInputDelimited_1));
								}

							} else {

								row1.IntoxicationsCO = null;

							}

							columnIndexWithD_tFileInputDelimited_1 = 33;

							temp = fid_tFileInputDelimited_1.get(columnIndexWithD_tFileInputDelimited_1);
							if (temp.length() > 0) {

								try {

									row1.AutresSAV = ParserUtils
											.parseTo_Integer(ParserUtils.parseTo_Number(temp, ' ', '.'));

								} catch (java.lang.Exception ex_tFileInputDelimited_1) {
									globalMap.put("tFileInputDelimited_1_ERROR_MESSAGE",
											ex_tFileInputDelimited_1.getMessage());
									rowstate_tFileInputDelimited_1.setException(new RuntimeException(String.format(
											"Couldn't parse value for column '%s' in '%s', value is '%s'. Details: %s",
											"AutresSAV", "row1", temp, ex_tFileInputDelimited_1),
											ex_tFileInputDelimited_1));
								}

							} else {

								row1.AutresSAV = null;

							}

							columnIndexWithD_tFileInputDelimited_1 = 34;

							temp = fid_tFileInputDelimited_1.get(columnIndexWithD_tFileInputDelimited_1);
							if (temp.length() > 0) {

								try {

									row1.SecoursVictime = ParserUtils
											.parseTo_Integer(ParserUtils.parseTo_Number(temp, ' ', '.'));

								} catch (java.lang.Exception ex_tFileInputDelimited_1) {
									globalMap.put("tFileInputDelimited_1_ERROR_MESSAGE",
											ex_tFileInputDelimited_1.getMessage());
									rowstate_tFileInputDelimited_1.setException(new RuntimeException(String.format(
											"Couldn't parse value for column '%s' in '%s', value is '%s'. Details: %s",
											"SecoursVictime", "row1", temp, ex_tFileInputDelimited_1),
											ex_tFileInputDelimited_1));
								}

							} else {

								row1.SecoursVictime = null;

							}

							columnIndexWithD_tFileInputDelimited_1 = 35;

							temp = fid_tFileInputDelimited_1.get(columnIndexWithD_tFileInputDelimited_1);
							if (temp.length() > 0) {

								try {

									row1.RelevagePersonnes = ParserUtils
											.parseTo_Integer(ParserUtils.parseTo_Number(temp, ' ', '.'));

								} catch (java.lang.Exception ex_tFileInputDelimited_1) {
									globalMap.put("tFileInputDelimited_1_ERROR_MESSAGE",
											ex_tFileInputDelimited_1.getMessage());
									rowstate_tFileInputDelimited_1.setException(new RuntimeException(String.format(
											"Couldn't parse value for column '%s' in '%s', value is '%s'. Details: %s",
											"RelevagePersonnes", "row1", temp, ex_tFileInputDelimited_1),
											ex_tFileInputDelimited_1));
								}

							} else {

								row1.RelevagePersonnes = null;

							}

							columnIndexWithD_tFileInputDelimited_1 = 36;

							temp = fid_tFileInputDelimited_1.get(columnIndexWithD_tFileInputDelimited_1);
							if (temp.length() > 0) {

								try {

									row1.RecherchePersonnes = ParserUtils
											.parseTo_Integer(ParserUtils.parseTo_Number(temp, ' ', '.'));

								} catch (java.lang.Exception ex_tFileInputDelimited_1) {
									globalMap.put("tFileInputDelimited_1_ERROR_MESSAGE",
											ex_tFileInputDelimited_1.getMessage());
									rowstate_tFileInputDelimited_1.setException(new RuntimeException(String.format(
											"Couldn't parse value for column '%s' in '%s', value is '%s'. Details: %s",
											"RecherchePersonnes", "row1", temp, ex_tFileInputDelimited_1),
											ex_tFileInputDelimited_1));
								}

							} else {

								row1.RecherchePersonnes = null;

							}

							columnIndexWithD_tFileInputDelimited_1 = 37;

							temp = fid_tFileInputDelimited_1.get(columnIndexWithD_tFileInputDelimited_1);
							if (temp.length() > 0) {

								try {

									row1.AidesPersonne = ParserUtils
											.parseTo_Integer(ParserUtils.parseTo_Number(temp, ' ', '.'));

								} catch (java.lang.Exception ex_tFileInputDelimited_1) {
									globalMap.put("tFileInputDelimited_1_ERROR_MESSAGE",
											ex_tFileInputDelimited_1.getMessage());
									rowstate_tFileInputDelimited_1.setException(new RuntimeException(String.format(
											"Couldn't parse value for column '%s' in '%s', value is '%s'. Details: %s",
											"AidesPersonne", "row1", temp, ex_tFileInputDelimited_1),
											ex_tFileInputDelimited_1));
								}

							} else {

								row1.AidesPersonne = null;

							}

							columnIndexWithD_tFileInputDelimited_1 = 38;

							temp = fid_tFileInputDelimited_1.get(columnIndexWithD_tFileInputDelimited_1);
							if (temp.length() > 0) {

								try {

									row1.SecoursPersonne = ParserUtils
											.parseTo_Integer(ParserUtils.parseTo_Number(temp, ' ', '.'));

								} catch (java.lang.Exception ex_tFileInputDelimited_1) {
									globalMap.put("tFileInputDelimited_1_ERROR_MESSAGE",
											ex_tFileInputDelimited_1.getMessage());
									rowstate_tFileInputDelimited_1.setException(new RuntimeException(String.format(
											"Couldn't parse value for column '%s' in '%s', value is '%s'. Details: %s",
											"SecoursPersonne", "row1", temp, ex_tFileInputDelimited_1),
											ex_tFileInputDelimited_1));
								}

							} else {

								row1.SecoursPersonne = null;

							}

							columnIndexWithD_tFileInputDelimited_1 = 39;

							temp = fid_tFileInputDelimited_1.get(columnIndexWithD_tFileInputDelimited_1);
							if (temp.length() > 0) {

								try {

									row1.AccientsRoutiers = ParserUtils
											.parseTo_Integer(ParserUtils.parseTo_Number(temp, ' ', '.'));

								} catch (java.lang.Exception ex_tFileInputDelimited_1) {
									globalMap.put("tFileInputDelimited_1_ERROR_MESSAGE",
											ex_tFileInputDelimited_1.getMessage());
									rowstate_tFileInputDelimited_1.setException(new RuntimeException(String.format(
											"Couldn't parse value for column '%s' in '%s', value is '%s'. Details: %s",
											"AccientsRoutiers", "row1", temp, ex_tFileInputDelimited_1),
											ex_tFileInputDelimited_1));
								}

							} else {

								row1.AccientsRoutiers = null;

							}

							columnIndexWithD_tFileInputDelimited_1 = 40;

							temp = fid_tFileInputDelimited_1.get(columnIndexWithD_tFileInputDelimited_1);
							if (temp.length() > 0) {

								try {

									row1.AccidentsFerroviaires = ParserUtils
											.parseTo_Integer(ParserUtils.parseTo_Number(temp, ' ', '.'));

								} catch (java.lang.Exception ex_tFileInputDelimited_1) {
									globalMap.put("tFileInputDelimited_1_ERROR_MESSAGE",
											ex_tFileInputDelimited_1.getMessage());
									rowstate_tFileInputDelimited_1.setException(new RuntimeException(String.format(
											"Couldn't parse value for column '%s' in '%s', value is '%s'. Details: %s",
											"AccidentsFerroviaires", "row1", temp, ex_tFileInputDelimited_1),
											ex_tFileInputDelimited_1));
								}

							} else {

								row1.AccidentsFerroviaires = null;

							}

							columnIndexWithD_tFileInputDelimited_1 = 41;

							temp = fid_tFileInputDelimited_1.get(columnIndexWithD_tFileInputDelimited_1);
							if (temp.length() > 0) {

								try {

									row1.AccidentsAeriens = ParserUtils
											.parseTo_Integer(ParserUtils.parseTo_Number(temp, ' ', '.'));

								} catch (java.lang.Exception ex_tFileInputDelimited_1) {
									globalMap.put("tFileInputDelimited_1_ERROR_MESSAGE",
											ex_tFileInputDelimited_1.getMessage());
									rowstate_tFileInputDelimited_1.setException(new RuntimeException(String.format(
											"Couldn't parse value for column '%s' in '%s', value is '%s'. Details: %s",
											"AccidentsAeriens", "row1", temp, ex_tFileInputDelimited_1),
											ex_tFileInputDelimited_1));
								}

							} else {

								row1.AccidentsAeriens = null;

							}

							columnIndexWithD_tFileInputDelimited_1 = 42;

							temp = fid_tFileInputDelimited_1.get(columnIndexWithD_tFileInputDelimited_1);
							if (temp.length() > 0) {

								try {

									row1.AccidentsNavigation = ParserUtils
											.parseTo_Integer(ParserUtils.parseTo_Number(temp, ' ', '.'));

								} catch (java.lang.Exception ex_tFileInputDelimited_1) {
									globalMap.put("tFileInputDelimited_1_ERROR_MESSAGE",
											ex_tFileInputDelimited_1.getMessage());
									rowstate_tFileInputDelimited_1.setException(new RuntimeException(String.format(
											"Couldn't parse value for column '%s' in '%s', value is '%s'. Details: %s",
											"AccidentsNavigation", "row1", temp, ex_tFileInputDelimited_1),
											ex_tFileInputDelimited_1));
								}

							} else {

								row1.AccidentsNavigation = null;

							}

							columnIndexWithD_tFileInputDelimited_1 = 43;

							temp = fid_tFileInputDelimited_1.get(columnIndexWithD_tFileInputDelimited_1);
							if (temp.length() > 0) {

								try {

									row1.AccidentsTeleportage = ParserUtils
											.parseTo_Integer(ParserUtils.parseTo_Number(temp, ' ', '.'));

								} catch (java.lang.Exception ex_tFileInputDelimited_1) {
									globalMap.put("tFileInputDelimited_1_ERROR_MESSAGE",
											ex_tFileInputDelimited_1.getMessage());
									rowstate_tFileInputDelimited_1.setException(new RuntimeException(String.format(
											"Couldn't parse value for column '%s' in '%s', value is '%s'. Details: %s",
											"AccidentsTeleportage", "row1", temp, ex_tFileInputDelimited_1),
											ex_tFileInputDelimited_1));
								}

							} else {

								row1.AccidentsTeleportage = null;

							}

							columnIndexWithD_tFileInputDelimited_1 = 44;

							temp = fid_tFileInputDelimited_1.get(columnIndexWithD_tFileInputDelimited_1);
							if (temp.length() > 0) {

								try {

									row1.AccidentsCirculation = ParserUtils
											.parseTo_Integer(ParserUtils.parseTo_Number(temp, ' ', '.'));

								} catch (java.lang.Exception ex_tFileInputDelimited_1) {
									globalMap.put("tFileInputDelimited_1_ERROR_MESSAGE",
											ex_tFileInputDelimited_1.getMessage());
									rowstate_tFileInputDelimited_1.setException(new RuntimeException(String.format(
											"Couldn't parse value for column '%s' in '%s', value is '%s'. Details: %s",
											"AccidentsCirculation", "row1", temp, ex_tFileInputDelimited_1),
											ex_tFileInputDelimited_1));
								}

							} else {

								row1.AccidentsCirculation = null;

							}

							columnIndexWithD_tFileInputDelimited_1 = 45;

							temp = fid_tFileInputDelimited_1.get(columnIndexWithD_tFileInputDelimited_1);
							if (temp.length() > 0) {

								try {

									row1.OdeursFuiteGaz = ParserUtils
											.parseTo_Integer(ParserUtils.parseTo_Number(temp, ' ', '.'));

								} catch (java.lang.Exception ex_tFileInputDelimited_1) {
									globalMap.put("tFileInputDelimited_1_ERROR_MESSAGE",
											ex_tFileInputDelimited_1.getMessage());
									rowstate_tFileInputDelimited_1.setException(new RuntimeException(String.format(
											"Couldn't parse value for column '%s' in '%s', value is '%s'. Details: %s",
											"OdeursFuiteGaz", "row1", temp, ex_tFileInputDelimited_1),
											ex_tFileInputDelimited_1));
								}

							} else {

								row1.OdeursFuiteGaz = null;

							}

							columnIndexWithD_tFileInputDelimited_1 = 46;

							temp = fid_tFileInputDelimited_1.get(columnIndexWithD_tFileInputDelimited_1);
							if (temp.length() > 0) {

								try {

									row1.OdeursAutres = ParserUtils
											.parseTo_Integer(ParserUtils.parseTo_Number(temp, ' ', '.'));

								} catch (java.lang.Exception ex_tFileInputDelimited_1) {
									globalMap.put("tFileInputDelimited_1_ERROR_MESSAGE",
											ex_tFileInputDelimited_1.getMessage());
									rowstate_tFileInputDelimited_1.setException(new RuntimeException(String.format(
											"Couldn't parse value for column '%s' in '%s', value is '%s'. Details: %s",
											"OdeursAutres", "row1", temp, ex_tFileInputDelimited_1),
											ex_tFileInputDelimited_1));
								}

							} else {

								row1.OdeursAutres = null;

							}

							columnIndexWithD_tFileInputDelimited_1 = 47;

							temp = fid_tFileInputDelimited_1.get(columnIndexWithD_tFileInputDelimited_1);
							if (temp.length() > 0) {

								try {

									row1.FaitDusElectricite = ParserUtils
											.parseTo_Integer(ParserUtils.parseTo_Number(temp, ' ', '.'));

								} catch (java.lang.Exception ex_tFileInputDelimited_1) {
									globalMap.put("tFileInputDelimited_1_ERROR_MESSAGE",
											ex_tFileInputDelimited_1.getMessage());
									rowstate_tFileInputDelimited_1.setException(new RuntimeException(String.format(
											"Couldn't parse value for column '%s' in '%s', value is '%s'. Details: %s",
											"FaitDusElectricite", "row1", temp, ex_tFileInputDelimited_1),
											ex_tFileInputDelimited_1));
								}

							} else {

								row1.FaitDusElectricite = null;

							}

							columnIndexWithD_tFileInputDelimited_1 = 48;

							temp = fid_tFileInputDelimited_1.get(columnIndexWithD_tFileInputDelimited_1);
							if (temp.length() > 0) {

								try {

									row1.PollutionsContaminations = ParserUtils
											.parseTo_Integer(ParserUtils.parseTo_Number(temp, ' ', '.'));

								} catch (java.lang.Exception ex_tFileInputDelimited_1) {
									globalMap.put("tFileInputDelimited_1_ERROR_MESSAGE",
											ex_tFileInputDelimited_1.getMessage());
									rowstate_tFileInputDelimited_1.setException(new RuntimeException(String.format(
											"Couldn't parse value for column '%s' in '%s', value is '%s'. Details: %s",
											"PollutionsContaminations", "row1", temp, ex_tFileInputDelimited_1),
											ex_tFileInputDelimited_1));
								}

							} else {

								row1.PollutionsContaminations = null;

							}

							columnIndexWithD_tFileInputDelimited_1 = 49;

							temp = fid_tFileInputDelimited_1.get(columnIndexWithD_tFileInputDelimited_1);
							if (temp.length() > 0) {

								try {

									row1.AutresRisquesTechnologiques = ParserUtils
											.parseTo_Integer(ParserUtils.parseTo_Number(temp, ' ', '.'));

								} catch (java.lang.Exception ex_tFileInputDelimited_1) {
									globalMap.put("tFileInputDelimited_1_ERROR_MESSAGE",
											ex_tFileInputDelimited_1.getMessage());
									rowstate_tFileInputDelimited_1.setException(new RuntimeException(String.format(
											"Couldn't parse value for column '%s' in '%s', value is '%s'. Details: %s",
											"AutresRisquesTechnologiques", "row1", temp, ex_tFileInputDelimited_1),
											ex_tFileInputDelimited_1));
								}

							} else {

								row1.AutresRisquesTechnologiques = null;

							}

							columnIndexWithD_tFileInputDelimited_1 = 50;

							temp = fid_tFileInputDelimited_1.get(columnIndexWithD_tFileInputDelimited_1);
							if (temp.length() > 0) {

								try {

									row1.RisquesTechnologiques = ParserUtils
											.parseTo_Integer(ParserUtils.parseTo_Number(temp, ' ', '.'));

								} catch (java.lang.Exception ex_tFileInputDelimited_1) {
									globalMap.put("tFileInputDelimited_1_ERROR_MESSAGE",
											ex_tFileInputDelimited_1.getMessage());
									rowstate_tFileInputDelimited_1.setException(new RuntimeException(String.format(
											"Couldn't parse value for column '%s' in '%s', value is '%s'. Details: %s",
											"RisquesTechnologiques", "row1", temp, ex_tFileInputDelimited_1),
											ex_tFileInputDelimited_1));
								}

							} else {

								row1.RisquesTechnologiques = null;

							}

							columnIndexWithD_tFileInputDelimited_1 = 51;

							temp = fid_tFileInputDelimited_1.get(columnIndexWithD_tFileInputDelimited_1);
							if (temp.length() > 0) {

								try {

									row1.FuitesEau = ParserUtils
											.parseTo_Integer(ParserUtils.parseTo_Number(temp, ' ', '.'));

								} catch (java.lang.Exception ex_tFileInputDelimited_1) {
									globalMap.put("tFileInputDelimited_1_ERROR_MESSAGE",
											ex_tFileInputDelimited_1.getMessage());
									rowstate_tFileInputDelimited_1.setException(new RuntimeException(String.format(
											"Couldn't parse value for column '%s' in '%s', value is '%s'. Details: %s",
											"FuitesEau", "row1", temp, ex_tFileInputDelimited_1),
											ex_tFileInputDelimited_1));
								}

							} else {

								row1.FuitesEau = null;

							}

							columnIndexWithD_tFileInputDelimited_1 = 52;

							temp = fid_tFileInputDelimited_1.get(columnIndexWithD_tFileInputDelimited_1);
							if (temp.length() > 0) {

								try {

									row1.Inondations = ParserUtils
											.parseTo_Integer(ParserUtils.parseTo_Number(temp, ' ', '.'));

								} catch (java.lang.Exception ex_tFileInputDelimited_1) {
									globalMap.put("tFileInputDelimited_1_ERROR_MESSAGE",
											ex_tFileInputDelimited_1.getMessage());
									rowstate_tFileInputDelimited_1.setException(new RuntimeException(String.format(
											"Couldn't parse value for column '%s' in '%s', value is '%s'. Details: %s",
											"Inondations", "row1", temp, ex_tFileInputDelimited_1),
											ex_tFileInputDelimited_1));
								}

							} else {

								row1.Inondations = null;

							}

							columnIndexWithD_tFileInputDelimited_1 = 53;

							temp = fid_tFileInputDelimited_1.get(columnIndexWithD_tFileInputDelimited_1);
							if (temp.length() > 0) {

								try {

									row1.OuverturesPortes = ParserUtils
											.parseTo_Integer(ParserUtils.parseTo_Number(temp, ' ', '.'));

								} catch (java.lang.Exception ex_tFileInputDelimited_1) {
									globalMap.put("tFileInputDelimited_1_ERROR_MESSAGE",
											ex_tFileInputDelimited_1.getMessage());
									rowstate_tFileInputDelimited_1.setException(new RuntimeException(String.format(
											"Couldn't parse value for column '%s' in '%s', value is '%s'. Details: %s",
											"OuverturesPortes", "row1", temp, ex_tFileInputDelimited_1),
											ex_tFileInputDelimited_1));
								}

							} else {

								row1.OuverturesPortes = null;

							}

							columnIndexWithD_tFileInputDelimited_1 = 54;

							temp = fid_tFileInputDelimited_1.get(columnIndexWithD_tFileInputDelimited_1);
							if (temp.length() > 0) {

								try {

									row1.RecherchesObjets = ParserUtils
											.parseTo_Integer(ParserUtils.parseTo_Number(temp, ' ', '.'));

								} catch (java.lang.Exception ex_tFileInputDelimited_1) {
									globalMap.put("tFileInputDelimited_1_ERROR_MESSAGE",
											ex_tFileInputDelimited_1.getMessage());
									rowstate_tFileInputDelimited_1.setException(new RuntimeException(String.format(
											"Couldn't parse value for column '%s' in '%s', value is '%s'. Details: %s",
											"RecherchesObjets", "row1", temp, ex_tFileInputDelimited_1),
											ex_tFileInputDelimited_1));
								}

							} else {

								row1.RecherchesObjets = null;

							}

							columnIndexWithD_tFileInputDelimited_1 = 55;

							temp = fid_tFileInputDelimited_1.get(columnIndexWithD_tFileInputDelimited_1);
							if (temp.length() > 0) {

								try {

									row1.BruitsSuspects = ParserUtils
											.parseTo_Integer(ParserUtils.parseTo_Number(temp, ' ', '.'));

								} catch (java.lang.Exception ex_tFileInputDelimited_1) {
									globalMap.put("tFileInputDelimited_1_ERROR_MESSAGE",
											ex_tFileInputDelimited_1.getMessage());
									rowstate_tFileInputDelimited_1.setException(new RuntimeException(String.format(
											"Couldn't parse value for column '%s' in '%s', value is '%s'. Details: %s",
											"BruitsSuspects", "row1", temp, ex_tFileInputDelimited_1),
											ex_tFileInputDelimited_1));
								}

							} else {

								row1.BruitsSuspects = null;

							}

							columnIndexWithD_tFileInputDelimited_1 = 56;

							temp = fid_tFileInputDelimited_1.get(columnIndexWithD_tFileInputDelimited_1);
							if (temp.length() > 0) {

								try {

									row1.ProtectionBiens = ParserUtils
											.parseTo_Integer(ParserUtils.parseTo_Number(temp, ' ', '.'));

								} catch (java.lang.Exception ex_tFileInputDelimited_1) {
									globalMap.put("tFileInputDelimited_1_ERROR_MESSAGE",
											ex_tFileInputDelimited_1.getMessage());
									rowstate_tFileInputDelimited_1.setException(new RuntimeException(String.format(
											"Couldn't parse value for column '%s' in '%s', value is '%s'. Details: %s",
											"ProtectionBiens", "row1", temp, ex_tFileInputDelimited_1),
											ex_tFileInputDelimited_1));
								}

							} else {

								row1.ProtectionBiens = null;

							}

							columnIndexWithD_tFileInputDelimited_1 = 57;

							temp = fid_tFileInputDelimited_1.get(columnIndexWithD_tFileInputDelimited_1);
							if (temp.length() > 0) {

								try {

									row1.FaussesAlertes = ParserUtils
											.parseTo_Integer(ParserUtils.parseTo_Number(temp, ' ', '.'));

								} catch (java.lang.Exception ex_tFileInputDelimited_1) {
									globalMap.put("tFileInputDelimited_1_ERROR_MESSAGE",
											ex_tFileInputDelimited_1.getMessage());
									rowstate_tFileInputDelimited_1.setException(new RuntimeException(String.format(
											"Couldn't parse value for column '%s' in '%s', value is '%s'. Details: %s",
											"FaussesAlertes", "row1", temp, ex_tFileInputDelimited_1),
											ex_tFileInputDelimited_1));
								}

							} else {

								row1.FaussesAlertes = null;

							}

							columnIndexWithD_tFileInputDelimited_1 = 58;

							temp = fid_tFileInputDelimited_1.get(columnIndexWithD_tFileInputDelimited_1);
							if (temp.length() > 0) {

								try {

									row1.FaussesAlertesDAAF = ParserUtils
											.parseTo_Integer(ParserUtils.parseTo_Number(temp, ' ', '.'));

								} catch (java.lang.Exception ex_tFileInputDelimited_1) {
									globalMap.put("tFileInputDelimited_1_ERROR_MESSAGE",
											ex_tFileInputDelimited_1.getMessage());
									rowstate_tFileInputDelimited_1.setException(new RuntimeException(String.format(
											"Couldn't parse value for column '%s' in '%s', value is '%s'. Details: %s",
											"FaussesAlertesDAAF", "row1", temp, ex_tFileInputDelimited_1),
											ex_tFileInputDelimited_1));
								}

							} else {

								row1.FaussesAlertesDAAF = null;

							}

							columnIndexWithD_tFileInputDelimited_1 = 59;

							temp = fid_tFileInputDelimited_1.get(columnIndexWithD_tFileInputDelimited_1);
							if (temp.length() > 0) {

								try {

									row1.FaitsAnimaux = ParserUtils
											.parseTo_Integer(ParserUtils.parseTo_Number(temp, ' ', '.'));

								} catch (java.lang.Exception ex_tFileInputDelimited_1) {
									globalMap.put("tFileInputDelimited_1_ERROR_MESSAGE",
											ex_tFileInputDelimited_1.getMessage());
									rowstate_tFileInputDelimited_1.setException(new RuntimeException(String.format(
											"Couldn't parse value for column '%s' in '%s', value is '%s'. Details: %s",
											"FaitsAnimaux", "row1", temp, ex_tFileInputDelimited_1),
											ex_tFileInputDelimited_1));
								}

							} else {

								row1.FaitsAnimaux = null;

							}

							columnIndexWithD_tFileInputDelimited_1 = 60;

							temp = fid_tFileInputDelimited_1.get(columnIndexWithD_tFileInputDelimited_1);
							if (temp.length() > 0) {

								try {

									row1.FaitsHymenopteres = ParserUtils
											.parseTo_Integer(ParserUtils.parseTo_Number(temp, ' ', '.'));

								} catch (java.lang.Exception ex_tFileInputDelimited_1) {
									globalMap.put("tFileInputDelimited_1_ERROR_MESSAGE",
											ex_tFileInputDelimited_1.getMessage());
									rowstate_tFileInputDelimited_1.setException(new RuntimeException(String.format(
											"Couldn't parse value for column '%s' in '%s', value is '%s'. Details: %s",
											"FaitsHymenopteres", "row1", temp, ex_tFileInputDelimited_1),
											ex_tFileInputDelimited_1));
								}

							} else {

								row1.FaitsHymenopteres = null;

							}

							columnIndexWithD_tFileInputDelimited_1 = 61;

							temp = fid_tFileInputDelimited_1.get(columnIndexWithD_tFileInputDelimited_1);
							if (temp.length() > 0) {

								try {

									row1.DegagementsVoiePublique = ParserUtils
											.parseTo_Integer(ParserUtils.parseTo_Number(temp, ' ', '.'));

								} catch (java.lang.Exception ex_tFileInputDelimited_1) {
									globalMap.put("tFileInputDelimited_1_ERROR_MESSAGE",
											ex_tFileInputDelimited_1.getMessage());
									rowstate_tFileInputDelimited_1.setException(new RuntimeException(String.format(
											"Couldn't parse value for column '%s' in '%s', value is '%s'. Details: %s",
											"DegagementsVoiePublique", "row1", temp, ex_tFileInputDelimited_1),
											ex_tFileInputDelimited_1));
								}

							} else {

								row1.DegagementsVoiePublique = null;

							}

							columnIndexWithD_tFileInputDelimited_1 = 62;

							temp = fid_tFileInputDelimited_1.get(columnIndexWithD_tFileInputDelimited_1);
							if (temp.length() > 0) {

								try {

									row1.NettoyagesVoiePublique = ParserUtils
											.parseTo_Integer(ParserUtils.parseTo_Number(temp, ' ', '.'));

								} catch (java.lang.Exception ex_tFileInputDelimited_1) {
									globalMap.put("tFileInputDelimited_1_ERROR_MESSAGE",
											ex_tFileInputDelimited_1.getMessage());
									rowstate_tFileInputDelimited_1.setException(new RuntimeException(String.format(
											"Couldn't parse value for column '%s' in '%s', value is '%s'. Details: %s",
											"NettoyagesVoiePublique", "row1", temp, ex_tFileInputDelimited_1),
											ex_tFileInputDelimited_1));
								}

							} else {

								row1.NettoyagesVoiePublique = null;

							}

							columnIndexWithD_tFileInputDelimited_1 = 63;

							temp = fid_tFileInputDelimited_1.get(columnIndexWithD_tFileInputDelimited_1);
							if (temp.length() > 0) {

								try {

									row1.Eboulements = ParserUtils
											.parseTo_Integer(ParserUtils.parseTo_Number(temp, ' ', '.'));

								} catch (java.lang.Exception ex_tFileInputDelimited_1) {
									globalMap.put("tFileInputDelimited_1_ERROR_MESSAGE",
											ex_tFileInputDelimited_1.getMessage());
									rowstate_tFileInputDelimited_1.setException(new RuntimeException(String.format(
											"Couldn't parse value for column '%s' in '%s', value is '%s'. Details: %s",
											"Eboulements", "row1", temp, ex_tFileInputDelimited_1),
											ex_tFileInputDelimited_1));
								}

							} else {

								row1.Eboulements = null;

							}

							columnIndexWithD_tFileInputDelimited_1 = 64;

							temp = fid_tFileInputDelimited_1.get(columnIndexWithD_tFileInputDelimited_1);
							if (temp.length() > 0) {

								try {

									row1.DeposesObjets = ParserUtils
											.parseTo_Integer(ParserUtils.parseTo_Number(temp, ' ', '.'));

								} catch (java.lang.Exception ex_tFileInputDelimited_1) {
									globalMap.put("tFileInputDelimited_1_ERROR_MESSAGE",
											ex_tFileInputDelimited_1.getMessage());
									rowstate_tFileInputDelimited_1.setException(new RuntimeException(String.format(
											"Couldn't parse value for column '%s' in '%s', value is '%s'. Details: %s",
											"DeposesObjets", "row1", temp, ex_tFileInputDelimited_1),
											ex_tFileInputDelimited_1));
								}

							} else {

								row1.DeposesObjets = null;

							}

							columnIndexWithD_tFileInputDelimited_1 = 65;

							temp = fid_tFileInputDelimited_1.get(columnIndexWithD_tFileInputDelimited_1);
							if (temp.length() > 0) {

								try {

									row1.PiquetsSecuriteSurveillances = ParserUtils
											.parseTo_Integer(ParserUtils.parseTo_Number(temp, ' ', '.'));

								} catch (java.lang.Exception ex_tFileInputDelimited_1) {
									globalMap.put("tFileInputDelimited_1_ERROR_MESSAGE",
											ex_tFileInputDelimited_1.getMessage());
									rowstate_tFileInputDelimited_1.setException(new RuntimeException(String.format(
											"Couldn't parse value for column '%s' in '%s', value is '%s'. Details: %s",
											"PiquetsSecuriteSurveillances", "row1", temp, ex_tFileInputDelimited_1),
											ex_tFileInputDelimited_1));
								}

							} else {

								row1.PiquetsSecuriteSurveillances = null;

							}

							columnIndexWithD_tFileInputDelimited_1 = 66;

							temp = fid_tFileInputDelimited_1.get(columnIndexWithD_tFileInputDelimited_1);
							if (temp.length() > 0) {

								try {

									row1.EnginsExplosifs = ParserUtils
											.parseTo_Integer(ParserUtils.parseTo_Number(temp, ' ', '.'));

								} catch (java.lang.Exception ex_tFileInputDelimited_1) {
									globalMap.put("tFileInputDelimited_1_ERROR_MESSAGE",
											ex_tFileInputDelimited_1.getMessage());
									rowstate_tFileInputDelimited_1.setException(new RuntimeException(String.format(
											"Couldn't parse value for column '%s' in '%s', value is '%s'. Details: %s",
											"EnginsExplosifs", "row1", temp, ex_tFileInputDelimited_1),
											ex_tFileInputDelimited_1));
								}

							} else {

								row1.EnginsExplosifs = null;

							}

							columnIndexWithD_tFileInputDelimited_1 = 67;

							temp = fid_tFileInputDelimited_1.get(columnIndexWithD_tFileInputDelimited_1);
							if (temp.length() > 0) {

								try {

									row1.AutresOperationsDiverses = ParserUtils
											.parseTo_Integer(ParserUtils.parseTo_Number(temp, ' ', '.'));

								} catch (java.lang.Exception ex_tFileInputDelimited_1) {
									globalMap.put("tFileInputDelimited_1_ERROR_MESSAGE",
											ex_tFileInputDelimited_1.getMessage());
									rowstate_tFileInputDelimited_1.setException(new RuntimeException(String.format(
											"Couldn't parse value for column '%s' in '%s', value is '%s'. Details: %s",
											"AutresOperationsDiverses", "row1", temp, ex_tFileInputDelimited_1),
											ex_tFileInputDelimited_1));
								}

							} else {

								row1.AutresOperationsDiverses = null;

							}

							columnIndexWithD_tFileInputDelimited_1 = 68;

							temp = fid_tFileInputDelimited_1.get(columnIndexWithD_tFileInputDelimited_1);
							if (temp.length() > 0) {

								try {

									row1.Divers = ParserUtils
											.parseTo_Integer(ParserUtils.parseTo_Number(temp, ' ', '.'));

								} catch (java.lang.Exception ex_tFileInputDelimited_1) {
									globalMap.put("tFileInputDelimited_1_ERROR_MESSAGE",
											ex_tFileInputDelimited_1.getMessage());
									rowstate_tFileInputDelimited_1.setException(new RuntimeException(String.format(
											"Couldn't parse value for column '%s' in '%s', value is '%s'. Details: %s",
											"Divers", "row1", temp, ex_tFileInputDelimited_1),
											ex_tFileInputDelimited_1));
								}

							} else {

								row1.Divers = null;

							}

							columnIndexWithD_tFileInputDelimited_1 = 69;

							temp = fid_tFileInputDelimited_1.get(columnIndexWithD_tFileInputDelimited_1);
							if (temp.length() > 0) {

								try {

									row1.OperationsDiverses = ParserUtils
											.parseTo_Integer(ParserUtils.parseTo_Number(temp, ' ', '.'));

								} catch (java.lang.Exception ex_tFileInputDelimited_1) {
									globalMap.put("tFileInputDelimited_1_ERROR_MESSAGE",
											ex_tFileInputDelimited_1.getMessage());
									rowstate_tFileInputDelimited_1.setException(new RuntimeException(String.format(
											"Couldn't parse value for column '%s' in '%s', value is '%s'. Details: %s",
											"OperationsDiverses", "row1", temp, ex_tFileInputDelimited_1),
											ex_tFileInputDelimited_1));
								}

							} else {

								row1.OperationsDiverses = null;

							}

							columnIndexWithD_tFileInputDelimited_1 = 70;

							temp = fid_tFileInputDelimited_1.get(columnIndexWithD_tFileInputDelimited_1);
							if (temp.length() > 0) {

								try {

									row1.TotalInterventions = ParserUtils
											.parseTo_Integer(ParserUtils.parseTo_Number(temp, ' ', '.'));

								} catch (java.lang.Exception ex_tFileInputDelimited_1) {
									globalMap.put("tFileInputDelimited_1_ERROR_MESSAGE",
											ex_tFileInputDelimited_1.getMessage());
									rowstate_tFileInputDelimited_1.setException(new RuntimeException(String.format(
											"Couldn't parse value for column '%s' in '%s', value is '%s'. Details: %s",
											"TotalInterventions", "row1", temp, ex_tFileInputDelimited_1),
											ex_tFileInputDelimited_1));
								}

							} else {

								row1.TotalInterventions = null;

							}

							if (rowstate_tFileInputDelimited_1.getException() != null) {
								throw rowstate_tFileInputDelimited_1.getException();
							}

						} catch (java.lang.Exception e) {
							globalMap.put("tFileInputDelimited_1_ERROR_MESSAGE", e.getMessage());
							whetherReject_tFileInputDelimited_1 = true;

							System.err.println(e.getMessage());
							row1 = null;

						}

						/**
						 * [tFileInputDelimited_1 begin ] stop
						 */

						/**
						 * [tFileInputDelimited_1 main ] start
						 */

						currentComponent = "tFileInputDelimited_1";

						tos_count_tFileInputDelimited_1++;

						/**
						 * [tFileInputDelimited_1 main ] stop
						 */

						/**
						 * [tFileInputDelimited_1 process_data_begin ] start
						 */

						currentComponent = "tFileInputDelimited_1";

						/**
						 * [tFileInputDelimited_1 process_data_begin ] stop
						 */
// Start of branch "row1"
						if (row1 != null) {

							/**
							 * [tLogRow_1 main ] start
							 */

							currentComponent = "tLogRow_1";

							if (execStat) {
								runStat.updateStatOnConnection(iterateId, 1, 1

										, "row1"

								);
							}

///////////////////////		

							String[] row_tLogRow_1 = new String[71];

							if (row1.Annee != null) { //
								row_tLogRow_1[0] = String.valueOf(row1.Annee);

							} //

							if (row1.Zone != null) { //
								row_tLogRow_1[1] = String.valueOf(row1.Zone);

							} //

							if (row1.Region != null) { //
								row_tLogRow_1[2] = String.valueOf(row1.Region);

							} //

							if (row1.Numero != null) { //
								row_tLogRow_1[3] = String.valueOf(row1.Numero);

							} //

							if (row1.Departement != null) { //
								row_tLogRow_1[4] = String.valueOf(row1.Departement);

							} //

							if (row1.CategorieA != null) { //
								row_tLogRow_1[5] = String.valueOf(row1.CategorieA);

							} //

							if (row1.FeuxHabitationsBureaux != null) { //
								row_tLogRow_1[6] = String.valueOf(row1.FeuxHabitationsBureaux);

							} //

							if (row1.FeuxCheminees != null) { //
								row_tLogRow_1[7] = String.valueOf(row1.FeuxCheminees);

							} //

							if (row1.FeuxAvecLocalSommeil != null) { //
								row_tLogRow_1[8] = String.valueOf(row1.FeuxAvecLocalSommeil);

							} //

							if (row1.FeuxSansLocalSommeil != null) { //
								row_tLogRow_1[9] = String.valueOf(row1.FeuxSansLocalSommeil);

							} //

							if (row1.FeuxLocalIndustriels != null) { //
								row_tLogRow_1[10] = String.valueOf(row1.FeuxLocalIndustriels);

							} //

							if (row1.FeuxLocauxArtisanaux != null) { //
								row_tLogRow_1[11] = String.valueOf(row1.FeuxLocauxArtisanaux);

							} //

							if (row1.FeuxLocauxAgricoles != null) { //
								row_tLogRow_1[12] = String.valueOf(row1.FeuxLocauxAgricoles);

							} //

							if (row1.FeuxVoiePublique != null) { //
								row_tLogRow_1[13] = String.valueOf(row1.FeuxVoiePublique);

							} //

							if (row1.FeuxVehicules != null) { //
								row_tLogRow_1[14] = String.valueOf(row1.FeuxVehicules);

							} //

							if (row1.FeuxVegetations != null) { //
								row_tLogRow_1[15] = String.valueOf(row1.FeuxVegetations);

							} //

							if (row1.AutresFeux != null) { //
								row_tLogRow_1[16] = String.valueOf(row1.AutresFeux);

							} //

							if (row1.Incendies != null) { //
								row_tLogRow_1[17] = String.valueOf(row1.Incendies);

							} //

							if (row1.AccidentsLieuxTravail != null) { //
								row_tLogRow_1[18] = String.valueOf(row1.AccidentsLieuxTravail);

							} //

							if (row1.AccidentsDomicile != null) { //
								row_tLogRow_1[19] = String.valueOf(row1.AccidentsDomicile);

							} //

							if (row1.AccidentsSport != null) { //
								row_tLogRow_1[20] = String.valueOf(row1.AccidentsSport);

							} //

							if (row1.AccidentsVoiePublique != null) { //
								row_tLogRow_1[21] = String.valueOf(row1.AccidentsVoiePublique);

							} //

							if (row1.SecoursMontagne != null) { //
								row_tLogRow_1[22] = String.valueOf(row1.SecoursMontagne);

							} //

							if (row1.MalaisesLieuxTravail != null) { //
								row_tLogRow_1[23] = String.valueOf(row1.MalaisesLieuxTravail);

							} //

							if (row1.MalaisesDomicileVitale != null) { //
								row_tLogRow_1[24] = String.valueOf(row1.MalaisesDomicileVitale);

							} //

							if (row1.MalaisesDomicileCarence != null) { //
								row_tLogRow_1[25] = String.valueOf(row1.MalaisesDomicileCarence);

							} //

							if (row1.MalaisesSport != null) { //
								row_tLogRow_1[26] = String.valueOf(row1.MalaisesSport);

							} //

							if (row1.MalaisesVoiePublique != null) { //
								row_tLogRow_1[27] = String.valueOf(row1.MalaisesVoiePublique);

							} //

							if (row1.Autolyses != null) { //
								row_tLogRow_1[28] = String.valueOf(row1.Autolyses);

							} //

							if (row1.SecoursEauxInterieuresPiscines != null) { //
								row_tLogRow_1[29] = String.valueOf(row1.SecoursEauxInterieuresPiscines);

							} //

							if (row1.SecoursMer != null) { //
								row_tLogRow_1[30] = String.valueOf(row1.SecoursMer);

							} //

							if (row1.Intoxications != null) { //
								row_tLogRow_1[31] = String.valueOf(row1.Intoxications);

							} //

							if (row1.IntoxicationsCO != null) { //
								row_tLogRow_1[32] = String.valueOf(row1.IntoxicationsCO);

							} //

							if (row1.AutresSAV != null) { //
								row_tLogRow_1[33] = String.valueOf(row1.AutresSAV);

							} //

							if (row1.SecoursVictime != null) { //
								row_tLogRow_1[34] = String.valueOf(row1.SecoursVictime);

							} //

							if (row1.RelevagePersonnes != null) { //
								row_tLogRow_1[35] = String.valueOf(row1.RelevagePersonnes);

							} //

							if (row1.RecherchePersonnes != null) { //
								row_tLogRow_1[36] = String.valueOf(row1.RecherchePersonnes);

							} //

							if (row1.AidesPersonne != null) { //
								row_tLogRow_1[37] = String.valueOf(row1.AidesPersonne);

							} //

							if (row1.SecoursPersonne != null) { //
								row_tLogRow_1[38] = String.valueOf(row1.SecoursPersonne);

							} //

							if (row1.AccientsRoutiers != null) { //
								row_tLogRow_1[39] = String.valueOf(row1.AccientsRoutiers);

							} //

							if (row1.AccidentsFerroviaires != null) { //
								row_tLogRow_1[40] = String.valueOf(row1.AccidentsFerroviaires);

							} //

							if (row1.AccidentsAeriens != null) { //
								row_tLogRow_1[41] = String.valueOf(row1.AccidentsAeriens);

							} //

							if (row1.AccidentsNavigation != null) { //
								row_tLogRow_1[42] = String.valueOf(row1.AccidentsNavigation);

							} //

							if (row1.AccidentsTeleportage != null) { //
								row_tLogRow_1[43] = String.valueOf(row1.AccidentsTeleportage);

							} //

							if (row1.AccidentsCirculation != null) { //
								row_tLogRow_1[44] = String.valueOf(row1.AccidentsCirculation);

							} //

							if (row1.OdeursFuiteGaz != null) { //
								row_tLogRow_1[45] = String.valueOf(row1.OdeursFuiteGaz);

							} //

							if (row1.OdeursAutres != null) { //
								row_tLogRow_1[46] = String.valueOf(row1.OdeursAutres);

							} //

							if (row1.FaitDusElectricite != null) { //
								row_tLogRow_1[47] = String.valueOf(row1.FaitDusElectricite);

							} //

							if (row1.PollutionsContaminations != null) { //
								row_tLogRow_1[48] = String.valueOf(row1.PollutionsContaminations);

							} //

							if (row1.AutresRisquesTechnologiques != null) { //
								row_tLogRow_1[49] = String.valueOf(row1.AutresRisquesTechnologiques);

							} //

							if (row1.RisquesTechnologiques != null) { //
								row_tLogRow_1[50] = String.valueOf(row1.RisquesTechnologiques);

							} //

							if (row1.FuitesEau != null) { //
								row_tLogRow_1[51] = String.valueOf(row1.FuitesEau);

							} //

							if (row1.Inondations != null) { //
								row_tLogRow_1[52] = String.valueOf(row1.Inondations);

							} //

							if (row1.OuverturesPortes != null) { //
								row_tLogRow_1[53] = String.valueOf(row1.OuverturesPortes);

							} //

							if (row1.RecherchesObjets != null) { //
								row_tLogRow_1[54] = String.valueOf(row1.RecherchesObjets);

							} //

							if (row1.BruitsSuspects != null) { //
								row_tLogRow_1[55] = String.valueOf(row1.BruitsSuspects);

							} //

							if (row1.ProtectionBiens != null) { //
								row_tLogRow_1[56] = String.valueOf(row1.ProtectionBiens);

							} //

							if (row1.FaussesAlertes != null) { //
								row_tLogRow_1[57] = String.valueOf(row1.FaussesAlertes);

							} //

							if (row1.FaussesAlertesDAAF != null) { //
								row_tLogRow_1[58] = String.valueOf(row1.FaussesAlertesDAAF);

							} //

							if (row1.FaitsAnimaux != null) { //
								row_tLogRow_1[59] = String.valueOf(row1.FaitsAnimaux);

							} //

							if (row1.FaitsHymenopteres != null) { //
								row_tLogRow_1[60] = String.valueOf(row1.FaitsHymenopteres);

							} //

							if (row1.DegagementsVoiePublique != null) { //
								row_tLogRow_1[61] = String.valueOf(row1.DegagementsVoiePublique);

							} //

							if (row1.NettoyagesVoiePublique != null) { //
								row_tLogRow_1[62] = String.valueOf(row1.NettoyagesVoiePublique);

							} //

							if (row1.Eboulements != null) { //
								row_tLogRow_1[63] = String.valueOf(row1.Eboulements);

							} //

							if (row1.DeposesObjets != null) { //
								row_tLogRow_1[64] = String.valueOf(row1.DeposesObjets);

							} //

							if (row1.PiquetsSecuriteSurveillances != null) { //
								row_tLogRow_1[65] = String.valueOf(row1.PiquetsSecuriteSurveillances);

							} //

							if (row1.EnginsExplosifs != null) { //
								row_tLogRow_1[66] = String.valueOf(row1.EnginsExplosifs);

							} //

							if (row1.AutresOperationsDiverses != null) { //
								row_tLogRow_1[67] = String.valueOf(row1.AutresOperationsDiverses);

							} //

							if (row1.Divers != null) { //
								row_tLogRow_1[68] = String.valueOf(row1.Divers);

							} //

							if (row1.OperationsDiverses != null) { //
								row_tLogRow_1[69] = String.valueOf(row1.OperationsDiverses);

							} //

							if (row1.TotalInterventions != null) { //
								row_tLogRow_1[70] = String.valueOf(row1.TotalInterventions);

							} //

							util_tLogRow_1.addRow(row_tLogRow_1);
							nb_line_tLogRow_1++;
//////

//////                    

///////////////////////    			

							row2 = row1;

							tos_count_tLogRow_1++;

							/**
							 * [tLogRow_1 main ] stop
							 */

							/**
							 * [tLogRow_1 process_data_begin ] start
							 */

							currentComponent = "tLogRow_1";

							/**
							 * [tLogRow_1 process_data_begin ] stop
							 */

							/**
							 * [tMap_1 main ] start
							 */

							currentComponent = "tMap_1";

							if (execStat) {
								runStat.updateStatOnConnection(iterateId, 1, 1

										, "row2"

								);
							}

							boolean hasCasePrimitiveKeyWithNull_tMap_1 = false;

							// ###############################
							// # Input tables (lookups)
							boolean rejectedInnerJoin_tMap_1 = false;
							boolean mainRowRejected_tMap_1 = false;

							// ###############################
							{ // start of Var scope

								// ###############################
								// # Vars tables

								Var__tMap_1__Struct Var = Var__tMap_1;
								Var.Id = Numeric.sequence("Id", 1, 1);// ###############################
								// ###############################
								// # Output tables

								out1 = null;

// # Output table : 'out1'
								out1_tmp.Id = Var.Id;
								out1_tmp.Annee = row2.Annee;
								out1_tmp.Zone = row2.Zone;
								out1_tmp.Region = row2.Region;
								out1_tmp.Departement = row2.Departement;
								out1_tmp.CategorieA = row2.CategorieA;
								out1_tmp.FeuxHabitationsBureaux = row2.FeuxHabitationsBureaux;
								out1_tmp.FeuxLocalIndustriels = row2.FeuxLocalIndustriels;
								out1_tmp.FeuxLocauxArtisanaux = row2.FeuxLocauxArtisanaux;
								out1_tmp.FeuxLocauxAgricoles = row2.FeuxLocauxAgricoles;
								out1_tmp.FeuxVoiePublique = row2.FeuxVoiePublique;
								out1_tmp.FeuxVehicules = row2.FeuxVehicules;
								out1_tmp.FeuxVegetations = row2.FeuxVegetations;
								out1_tmp.AutresFeux = row2.AutresFeux;
								out1_tmp.Incendies = row2.Incendies;
								out1_tmp.AccidentsLieuxTravail = row2.AccidentsLieuxTravail;
								out1_tmp.AccidentsVoiePublique = row2.AccidentsVoiePublique;
								out1_tmp.MalaisesLieuxTravail = row2.MalaisesLieuxTravail;
								out1_tmp.MalaisesVoiePublique = row2.MalaisesVoiePublique;
								out1_tmp.Intoxications = row2.Intoxications;
								out1_tmp.IntoxicationsCO = row2.IntoxicationsCO;
								out1_tmp.SecoursVictime = row2.SecoursVictime;
								out1_tmp.RecherchePersonnes = row2.RecherchePersonnes;
								out1_tmp.AidesPersonne = row2.AidesPersonne;
								out1_tmp.SecoursPersonne = row2.SecoursPersonne;
								out1_tmp.AccientsRoutiers = row2.AccientsRoutiers;
								out1_tmp.AccidentsFerroviaires = row2.AccidentsFerroviaires;
								out1_tmp.AccidentsAeriens = row2.AccidentsAeriens;
								out1_tmp.AccidentsNavigation = row2.AccidentsNavigation;
								out1_tmp.AccidentsCirculation = row2.AccidentsCirculation;
								out1_tmp.OdeursFuiteGaz = row2.OdeursFuiteGaz;
								out1_tmp.OdeursAutres = row2.OdeursAutres;
								out1_tmp.FaitDusElectricite = row2.FaitDusElectricite;
								out1_tmp.PollutionsContaminations = row2.PollutionsContaminations;
								out1_tmp.AutresRisquesTechnologiques = row2.AutresRisquesTechnologiques;
								out1_tmp.RisquesTechnologiques = row2.RisquesTechnologiques;
								out1_tmp.FaussesAlertes = row2.FaussesAlertes;
								out1_tmp.PiquetsSecuriteSurveillances = row2.PiquetsSecuriteSurveillances;
								out1_tmp.EnginsExplosifs = row2.EnginsExplosifs;
								out1_tmp.TotalInterventions = row2.TotalInterventions;
								out1 = out1_tmp;
// ###############################

							} // end of Var scope

							rejectedInnerJoin_tMap_1 = false;

							tos_count_tMap_1++;

							/**
							 * [tMap_1 main ] stop
							 */

							/**
							 * [tMap_1 process_data_begin ] start
							 */

							currentComponent = "tMap_1";

							/**
							 * [tMap_1 process_data_begin ] stop
							 */
// Start of branch "out1"
							if (out1 != null) {

								/**
								 * [tReplicate_1 main ] start
								 */

								currentComponent = "tReplicate_1";

								if (execStat) {
									runStat.updateStatOnConnection(iterateId, 1, 1

											, "out1"

									);
								}

								row4 = new row4Struct();

								row4.Id = out1.Id;
								row4.Annee = out1.Annee;
								row4.Zone = out1.Zone;
								row4.Region = out1.Region;
								row4.Departement = out1.Departement;
								row4.CategorieA = out1.CategorieA;
								row4.FeuxHabitationsBureaux = out1.FeuxHabitationsBureaux;
								row4.FeuxLocalIndustriels = out1.FeuxLocalIndustriels;
								row4.FeuxLocauxArtisanaux = out1.FeuxLocauxArtisanaux;
								row4.FeuxLocauxAgricoles = out1.FeuxLocauxAgricoles;
								row4.FeuxVoiePublique = out1.FeuxVoiePublique;
								row4.FeuxVehicules = out1.FeuxVehicules;
								row4.FeuxVegetations = out1.FeuxVegetations;
								row4.AutresFeux = out1.AutresFeux;
								row4.Incendies = out1.Incendies;
								row4.AccidentsLieuxTravail = out1.AccidentsLieuxTravail;
								row4.AccidentsVoiePublique = out1.AccidentsVoiePublique;
								row4.MalaisesLieuxTravail = out1.MalaisesLieuxTravail;
								row4.MalaisesVoiePublique = out1.MalaisesVoiePublique;
								row4.Intoxications = out1.Intoxications;
								row4.IntoxicationsCO = out1.IntoxicationsCO;
								row4.SecoursVictime = out1.SecoursVictime;
								row4.RecherchePersonnes = out1.RecherchePersonnes;
								row4.AidesPersonne = out1.AidesPersonne;
								row4.SecoursPersonne = out1.SecoursPersonne;
								row4.AccientsRoutiers = out1.AccientsRoutiers;
								row4.AccidentsFerroviaires = out1.AccidentsFerroviaires;
								row4.AccidentsAeriens = out1.AccidentsAeriens;
								row4.AccidentsNavigation = out1.AccidentsNavigation;
								row4.AccidentsCirculation = out1.AccidentsCirculation;
								row4.OdeursFuiteGaz = out1.OdeursFuiteGaz;
								row4.OdeursAutres = out1.OdeursAutres;
								row4.FaitDusElectricite = out1.FaitDusElectricite;
								row4.PollutionsContaminations = out1.PollutionsContaminations;
								row4.AutresRisquesTechnologiques = out1.AutresRisquesTechnologiques;
								row4.RisquesTechnologiques = out1.RisquesTechnologiques;
								row4.FaussesAlertes = out1.FaussesAlertes;
								row4.PiquetsSecuriteSurveillances = out1.PiquetsSecuriteSurveillances;
								row4.EnginsExplosifs = out1.EnginsExplosifs;
								row4.TotalInterventions = out1.TotalInterventions;
								row5 = new row5Struct();

								row5.Id = out1.Id;
								row5.Annee = out1.Annee;
								row5.Zone = out1.Zone;
								row5.Region = out1.Region;
								row5.Departement = out1.Departement;
								row5.CategorieA = out1.CategorieA;
								row5.FeuxHabitationsBureaux = out1.FeuxHabitationsBureaux;
								row5.FeuxLocalIndustriels = out1.FeuxLocalIndustriels;
								row5.FeuxLocauxArtisanaux = out1.FeuxLocauxArtisanaux;
								row5.FeuxLocauxAgricoles = out1.FeuxLocauxAgricoles;
								row5.FeuxVoiePublique = out1.FeuxVoiePublique;
								row5.FeuxVehicules = out1.FeuxVehicules;
								row5.FeuxVegetations = out1.FeuxVegetations;
								row5.AutresFeux = out1.AutresFeux;
								row5.Incendies = out1.Incendies;
								row5.AccidentsLieuxTravail = out1.AccidentsLieuxTravail;
								row5.AccidentsVoiePublique = out1.AccidentsVoiePublique;
								row5.MalaisesLieuxTravail = out1.MalaisesLieuxTravail;
								row5.MalaisesVoiePublique = out1.MalaisesVoiePublique;
								row5.Intoxications = out1.Intoxications;
								row5.IntoxicationsCO = out1.IntoxicationsCO;
								row5.SecoursVictime = out1.SecoursVictime;
								row5.RecherchePersonnes = out1.RecherchePersonnes;
								row5.AidesPersonne = out1.AidesPersonne;
								row5.SecoursPersonne = out1.SecoursPersonne;
								row5.AccientsRoutiers = out1.AccientsRoutiers;
								row5.AccidentsFerroviaires = out1.AccidentsFerroviaires;
								row5.AccidentsAeriens = out1.AccidentsAeriens;
								row5.AccidentsNavigation = out1.AccidentsNavigation;
								row5.AccidentsCirculation = out1.AccidentsCirculation;
								row5.OdeursFuiteGaz = out1.OdeursFuiteGaz;
								row5.OdeursAutres = out1.OdeursAutres;
								row5.FaitDusElectricite = out1.FaitDusElectricite;
								row5.PollutionsContaminations = out1.PollutionsContaminations;
								row5.AutresRisquesTechnologiques = out1.AutresRisquesTechnologiques;
								row5.RisquesTechnologiques = out1.RisquesTechnologiques;
								row5.FaussesAlertes = out1.FaussesAlertes;
								row5.PiquetsSecuriteSurveillances = out1.PiquetsSecuriteSurveillances;
								row5.EnginsExplosifs = out1.EnginsExplosifs;
								row5.TotalInterventions = out1.TotalInterventions;

								tos_count_tReplicate_1++;

								/**
								 * [tReplicate_1 main ] stop
								 */

								/**
								 * [tReplicate_1 process_data_begin ] start
								 */

								currentComponent = "tReplicate_1";

								/**
								 * [tReplicate_1 process_data_begin ] stop
								 */

								/**
								 * [tFileOutputDelimited_1 main ] start
								 */

								currentComponent = "tFileOutputDelimited_1";

								if (execStat) {
									runStat.updateStatOnConnection(iterateId, 1, 1

											, "row4"

									);
								}

								StringBuilder sb_tFileOutputDelimited_1 = new StringBuilder();
								sb_tFileOutputDelimited_1.append(row4.Id);
								sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
								if (row4.Annee != null) {
									sb_tFileOutputDelimited_1.append(row4.Annee);
								}
								sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
								if (row4.Zone != null) {
									sb_tFileOutputDelimited_1.append(row4.Zone);
								}
								sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
								if (row4.Region != null) {
									sb_tFileOutputDelimited_1.append(row4.Region);
								}
								sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
								if (row4.Departement != null) {
									sb_tFileOutputDelimited_1.append(row4.Departement);
								}
								sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
								if (row4.CategorieA != null) {
									sb_tFileOutputDelimited_1.append(row4.CategorieA);
								}
								sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
								if (row4.FeuxHabitationsBureaux != null) {
									sb_tFileOutputDelimited_1.append(row4.FeuxHabitationsBureaux);
								}
								sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
								if (row4.FeuxLocalIndustriels != null) {
									sb_tFileOutputDelimited_1.append(row4.FeuxLocalIndustriels);
								}
								sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
								if (row4.FeuxLocauxArtisanaux != null) {
									sb_tFileOutputDelimited_1.append(row4.FeuxLocauxArtisanaux);
								}
								sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
								if (row4.FeuxLocauxAgricoles != null) {
									sb_tFileOutputDelimited_1.append(row4.FeuxLocauxAgricoles);
								}
								sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
								if (row4.FeuxVoiePublique != null) {
									sb_tFileOutputDelimited_1.append(row4.FeuxVoiePublique);
								}
								sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
								if (row4.FeuxVehicules != null) {
									sb_tFileOutputDelimited_1.append(row4.FeuxVehicules);
								}
								sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
								if (row4.FeuxVegetations != null) {
									sb_tFileOutputDelimited_1.append(row4.FeuxVegetations);
								}
								sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
								if (row4.AutresFeux != null) {
									sb_tFileOutputDelimited_1.append(row4.AutresFeux);
								}
								sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
								if (row4.Incendies != null) {
									sb_tFileOutputDelimited_1.append(row4.Incendies);
								}
								sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
								if (row4.AccidentsLieuxTravail != null) {
									sb_tFileOutputDelimited_1.append(row4.AccidentsLieuxTravail);
								}
								sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
								if (row4.AccidentsVoiePublique != null) {
									sb_tFileOutputDelimited_1.append(row4.AccidentsVoiePublique);
								}
								sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
								if (row4.MalaisesLieuxTravail != null) {
									sb_tFileOutputDelimited_1.append(row4.MalaisesLieuxTravail);
								}
								sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
								if (row4.MalaisesVoiePublique != null) {
									sb_tFileOutputDelimited_1.append(row4.MalaisesVoiePublique);
								}
								sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
								if (row4.Intoxications != null) {
									sb_tFileOutputDelimited_1.append(row4.Intoxications);
								}
								sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
								if (row4.IntoxicationsCO != null) {
									sb_tFileOutputDelimited_1.append(row4.IntoxicationsCO);
								}
								sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
								if (row4.SecoursVictime != null) {
									sb_tFileOutputDelimited_1.append(row4.SecoursVictime);
								}
								sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
								if (row4.RecherchePersonnes != null) {
									sb_tFileOutputDelimited_1.append(row4.RecherchePersonnes);
								}
								sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
								if (row4.AidesPersonne != null) {
									sb_tFileOutputDelimited_1.append(row4.AidesPersonne);
								}
								sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
								if (row4.SecoursPersonne != null) {
									sb_tFileOutputDelimited_1.append(row4.SecoursPersonne);
								}
								sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
								if (row4.AccientsRoutiers != null) {
									sb_tFileOutputDelimited_1.append(row4.AccientsRoutiers);
								}
								sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
								if (row4.AccidentsFerroviaires != null) {
									sb_tFileOutputDelimited_1.append(row4.AccidentsFerroviaires);
								}
								sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
								if (row4.AccidentsAeriens != null) {
									sb_tFileOutputDelimited_1.append(row4.AccidentsAeriens);
								}
								sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
								if (row4.AccidentsNavigation != null) {
									sb_tFileOutputDelimited_1.append(row4.AccidentsNavigation);
								}
								sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
								if (row4.AccidentsCirculation != null) {
									sb_tFileOutputDelimited_1.append(row4.AccidentsCirculation);
								}
								sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
								if (row4.OdeursFuiteGaz != null) {
									sb_tFileOutputDelimited_1.append(row4.OdeursFuiteGaz);
								}
								sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
								if (row4.OdeursAutres != null) {
									sb_tFileOutputDelimited_1.append(row4.OdeursAutres);
								}
								sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
								if (row4.FaitDusElectricite != null) {
									sb_tFileOutputDelimited_1.append(row4.FaitDusElectricite);
								}
								sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
								if (row4.PollutionsContaminations != null) {
									sb_tFileOutputDelimited_1.append(row4.PollutionsContaminations);
								}
								sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
								if (row4.AutresRisquesTechnologiques != null) {
									sb_tFileOutputDelimited_1.append(row4.AutresRisquesTechnologiques);
								}
								sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
								if (row4.RisquesTechnologiques != null) {
									sb_tFileOutputDelimited_1.append(row4.RisquesTechnologiques);
								}
								sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
								if (row4.FaussesAlertes != null) {
									sb_tFileOutputDelimited_1.append(row4.FaussesAlertes);
								}
								sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
								if (row4.PiquetsSecuriteSurveillances != null) {
									sb_tFileOutputDelimited_1.append(row4.PiquetsSecuriteSurveillances);
								}
								sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
								if (row4.EnginsExplosifs != null) {
									sb_tFileOutputDelimited_1.append(row4.EnginsExplosifs);
								}
								sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
								if (row4.TotalInterventions != null) {
									sb_tFileOutputDelimited_1.append(row4.TotalInterventions);
								}
								sb_tFileOutputDelimited_1.append(OUT_DELIM_ROWSEP_tFileOutputDelimited_1);

								nb_line_tFileOutputDelimited_1++;
								resourceMap.put("nb_line_tFileOutputDelimited_1", nb_line_tFileOutputDelimited_1);

								outtFileOutputDelimited_1.write(sb_tFileOutputDelimited_1.toString());

								tos_count_tFileOutputDelimited_1++;

								/**
								 * [tFileOutputDelimited_1 main ] stop
								 */

								/**
								 * [tFileOutputDelimited_1 process_data_begin ] start
								 */

								currentComponent = "tFileOutputDelimited_1";

								/**
								 * [tFileOutputDelimited_1 process_data_begin ] stop
								 */

								/**
								 * [tFileOutputDelimited_1 process_data_end ] start
								 */

								currentComponent = "tFileOutputDelimited_1";

								/**
								 * [tFileOutputDelimited_1 process_data_end ] stop
								 */

								/**
								 * [tDBOutput_1 main ] start
								 */

								currentComponent = "tDBOutput_1";

								if (execStat) {
									runStat.updateStatOnConnection(iterateId, 1, 1

											, "row5"

									);
								}

								whetherReject_tDBOutput_1 = false;
								pstmt_tDBOutput_1.setInt(1, row5.Id);

								int checkCount_tDBOutput_1 = -1;
								try (java.sql.ResultSet rs_tDBOutput_1 = pstmt_tDBOutput_1.executeQuery()) {
									while (rs_tDBOutput_1.next()) {
										checkCount_tDBOutput_1 = rs_tDBOutput_1.getInt(1);
									}
								}
								if (checkCount_tDBOutput_1 > 0) {
									if (row5.Annee == null) {
										pstmtUpdate_tDBOutput_1.setNull(1, java.sql.Types.INTEGER);
									} else {
										pstmtUpdate_tDBOutput_1.setInt(1, row5.Annee);
									}

									if (row5.Zone == null) {
										pstmtUpdate_tDBOutput_1.setNull(2, java.sql.Types.VARCHAR);
									} else {
										pstmtUpdate_tDBOutput_1.setString(2, row5.Zone);
									}

									if (row5.Region == null) {
										pstmtUpdate_tDBOutput_1.setNull(3, java.sql.Types.VARCHAR);
									} else {
										pstmtUpdate_tDBOutput_1.setString(3, row5.Region);
									}

									if (row5.Departement == null) {
										pstmtUpdate_tDBOutput_1.setNull(4, java.sql.Types.VARCHAR);
									} else {
										pstmtUpdate_tDBOutput_1.setString(4, row5.Departement);
									}

									if (row5.CategorieA == null) {
										pstmtUpdate_tDBOutput_1.setNull(5, java.sql.Types.VARCHAR);
									} else {
										pstmtUpdate_tDBOutput_1.setString(5, row5.CategorieA);
									}

									if (row5.FeuxHabitationsBureaux == null) {
										pstmtUpdate_tDBOutput_1.setNull(6, java.sql.Types.INTEGER);
									} else {
										pstmtUpdate_tDBOutput_1.setInt(6, row5.FeuxHabitationsBureaux);
									}

									if (row5.FeuxLocalIndustriels == null) {
										pstmtUpdate_tDBOutput_1.setNull(7, java.sql.Types.INTEGER);
									} else {
										pstmtUpdate_tDBOutput_1.setInt(7, row5.FeuxLocalIndustriels);
									}

									if (row5.FeuxLocauxArtisanaux == null) {
										pstmtUpdate_tDBOutput_1.setNull(8, java.sql.Types.INTEGER);
									} else {
										pstmtUpdate_tDBOutput_1.setInt(8, row5.FeuxLocauxArtisanaux);
									}

									if (row5.FeuxLocauxAgricoles == null) {
										pstmtUpdate_tDBOutput_1.setNull(9, java.sql.Types.INTEGER);
									} else {
										pstmtUpdate_tDBOutput_1.setInt(9, row5.FeuxLocauxAgricoles);
									}

									if (row5.FeuxVoiePublique == null) {
										pstmtUpdate_tDBOutput_1.setNull(10, java.sql.Types.INTEGER);
									} else {
										pstmtUpdate_tDBOutput_1.setInt(10, row5.FeuxVoiePublique);
									}

									if (row5.FeuxVehicules == null) {
										pstmtUpdate_tDBOutput_1.setNull(11, java.sql.Types.INTEGER);
									} else {
										pstmtUpdate_tDBOutput_1.setInt(11, row5.FeuxVehicules);
									}

									if (row5.FeuxVegetations == null) {
										pstmtUpdate_tDBOutput_1.setNull(12, java.sql.Types.INTEGER);
									} else {
										pstmtUpdate_tDBOutput_1.setInt(12, row5.FeuxVegetations);
									}

									if (row5.AutresFeux == null) {
										pstmtUpdate_tDBOutput_1.setNull(13, java.sql.Types.INTEGER);
									} else {
										pstmtUpdate_tDBOutput_1.setInt(13, row5.AutresFeux);
									}

									if (row5.Incendies == null) {
										pstmtUpdate_tDBOutput_1.setNull(14, java.sql.Types.INTEGER);
									} else {
										pstmtUpdate_tDBOutput_1.setInt(14, row5.Incendies);
									}

									if (row5.AccidentsLieuxTravail == null) {
										pstmtUpdate_tDBOutput_1.setNull(15, java.sql.Types.INTEGER);
									} else {
										pstmtUpdate_tDBOutput_1.setInt(15, row5.AccidentsLieuxTravail);
									}

									if (row5.AccidentsVoiePublique == null) {
										pstmtUpdate_tDBOutput_1.setNull(16, java.sql.Types.INTEGER);
									} else {
										pstmtUpdate_tDBOutput_1.setInt(16, row5.AccidentsVoiePublique);
									}

									if (row5.MalaisesLieuxTravail == null) {
										pstmtUpdate_tDBOutput_1.setNull(17, java.sql.Types.INTEGER);
									} else {
										pstmtUpdate_tDBOutput_1.setInt(17, row5.MalaisesLieuxTravail);
									}

									if (row5.MalaisesVoiePublique == null) {
										pstmtUpdate_tDBOutput_1.setNull(18, java.sql.Types.INTEGER);
									} else {
										pstmtUpdate_tDBOutput_1.setInt(18, row5.MalaisesVoiePublique);
									}

									if (row5.Intoxications == null) {
										pstmtUpdate_tDBOutput_1.setNull(19, java.sql.Types.INTEGER);
									} else {
										pstmtUpdate_tDBOutput_1.setInt(19, row5.Intoxications);
									}

									if (row5.IntoxicationsCO == null) {
										pstmtUpdate_tDBOutput_1.setNull(20, java.sql.Types.INTEGER);
									} else {
										pstmtUpdate_tDBOutput_1.setInt(20, row5.IntoxicationsCO);
									}

									if (row5.SecoursVictime == null) {
										pstmtUpdate_tDBOutput_1.setNull(21, java.sql.Types.INTEGER);
									} else {
										pstmtUpdate_tDBOutput_1.setInt(21, row5.SecoursVictime);
									}

									if (row5.RecherchePersonnes == null) {
										pstmtUpdate_tDBOutput_1.setNull(22, java.sql.Types.INTEGER);
									} else {
										pstmtUpdate_tDBOutput_1.setInt(22, row5.RecherchePersonnes);
									}

									if (row5.AidesPersonne == null) {
										pstmtUpdate_tDBOutput_1.setNull(23, java.sql.Types.INTEGER);
									} else {
										pstmtUpdate_tDBOutput_1.setInt(23, row5.AidesPersonne);
									}

									if (row5.SecoursPersonne == null) {
										pstmtUpdate_tDBOutput_1.setNull(24, java.sql.Types.INTEGER);
									} else {
										pstmtUpdate_tDBOutput_1.setInt(24, row5.SecoursPersonne);
									}

									if (row5.AccientsRoutiers == null) {
										pstmtUpdate_tDBOutput_1.setNull(25, java.sql.Types.INTEGER);
									} else {
										pstmtUpdate_tDBOutput_1.setInt(25, row5.AccientsRoutiers);
									}

									if (row5.AccidentsFerroviaires == null) {
										pstmtUpdate_tDBOutput_1.setNull(26, java.sql.Types.INTEGER);
									} else {
										pstmtUpdate_tDBOutput_1.setInt(26, row5.AccidentsFerroviaires);
									}

									if (row5.AccidentsAeriens == null) {
										pstmtUpdate_tDBOutput_1.setNull(27, java.sql.Types.INTEGER);
									} else {
										pstmtUpdate_tDBOutput_1.setInt(27, row5.AccidentsAeriens);
									}

									if (row5.AccidentsNavigation == null) {
										pstmtUpdate_tDBOutput_1.setNull(28, java.sql.Types.INTEGER);
									} else {
										pstmtUpdate_tDBOutput_1.setInt(28, row5.AccidentsNavigation);
									}

									if (row5.AccidentsCirculation == null) {
										pstmtUpdate_tDBOutput_1.setNull(29, java.sql.Types.INTEGER);
									} else {
										pstmtUpdate_tDBOutput_1.setInt(29, row5.AccidentsCirculation);
									}

									if (row5.OdeursFuiteGaz == null) {
										pstmtUpdate_tDBOutput_1.setNull(30, java.sql.Types.INTEGER);
									} else {
										pstmtUpdate_tDBOutput_1.setInt(30, row5.OdeursFuiteGaz);
									}

									if (row5.OdeursAutres == null) {
										pstmtUpdate_tDBOutput_1.setNull(31, java.sql.Types.INTEGER);
									} else {
										pstmtUpdate_tDBOutput_1.setInt(31, row5.OdeursAutres);
									}

									if (row5.FaitDusElectricite == null) {
										pstmtUpdate_tDBOutput_1.setNull(32, java.sql.Types.INTEGER);
									} else {
										pstmtUpdate_tDBOutput_1.setInt(32, row5.FaitDusElectricite);
									}

									if (row5.PollutionsContaminations == null) {
										pstmtUpdate_tDBOutput_1.setNull(33, java.sql.Types.INTEGER);
									} else {
										pstmtUpdate_tDBOutput_1.setInt(33, row5.PollutionsContaminations);
									}

									if (row5.AutresRisquesTechnologiques == null) {
										pstmtUpdate_tDBOutput_1.setNull(34, java.sql.Types.INTEGER);
									} else {
										pstmtUpdate_tDBOutput_1.setInt(34, row5.AutresRisquesTechnologiques);
									}

									if (row5.RisquesTechnologiques == null) {
										pstmtUpdate_tDBOutput_1.setNull(35, java.sql.Types.INTEGER);
									} else {
										pstmtUpdate_tDBOutput_1.setInt(35, row5.RisquesTechnologiques);
									}

									if (row5.FaussesAlertes == null) {
										pstmtUpdate_tDBOutput_1.setNull(36, java.sql.Types.INTEGER);
									} else {
										pstmtUpdate_tDBOutput_1.setInt(36, row5.FaussesAlertes);
									}

									if (row5.PiquetsSecuriteSurveillances == null) {
										pstmtUpdate_tDBOutput_1.setNull(37, java.sql.Types.INTEGER);
									} else {
										pstmtUpdate_tDBOutput_1.setInt(37, row5.PiquetsSecuriteSurveillances);
									}

									if (row5.EnginsExplosifs == null) {
										pstmtUpdate_tDBOutput_1.setNull(38, java.sql.Types.INTEGER);
									} else {
										pstmtUpdate_tDBOutput_1.setInt(38, row5.EnginsExplosifs);
									}

									if (row5.TotalInterventions == null) {
										pstmtUpdate_tDBOutput_1.setNull(39, java.sql.Types.INTEGER);
									} else {
										pstmtUpdate_tDBOutput_1.setInt(39, row5.TotalInterventions);
									}

									pstmtUpdate_tDBOutput_1.setInt(40 + count_tDBOutput_1, row5.Id);

									try {
										int processedCount_tDBOutput_1 = pstmtUpdate_tDBOutput_1.executeUpdate();
										updatedCount_tDBOutput_1 += processedCount_tDBOutput_1;
										rowsToCommitCount_tDBOutput_1 += processedCount_tDBOutput_1;
										nb_line_tDBOutput_1++;
									} catch (java.lang.Exception e) {
										globalMap.put("tDBOutput_1_ERROR_MESSAGE", e.getMessage());
										whetherReject_tDBOutput_1 = true;
										nb_line_tDBOutput_1++;
										System.err.print(e.getMessage());
									}
								} else {
									pstmtInsert_tDBOutput_1.setInt(1, row5.Id);

									if (row5.Annee == null) {
										pstmtInsert_tDBOutput_1.setNull(2, java.sql.Types.INTEGER);
									} else {
										pstmtInsert_tDBOutput_1.setInt(2, row5.Annee);
									}

									if (row5.Zone == null) {
										pstmtInsert_tDBOutput_1.setNull(3, java.sql.Types.VARCHAR);
									} else {
										pstmtInsert_tDBOutput_1.setString(3, row5.Zone);
									}

									if (row5.Region == null) {
										pstmtInsert_tDBOutput_1.setNull(4, java.sql.Types.VARCHAR);
									} else {
										pstmtInsert_tDBOutput_1.setString(4, row5.Region);
									}

									if (row5.Departement == null) {
										pstmtInsert_tDBOutput_1.setNull(5, java.sql.Types.VARCHAR);
									} else {
										pstmtInsert_tDBOutput_1.setString(5, row5.Departement);
									}

									if (row5.CategorieA == null) {
										pstmtInsert_tDBOutput_1.setNull(6, java.sql.Types.VARCHAR);
									} else {
										pstmtInsert_tDBOutput_1.setString(6, row5.CategorieA);
									}

									if (row5.FeuxHabitationsBureaux == null) {
										pstmtInsert_tDBOutput_1.setNull(7, java.sql.Types.INTEGER);
									} else {
										pstmtInsert_tDBOutput_1.setInt(7, row5.FeuxHabitationsBureaux);
									}

									if (row5.FeuxLocalIndustriels == null) {
										pstmtInsert_tDBOutput_1.setNull(8, java.sql.Types.INTEGER);
									} else {
										pstmtInsert_tDBOutput_1.setInt(8, row5.FeuxLocalIndustriels);
									}

									if (row5.FeuxLocauxArtisanaux == null) {
										pstmtInsert_tDBOutput_1.setNull(9, java.sql.Types.INTEGER);
									} else {
										pstmtInsert_tDBOutput_1.setInt(9, row5.FeuxLocauxArtisanaux);
									}

									if (row5.FeuxLocauxAgricoles == null) {
										pstmtInsert_tDBOutput_1.setNull(10, java.sql.Types.INTEGER);
									} else {
										pstmtInsert_tDBOutput_1.setInt(10, row5.FeuxLocauxAgricoles);
									}

									if (row5.FeuxVoiePublique == null) {
										pstmtInsert_tDBOutput_1.setNull(11, java.sql.Types.INTEGER);
									} else {
										pstmtInsert_tDBOutput_1.setInt(11, row5.FeuxVoiePublique);
									}

									if (row5.FeuxVehicules == null) {
										pstmtInsert_tDBOutput_1.setNull(12, java.sql.Types.INTEGER);
									} else {
										pstmtInsert_tDBOutput_1.setInt(12, row5.FeuxVehicules);
									}

									if (row5.FeuxVegetations == null) {
										pstmtInsert_tDBOutput_1.setNull(13, java.sql.Types.INTEGER);
									} else {
										pstmtInsert_tDBOutput_1.setInt(13, row5.FeuxVegetations);
									}

									if (row5.AutresFeux == null) {
										pstmtInsert_tDBOutput_1.setNull(14, java.sql.Types.INTEGER);
									} else {
										pstmtInsert_tDBOutput_1.setInt(14, row5.AutresFeux);
									}

									if (row5.Incendies == null) {
										pstmtInsert_tDBOutput_1.setNull(15, java.sql.Types.INTEGER);
									} else {
										pstmtInsert_tDBOutput_1.setInt(15, row5.Incendies);
									}

									if (row5.AccidentsLieuxTravail == null) {
										pstmtInsert_tDBOutput_1.setNull(16, java.sql.Types.INTEGER);
									} else {
										pstmtInsert_tDBOutput_1.setInt(16, row5.AccidentsLieuxTravail);
									}

									if (row5.AccidentsVoiePublique == null) {
										pstmtInsert_tDBOutput_1.setNull(17, java.sql.Types.INTEGER);
									} else {
										pstmtInsert_tDBOutput_1.setInt(17, row5.AccidentsVoiePublique);
									}

									if (row5.MalaisesLieuxTravail == null) {
										pstmtInsert_tDBOutput_1.setNull(18, java.sql.Types.INTEGER);
									} else {
										pstmtInsert_tDBOutput_1.setInt(18, row5.MalaisesLieuxTravail);
									}

									if (row5.MalaisesVoiePublique == null) {
										pstmtInsert_tDBOutput_1.setNull(19, java.sql.Types.INTEGER);
									} else {
										pstmtInsert_tDBOutput_1.setInt(19, row5.MalaisesVoiePublique);
									}

									if (row5.Intoxications == null) {
										pstmtInsert_tDBOutput_1.setNull(20, java.sql.Types.INTEGER);
									} else {
										pstmtInsert_tDBOutput_1.setInt(20, row5.Intoxications);
									}

									if (row5.IntoxicationsCO == null) {
										pstmtInsert_tDBOutput_1.setNull(21, java.sql.Types.INTEGER);
									} else {
										pstmtInsert_tDBOutput_1.setInt(21, row5.IntoxicationsCO);
									}

									if (row5.SecoursVictime == null) {
										pstmtInsert_tDBOutput_1.setNull(22, java.sql.Types.INTEGER);
									} else {
										pstmtInsert_tDBOutput_1.setInt(22, row5.SecoursVictime);
									}

									if (row5.RecherchePersonnes == null) {
										pstmtInsert_tDBOutput_1.setNull(23, java.sql.Types.INTEGER);
									} else {
										pstmtInsert_tDBOutput_1.setInt(23, row5.RecherchePersonnes);
									}

									if (row5.AidesPersonne == null) {
										pstmtInsert_tDBOutput_1.setNull(24, java.sql.Types.INTEGER);
									} else {
										pstmtInsert_tDBOutput_1.setInt(24, row5.AidesPersonne);
									}

									if (row5.SecoursPersonne == null) {
										pstmtInsert_tDBOutput_1.setNull(25, java.sql.Types.INTEGER);
									} else {
										pstmtInsert_tDBOutput_1.setInt(25, row5.SecoursPersonne);
									}

									if (row5.AccientsRoutiers == null) {
										pstmtInsert_tDBOutput_1.setNull(26, java.sql.Types.INTEGER);
									} else {
										pstmtInsert_tDBOutput_1.setInt(26, row5.AccientsRoutiers);
									}

									if (row5.AccidentsFerroviaires == null) {
										pstmtInsert_tDBOutput_1.setNull(27, java.sql.Types.INTEGER);
									} else {
										pstmtInsert_tDBOutput_1.setInt(27, row5.AccidentsFerroviaires);
									}

									if (row5.AccidentsAeriens == null) {
										pstmtInsert_tDBOutput_1.setNull(28, java.sql.Types.INTEGER);
									} else {
										pstmtInsert_tDBOutput_1.setInt(28, row5.AccidentsAeriens);
									}

									if (row5.AccidentsNavigation == null) {
										pstmtInsert_tDBOutput_1.setNull(29, java.sql.Types.INTEGER);
									} else {
										pstmtInsert_tDBOutput_1.setInt(29, row5.AccidentsNavigation);
									}

									if (row5.AccidentsCirculation == null) {
										pstmtInsert_tDBOutput_1.setNull(30, java.sql.Types.INTEGER);
									} else {
										pstmtInsert_tDBOutput_1.setInt(30, row5.AccidentsCirculation);
									}

									if (row5.OdeursFuiteGaz == null) {
										pstmtInsert_tDBOutput_1.setNull(31, java.sql.Types.INTEGER);
									} else {
										pstmtInsert_tDBOutput_1.setInt(31, row5.OdeursFuiteGaz);
									}

									if (row5.OdeursAutres == null) {
										pstmtInsert_tDBOutput_1.setNull(32, java.sql.Types.INTEGER);
									} else {
										pstmtInsert_tDBOutput_1.setInt(32, row5.OdeursAutres);
									}

									if (row5.FaitDusElectricite == null) {
										pstmtInsert_tDBOutput_1.setNull(33, java.sql.Types.INTEGER);
									} else {
										pstmtInsert_tDBOutput_1.setInt(33, row5.FaitDusElectricite);
									}

									if (row5.PollutionsContaminations == null) {
										pstmtInsert_tDBOutput_1.setNull(34, java.sql.Types.INTEGER);
									} else {
										pstmtInsert_tDBOutput_1.setInt(34, row5.PollutionsContaminations);
									}

									if (row5.AutresRisquesTechnologiques == null) {
										pstmtInsert_tDBOutput_1.setNull(35, java.sql.Types.INTEGER);
									} else {
										pstmtInsert_tDBOutput_1.setInt(35, row5.AutresRisquesTechnologiques);
									}

									if (row5.RisquesTechnologiques == null) {
										pstmtInsert_tDBOutput_1.setNull(36, java.sql.Types.INTEGER);
									} else {
										pstmtInsert_tDBOutput_1.setInt(36, row5.RisquesTechnologiques);
									}

									if (row5.FaussesAlertes == null) {
										pstmtInsert_tDBOutput_1.setNull(37, java.sql.Types.INTEGER);
									} else {
										pstmtInsert_tDBOutput_1.setInt(37, row5.FaussesAlertes);
									}

									if (row5.PiquetsSecuriteSurveillances == null) {
										pstmtInsert_tDBOutput_1.setNull(38, java.sql.Types.INTEGER);
									} else {
										pstmtInsert_tDBOutput_1.setInt(38, row5.PiquetsSecuriteSurveillances);
									}

									if (row5.EnginsExplosifs == null) {
										pstmtInsert_tDBOutput_1.setNull(39, java.sql.Types.INTEGER);
									} else {
										pstmtInsert_tDBOutput_1.setInt(39, row5.EnginsExplosifs);
									}

									if (row5.TotalInterventions == null) {
										pstmtInsert_tDBOutput_1.setNull(40, java.sql.Types.INTEGER);
									} else {
										pstmtInsert_tDBOutput_1.setInt(40, row5.TotalInterventions);
									}

									try {
										int processedCount_tDBOutput_1 = pstmtInsert_tDBOutput_1.executeUpdate();
										insertedCount_tDBOutput_1 += processedCount_tDBOutput_1;
										rowsToCommitCount_tDBOutput_1 += processedCount_tDBOutput_1;
										nb_line_tDBOutput_1++;
									} catch (java.lang.Exception e) {
										globalMap.put("tDBOutput_1_ERROR_MESSAGE", e.getMessage());
										whetherReject_tDBOutput_1 = true;
										nb_line_tDBOutput_1++;
										System.err.print(e.getMessage());
									}
								}
								commitCounter_tDBOutput_1++;

								if (commitEvery_tDBOutput_1 <= commitCounter_tDBOutput_1) {

									if (rowsToCommitCount_tDBOutput_1 != 0) {
									}
									conn_tDBOutput_1.commit();
									if (rowsToCommitCount_tDBOutput_1 != 0) {
										rowsToCommitCount_tDBOutput_1 = 0;
									}
									commitCounter_tDBOutput_1 = 0;

								}

								tos_count_tDBOutput_1++;

								/**
								 * [tDBOutput_1 main ] stop
								 */

								/**
								 * [tDBOutput_1 process_data_begin ] start
								 */

								currentComponent = "tDBOutput_1";

								/**
								 * [tDBOutput_1 process_data_begin ] stop
								 */

								/**
								 * [tDBOutput_1 process_data_end ] start
								 */

								currentComponent = "tDBOutput_1";

								/**
								 * [tDBOutput_1 process_data_end ] stop
								 */

								/**
								 * [tReplicate_1 process_data_end ] start
								 */

								currentComponent = "tReplicate_1";

								/**
								 * [tReplicate_1 process_data_end ] stop
								 */

							} // End of branch "out1"

							/**
							 * [tMap_1 process_data_end ] start
							 */

							currentComponent = "tMap_1";

							/**
							 * [tMap_1 process_data_end ] stop
							 */

							/**
							 * [tLogRow_1 process_data_end ] start
							 */

							currentComponent = "tLogRow_1";

							/**
							 * [tLogRow_1 process_data_end ] stop
							 */

						} // End of branch "row1"

						/**
						 * [tFileInputDelimited_1 process_data_end ] start
						 */

						currentComponent = "tFileInputDelimited_1";

						/**
						 * [tFileInputDelimited_1 process_data_end ] stop
						 */

						/**
						 * [tFileInputDelimited_1 end ] start
						 */

						currentComponent = "tFileInputDelimited_1";

					}
				} finally {
					if (!((Object) ("C:/Program Files (x86)/TOS_DI-8.0.1/studio/workspace/DATAMSPR/_input/interventions2021.csv") instanceof java.io.InputStream)) {
						if (fid_tFileInputDelimited_1 != null) {
							fid_tFileInputDelimited_1.close();
						}
					}
					if (fid_tFileInputDelimited_1 != null) {
						globalMap.put("tFileInputDelimited_1_NB_LINE", fid_tFileInputDelimited_1.getRowNumber());

					}
				}

				ok_Hash.put("tFileInputDelimited_1", true);
				end_Hash.put("tFileInputDelimited_1", System.currentTimeMillis());

				/**
				 * [tFileInputDelimited_1 end ] stop
				 */

				/**
				 * [tLogRow_1 end ] start
				 */

				currentComponent = "tLogRow_1";

//////

				java.io.PrintStream consoleOut_tLogRow_1 = null;
				if (globalMap.get("tLogRow_CONSOLE") != null) {
					consoleOut_tLogRow_1 = (java.io.PrintStream) globalMap.get("tLogRow_CONSOLE");
				} else {
					consoleOut_tLogRow_1 = new java.io.PrintStream(new java.io.BufferedOutputStream(System.out));
					globalMap.put("tLogRow_CONSOLE", consoleOut_tLogRow_1);
				}

				consoleOut_tLogRow_1.println(util_tLogRow_1.format().toString());
				consoleOut_tLogRow_1.flush();
//////
				globalMap.put("tLogRow_1_NB_LINE", nb_line_tLogRow_1);

///////////////////////    			

				if (execStat) {
					runStat.updateStat(resourceMap, iterateId, 2, 0, "row1");
				}

				ok_Hash.put("tLogRow_1", true);
				end_Hash.put("tLogRow_1", System.currentTimeMillis());

				/**
				 * [tLogRow_1 end ] stop
				 */

				/**
				 * [tMap_1 end ] start
				 */

				currentComponent = "tMap_1";

// ###############################
// # Lookup hashes releasing
// ###############################      

				if (execStat) {
					runStat.updateStat(resourceMap, iterateId, 2, 0, "row2");
				}

				ok_Hash.put("tMap_1", true);
				end_Hash.put("tMap_1", System.currentTimeMillis());

				/**
				 * [tMap_1 end ] stop
				 */

				/**
				 * [tReplicate_1 end ] start
				 */

				currentComponent = "tReplicate_1";

				if (execStat) {
					runStat.updateStat(resourceMap, iterateId, 2, 0, "out1");
				}

				ok_Hash.put("tReplicate_1", true);
				end_Hash.put("tReplicate_1", System.currentTimeMillis());

				/**
				 * [tReplicate_1 end ] stop
				 */

				/**
				 * [tFileOutputDelimited_1 end ] start
				 */

				currentComponent = "tFileOutputDelimited_1";

				if (outtFileOutputDelimited_1 != null) {
					outtFileOutputDelimited_1.flush();
					outtFileOutputDelimited_1.close();
				}

				globalMap.put("tFileOutputDelimited_1_NB_LINE", nb_line_tFileOutputDelimited_1);
				globalMap.put("tFileOutputDelimited_1_FILE_NAME", fileName_tFileOutputDelimited_1);

				resourceMap.put("finish_tFileOutputDelimited_1", true);

				if (execStat) {
					runStat.updateStat(resourceMap, iterateId, 2, 0, "row4");
				}

				ok_Hash.put("tFileOutputDelimited_1", true);
				end_Hash.put("tFileOutputDelimited_1", System.currentTimeMillis());

				/**
				 * [tFileOutputDelimited_1 end ] stop
				 */

				/**
				 * [tDBOutput_1 end ] start
				 */

				currentComponent = "tDBOutput_1";

				if (pstmtUpdate_tDBOutput_1 != null) {
					pstmtUpdate_tDBOutput_1.close();
					resourceMap.remove("pstmtUpdate_tDBOutput_1");
				}
				if (pstmtInsert_tDBOutput_1 != null) {
					pstmtInsert_tDBOutput_1.close();
					resourceMap.remove("pstmtInsert_tDBOutput_1");
				}
				if (pstmt_tDBOutput_1 != null) {
					pstmt_tDBOutput_1.close();
					resourceMap.remove("pstmt_tDBOutput_1");
				}
				resourceMap.put("statementClosed_tDBOutput_1", true);
				if (commitCounter_tDBOutput_1 > 0 && rowsToCommitCount_tDBOutput_1 != 0) {

				}
				conn_tDBOutput_1.commit();
				if (commitCounter_tDBOutput_1 > 0 && rowsToCommitCount_tDBOutput_1 != 0) {

					rowsToCommitCount_tDBOutput_1 = 0;
				}
				commitCounter_tDBOutput_1 = 0;

				conn_tDBOutput_1.close();

				resourceMap.put("finish_tDBOutput_1", true);

				nb_line_deleted_tDBOutput_1 = nb_line_deleted_tDBOutput_1 + deletedCount_tDBOutput_1;
				nb_line_update_tDBOutput_1 = nb_line_update_tDBOutput_1 + updatedCount_tDBOutput_1;
				nb_line_inserted_tDBOutput_1 = nb_line_inserted_tDBOutput_1 + insertedCount_tDBOutput_1;
				nb_line_rejected_tDBOutput_1 = nb_line_rejected_tDBOutput_1 + rejectedCount_tDBOutput_1;

				globalMap.put("tDBOutput_1_NB_LINE", nb_line_tDBOutput_1);
				globalMap.put("tDBOutput_1_NB_LINE_UPDATED", nb_line_update_tDBOutput_1);
				globalMap.put("tDBOutput_1_NB_LINE_INSERTED", nb_line_inserted_tDBOutput_1);
				globalMap.put("tDBOutput_1_NB_LINE_DELETED", nb_line_deleted_tDBOutput_1);
				globalMap.put("tDBOutput_1_NB_LINE_REJECTED", nb_line_rejected_tDBOutput_1);

				if (execStat) {
					runStat.updateStat(resourceMap, iterateId, 2, 0, "row5");
				}

				ok_Hash.put("tDBOutput_1", true);
				end_Hash.put("tDBOutput_1", System.currentTimeMillis());

				/**
				 * [tDBOutput_1 end ] stop
				 */

			} // end the resume

		} catch (java.lang.Exception e) {

			TalendException te = new TalendException(e, currentComponent, globalMap);

			throw te;
		} catch (java.lang.Error error) {

			runStat.stopThreadStat();

			throw error;
		} finally {

			try {

				/**
				 * [tFileInputDelimited_1 finally ] start
				 */

				currentComponent = "tFileInputDelimited_1";

				/**
				 * [tFileInputDelimited_1 finally ] stop
				 */

				/**
				 * [tLogRow_1 finally ] start
				 */

				currentComponent = "tLogRow_1";

				/**
				 * [tLogRow_1 finally ] stop
				 */

				/**
				 * [tMap_1 finally ] start
				 */

				currentComponent = "tMap_1";

				/**
				 * [tMap_1 finally ] stop
				 */

				/**
				 * [tReplicate_1 finally ] start
				 */

				currentComponent = "tReplicate_1";

				/**
				 * [tReplicate_1 finally ] stop
				 */

				/**
				 * [tFileOutputDelimited_1 finally ] start
				 */

				currentComponent = "tFileOutputDelimited_1";

				if (resourceMap.get("finish_tFileOutputDelimited_1") == null) {

					java.io.Writer outtFileOutputDelimited_1 = (java.io.Writer) resourceMap
							.get("out_tFileOutputDelimited_1");
					if (outtFileOutputDelimited_1 != null) {
						outtFileOutputDelimited_1.flush();
						outtFileOutputDelimited_1.close();
					}

				}

				/**
				 * [tFileOutputDelimited_1 finally ] stop
				 */

				/**
				 * [tDBOutput_1 finally ] start
				 */

				currentComponent = "tDBOutput_1";

				try {
					if (resourceMap.get("statementClosed_tDBOutput_1") == null) {
						java.sql.PreparedStatement pstmtUpdateToClose_tDBOutput_1 = null;
						if ((pstmtUpdateToClose_tDBOutput_1 = (java.sql.PreparedStatement) resourceMap
								.remove("pstmtUpdate_tDBOutput_1")) != null) {
							pstmtUpdateToClose_tDBOutput_1.close();
						}
						java.sql.PreparedStatement pstmtInsertToClose_tDBOutput_1 = null;
						if ((pstmtInsertToClose_tDBOutput_1 = (java.sql.PreparedStatement) resourceMap
								.remove("pstmtInsert_tDBOutput_1")) != null) {
							pstmtInsertToClose_tDBOutput_1.close();
						}
						java.sql.PreparedStatement pstmtToClose_tDBOutput_1 = null;
						if ((pstmtToClose_tDBOutput_1 = (java.sql.PreparedStatement) resourceMap
								.remove("pstmt_tDBOutput_1")) != null) {
							pstmtToClose_tDBOutput_1.close();
						}
					}
				} finally {
					if (resourceMap.get("finish_tDBOutput_1") == null) {
						java.sql.Connection ctn_tDBOutput_1 = null;
						if ((ctn_tDBOutput_1 = (java.sql.Connection) resourceMap.get("conn_tDBOutput_1")) != null) {
							try {
								ctn_tDBOutput_1.close();
							} catch (java.sql.SQLException sqlEx_tDBOutput_1) {
								String errorMessage_tDBOutput_1 = "failed to close the connection in tDBOutput_1 :"
										+ sqlEx_tDBOutput_1.getMessage();
								System.err.println(errorMessage_tDBOutput_1);
							}
						}
					}
				}

				/**
				 * [tDBOutput_1 finally ] stop
				 */

			} catch (java.lang.Exception e) {
				// ignore
			} catch (java.lang.Error error) {
				// ignore
			}
			resourceMap = null;
		}

		globalMap.put("tFileInputDelimited_1_SUBPROCESS_STATE", 1);
	}

	public String resuming_logs_dir_path = null;
	public String resuming_checkpoint_path = null;
	public String parent_part_launcher = null;
	private String resumeEntryMethodName = null;
	private boolean globalResumeTicket = false;

	public boolean watch = false;
	// portStats is null, it means don't execute the statistics
	public Integer portStats = null;
	public int portTraces = 4334;
	public String clientHost;
	public String defaultClientHost = "localhost";
	public String contextStr = "Default";
	public boolean isDefaultContext = true;
	public String pid = "0";
	public String rootPid = null;
	public String fatherPid = null;
	public String fatherNode = null;
	public long startTime = 0;
	public boolean isChildJob = false;
	public String log4jLevel = "";

	private boolean enableLogStash;

	private boolean execStat = true;

	private ThreadLocal<java.util.Map<String, String>> threadLocal = new ThreadLocal<java.util.Map<String, String>>() {
		protected java.util.Map<String, String> initialValue() {
			java.util.Map<String, String> threadRunResultMap = new java.util.HashMap<String, String>();
			threadRunResultMap.put("errorCode", null);
			threadRunResultMap.put("status", "");
			return threadRunResultMap;
		};
	};

	protected PropertiesWithType context_param = new PropertiesWithType();
	public java.util.Map<String, Object> parentContextMap = new java.util.HashMap<String, Object>();

	public String status = "";

	public static void main(String[] args) {
		final Intervention InterventionClass = new Intervention();

		int exitCode = InterventionClass.runJobInTOS(args);

		System.exit(exitCode);
	}

	public String[][] runJob(String[] args) {

		int exitCode = runJobInTOS(args);
		String[][] bufferValue = new String[][] { { Integer.toString(exitCode) } };

		return bufferValue;
	}

	public boolean hastBufferOutputComponent() {
		boolean hastBufferOutput = false;

		return hastBufferOutput;
	}

	public int runJobInTOS(String[] args) {
		// reset status
		status = "";

		String lastStr = "";
		for (String arg : args) {
			if (arg.equalsIgnoreCase("--context_param")) {
				lastStr = arg;
			} else if (lastStr.equals("")) {
				evalParam(arg);
			} else {
				evalParam(lastStr + " " + arg);
				lastStr = "";
			}
		}
		enableLogStash = "true".equalsIgnoreCase(System.getProperty("audit.enabled"));

		if (clientHost == null) {
			clientHost = defaultClientHost;
		}

		if (pid == null || "0".equals(pid)) {
			pid = TalendString.getAsciiRandomString(6);
		}

		if (rootPid == null) {
			rootPid = pid;
		}
		if (fatherPid == null) {
			fatherPid = pid;
		} else {
			isChildJob = true;
		}

		if (portStats != null) {
			// portStats = -1; //for testing
			if (portStats < 0 || portStats > 65535) {
				// issue:10869, the portStats is invalid, so this client socket can't open
				System.err.println("The statistics socket port " + portStats + " is invalid.");
				execStat = false;
			}
		} else {
			execStat = false;
		}
		boolean inOSGi = routines.system.BundleUtils.inOSGi();

		if (inOSGi) {
			java.util.Dictionary<String, Object> jobProperties = routines.system.BundleUtils.getJobProperties(jobName);

			if (jobProperties != null && jobProperties.get("context") != null) {
				contextStr = (String) jobProperties.get("context");
			}
		}

		try {
			// call job/subjob with an existing context, like: --context=production. if
			// without this parameter, there will use the default context instead.
			java.io.InputStream inContext = Intervention.class.getClassLoader()
					.getResourceAsStream("datamspr/intervention_0_1/contexts/" + contextStr + ".properties");
			if (inContext == null) {
				inContext = Intervention.class.getClassLoader()
						.getResourceAsStream("config/contexts/" + contextStr + ".properties");
			}
			if (inContext != null) {
				try {
					// defaultProps is in order to keep the original context value
					if (context != null && context.isEmpty()) {
						defaultProps.load(inContext);
						context = new ContextProperties(defaultProps);
					}
				} finally {
					inContext.close();
				}
			} else if (!isDefaultContext) {
				// print info and job continue to run, for case: context_param is not empty.
				System.err.println("Could not find the context " + contextStr);
			}

			if (!context_param.isEmpty()) {
				context.putAll(context_param);
				// set types for params from parentJobs
				for (Object key : context_param.keySet()) {
					String context_key = key.toString();
					String context_type = context_param.getContextType(context_key);
					context.setContextType(context_key, context_type);

				}
			}
			class ContextProcessing {
				private void processContext_0() {
				}

				public void processAllContext() {
					processContext_0();
				}
			}

			new ContextProcessing().processAllContext();
		} catch (java.io.IOException ie) {
			System.err.println("Could not load context " + contextStr);
			ie.printStackTrace();
		}

		// get context value from parent directly
		if (parentContextMap != null && !parentContextMap.isEmpty()) {
		}

		// Resume: init the resumeUtil
		resumeEntryMethodName = ResumeUtil.getResumeEntryMethodName(resuming_checkpoint_path);
		resumeUtil = new ResumeUtil(resuming_logs_dir_path, isChildJob, rootPid);
		resumeUtil.initCommonInfo(pid, rootPid, fatherPid, projectName, jobName, contextStr, jobVersion);

		List<String> parametersToEncrypt = new java.util.ArrayList<String>();
		// Resume: jobStart
		resumeUtil.addLog("JOB_STARTED", "JOB:" + jobName, parent_part_launcher, Thread.currentThread().getId() + "",
				"", "", "", "", resumeUtil.convertToJsonText(context, parametersToEncrypt));

		if (execStat) {
			try {
				runStat.openSocket(!isChildJob);
				runStat.setAllPID(rootPid, fatherPid, pid, jobName);
				runStat.startThreadStat(clientHost, portStats);
				runStat.updateStatOnJob(RunStat.JOBSTART, fatherNode);
			} catch (java.io.IOException ioException) {
				ioException.printStackTrace();
			}
		}

		java.util.concurrent.ConcurrentHashMap<Object, Object> concurrentHashMap = new java.util.concurrent.ConcurrentHashMap<Object, Object>();
		globalMap.put("concurrentHashMap", concurrentHashMap);

		long startUsedMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
		long endUsedMemory = 0;
		long end = 0;

		startTime = System.currentTimeMillis();

		this.globalResumeTicket = true;// to run tPreJob

		this.globalResumeTicket = false;// to run others jobs

		try {
			errorCode = null;
			tFileInputDelimited_1Process(globalMap);
			if (!"failure".equals(status)) {
				status = "end";
			}
		} catch (TalendException e_tFileInputDelimited_1) {
			globalMap.put("tFileInputDelimited_1_SUBPROCESS_STATE", -1);

			e_tFileInputDelimited_1.printStackTrace();

		}

		this.globalResumeTicket = true;// to run tPostJob

		end = System.currentTimeMillis();

		if (watch) {
			System.out.println((end - startTime) + " milliseconds");
		}

		endUsedMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
		if (false) {
			System.out
					.println((endUsedMemory - startUsedMemory) + " bytes memory increase when running : Intervention");
		}

		if (execStat) {
			runStat.updateStatOnJob(RunStat.JOBEND, fatherNode);
			runStat.stopThreadStat();
		}
		int returnCode = 0;

		if (errorCode == null) {
			returnCode = status != null && status.equals("failure") ? 1 : 0;
		} else {
			returnCode = errorCode.intValue();
		}
		resumeUtil.addLog("JOB_ENDED", "JOB:" + jobName, parent_part_launcher, Thread.currentThread().getId() + "", "",
				"" + returnCode, "", "", "");

		return returnCode;

	}

	// only for OSGi env
	public void destroy() {

	}

	private java.util.Map<String, Object> getSharedConnections4REST() {
		java.util.Map<String, Object> connections = new java.util.HashMap<String, Object>();

		return connections;
	}

	private void evalParam(String arg) {
		if (arg.startsWith("--resuming_logs_dir_path")) {
			resuming_logs_dir_path = arg.substring(25);
		} else if (arg.startsWith("--resuming_checkpoint_path")) {
			resuming_checkpoint_path = arg.substring(27);
		} else if (arg.startsWith("--parent_part_launcher")) {
			parent_part_launcher = arg.substring(23);
		} else if (arg.startsWith("--watch")) {
			watch = true;
		} else if (arg.startsWith("--stat_port=")) {
			String portStatsStr = arg.substring(12);
			if (portStatsStr != null && !portStatsStr.equals("null")) {
				portStats = Integer.parseInt(portStatsStr);
			}
		} else if (arg.startsWith("--trace_port=")) {
			portTraces = Integer.parseInt(arg.substring(13));
		} else if (arg.startsWith("--client_host=")) {
			clientHost = arg.substring(14);
		} else if (arg.startsWith("--context=")) {
			contextStr = arg.substring(10);
			isDefaultContext = false;
		} else if (arg.startsWith("--father_pid=")) {
			fatherPid = arg.substring(13);
		} else if (arg.startsWith("--root_pid=")) {
			rootPid = arg.substring(11);
		} else if (arg.startsWith("--father_node=")) {
			fatherNode = arg.substring(14);
		} else if (arg.startsWith("--pid=")) {
			pid = arg.substring(6);
		} else if (arg.startsWith("--context_type")) {
			String keyValue = arg.substring(15);
			int index = -1;
			if (keyValue != null && (index = keyValue.indexOf('=')) > -1) {
				if (fatherPid == null) {
					context_param.setContextType(keyValue.substring(0, index),
							replaceEscapeChars(keyValue.substring(index + 1)));
				} else { // the subjob won't escape the especial chars
					context_param.setContextType(keyValue.substring(0, index), keyValue.substring(index + 1));
				}

			}

		} else if (arg.startsWith("--context_param")) {
			String keyValue = arg.substring(16);
			int index = -1;
			if (keyValue != null && (index = keyValue.indexOf('=')) > -1) {
				if (fatherPid == null) {
					context_param.put(keyValue.substring(0, index), replaceEscapeChars(keyValue.substring(index + 1)));
				} else { // the subjob won't escape the especial chars
					context_param.put(keyValue.substring(0, index), keyValue.substring(index + 1));
				}
			}
		} else if (arg.startsWith("--log4jLevel=")) {
			log4jLevel = arg.substring(13);
		} else if (arg.startsWith("--audit.enabled") && arg.contains("=")) {// for trunjob call
			final int equal = arg.indexOf('=');
			final String key = arg.substring("--".length(), equal);
			System.setProperty(key, arg.substring(equal + 1));
		}
	}

	private static final String NULL_VALUE_EXPRESSION_IN_COMMAND_STRING_FOR_CHILD_JOB_ONLY = "<TALEND_NULL>";

	private final String[][] escapeChars = { { "\\\\", "\\" }, { "\\n", "\n" }, { "\\'", "\'" }, { "\\r", "\r" },
			{ "\\f", "\f" }, { "\\b", "\b" }, { "\\t", "\t" } };

	private String replaceEscapeChars(String keyValue) {

		if (keyValue == null || ("").equals(keyValue.trim())) {
			return keyValue;
		}

		StringBuilder result = new StringBuilder();
		int currIndex = 0;
		while (currIndex < keyValue.length()) {
			int index = -1;
			// judege if the left string includes escape chars
			for (String[] strArray : escapeChars) {
				index = keyValue.indexOf(strArray[0], currIndex);
				if (index >= 0) {

					result.append(keyValue.substring(currIndex, index + strArray[0].length()).replace(strArray[0],
							strArray[1]));
					currIndex = index + strArray[0].length();
					break;
				}
			}
			// if the left string doesn't include escape chars, append the left into the
			// result
			if (index < 0) {
				result.append(keyValue.substring(currIndex));
				currIndex = currIndex + keyValue.length();
			}
		}

		return result.toString();
	}

	public Integer getErrorCode() {
		return errorCode;
	}

	public String getStatus() {
		return status;
	}

	ResumeUtil resumeUtil = null;
}
/************************************************************************************************
 * 374976 characters generated by Talend Open Studio for Data Integration on the
 * 15 avril 2024, 17:49:27 CEST
 ************************************************************************************************/