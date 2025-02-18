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

package datamspr.egalitehf_0_1;

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
 * Job: EgaliteHF Purpose: <br>
 * Description: <br>
 * 
 * @author user@talend.com
 * @version 8.0.1.20211109_1610
 * @status
 */
public class EgaliteHF implements TalendJob {

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
	private final String jobName = "EgaliteHF";
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
					EgaliteHF.this.exception = e;
				}
			}
			if (!(e instanceof TalendException)) {
				try {
					for (java.lang.reflect.Method m : this.getClass().getEnclosingClass().getMethods()) {
						if (m.getName().compareTo(currentComponent + "_error") == 0) {
							m.invoke(EgaliteHF.this, new Object[] { e, currentComponent, globalMap });
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

	public void tFileInputExcel_1_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputExcel_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tMap_1_error(Exception exception, String errorComponent, final java.util.Map<String, Object> globalMap)
			throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputExcel_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tLogRow_1_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputExcel_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tReplicate_1_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputExcel_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tFileOutputDelimited_1_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputExcel_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tDBOutput_1_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputExcel_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tFileInputExcel_1_onSubJobError(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		resumeUtil.addLog("SYSTEM_LOG", "NODE:" + errorComponent, "", Thread.currentThread().getId() + "", "FATAL", "",
				exception.getMessage(), ResumeUtil.getExceptionStackTrace(exception), "");

	}

	public static class row4Struct implements routines.system.IPersistableRow<row4Struct> {
		final static byte[] commonByteArrayLock_DATAMSPR_EgaliteHF = new byte[0];
		static byte[] commonByteArray_DATAMSPR_EgaliteHF = new byte[0];
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

		public String Structure;

		public String getStructure() {
			return this.Structure;
		}

		public String TrancheEffectif;

		public String getTrancheEffectif() {
			return this.TrancheEffectif;
		}

		public String SIREN;

		public String getSIREN() {
			return this.SIREN;
		}

		public String RaisonSociale;

		public String getRaisonSociale() {
			return this.RaisonSociale;
		}

		public String Region;

		public String getRegion() {
			return this.Region;
		}

		public String Departement;

		public String getDepartement() {
			return this.Departement;
		}

		public String Pays;

		public String getPays() {
			return this.Pays;
		}

		public String CodeNAF;

		public String getCodeNAF() {
			return this.CodeNAF;
		}

		public Integer NoteEcartRemuneration;

		public Integer getNoteEcartRemuneration() {
			return this.NoteEcartRemuneration;
		}

		public Integer NoteEcartTauxPromotion;

		public Integer getNoteEcartTauxPromotion() {
			return this.NoteEcartTauxPromotion;
		}

		public Integer NoteHautesRemunerations;

		public Integer getNoteHautesRemunerations() {
			return this.NoteHautesRemunerations;
		}

		public Integer NoteIndex;

		public Integer getNoteIndex() {
			return this.NoteIndex;
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
			other.Structure = this.Structure;
			other.TrancheEffectif = this.TrancheEffectif;
			other.SIREN = this.SIREN;
			other.RaisonSociale = this.RaisonSociale;
			other.Region = this.Region;
			other.Departement = this.Departement;
			other.Pays = this.Pays;
			other.CodeNAF = this.CodeNAF;
			other.NoteEcartRemuneration = this.NoteEcartRemuneration;
			other.NoteEcartTauxPromotion = this.NoteEcartTauxPromotion;
			other.NoteHautesRemunerations = this.NoteHautesRemunerations;
			other.NoteIndex = this.NoteIndex;

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
				if (length > commonByteArray_DATAMSPR_EgaliteHF.length) {
					if (length < 1024 && commonByteArray_DATAMSPR_EgaliteHF.length == 0) {
						commonByteArray_DATAMSPR_EgaliteHF = new byte[1024];
					} else {
						commonByteArray_DATAMSPR_EgaliteHF = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_DATAMSPR_EgaliteHF, 0, length);
				strReturn = new String(commonByteArray_DATAMSPR_EgaliteHF, 0, length, utf8Charset);
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
				if (length > commonByteArray_DATAMSPR_EgaliteHF.length) {
					if (length < 1024 && commonByteArray_DATAMSPR_EgaliteHF.length == 0) {
						commonByteArray_DATAMSPR_EgaliteHF = new byte[1024];
					} else {
						commonByteArray_DATAMSPR_EgaliteHF = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_DATAMSPR_EgaliteHF, 0, length);
				strReturn = new String(commonByteArray_DATAMSPR_EgaliteHF, 0, length, utf8Charset);
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

			synchronized (commonByteArrayLock_DATAMSPR_EgaliteHF) {

				try {

					int length = 0;

					this.Id = dis.readInt();

					this.Annee = readInteger(dis);

					this.Structure = readString(dis);

					this.TrancheEffectif = readString(dis);

					this.SIREN = readString(dis);

					this.RaisonSociale = readString(dis);

					this.Region = readString(dis);

					this.Departement = readString(dis);

					this.Pays = readString(dis);

					this.CodeNAF = readString(dis);

					this.NoteEcartRemuneration = readInteger(dis);

					this.NoteEcartTauxPromotion = readInteger(dis);

					this.NoteHautesRemunerations = readInteger(dis);

					this.NoteIndex = readInteger(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_DATAMSPR_EgaliteHF) {

				try {

					int length = 0;

					this.Id = dis.readInt();

					this.Annee = readInteger(dis);

					this.Structure = readString(dis);

					this.TrancheEffectif = readString(dis);

					this.SIREN = readString(dis);

					this.RaisonSociale = readString(dis);

					this.Region = readString(dis);

					this.Departement = readString(dis);

					this.Pays = readString(dis);

					this.CodeNAF = readString(dis);

					this.NoteEcartRemuneration = readInteger(dis);

					this.NoteEcartTauxPromotion = readInteger(dis);

					this.NoteHautesRemunerations = readInteger(dis);

					this.NoteIndex = readInteger(dis);

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

				writeString(this.Structure, dos);

				// String

				writeString(this.TrancheEffectif, dos);

				// String

				writeString(this.SIREN, dos);

				// String

				writeString(this.RaisonSociale, dos);

				// String

				writeString(this.Region, dos);

				// String

				writeString(this.Departement, dos);

				// String

				writeString(this.Pays, dos);

				// String

				writeString(this.CodeNAF, dos);

				// Integer

				writeInteger(this.NoteEcartRemuneration, dos);

				// Integer

				writeInteger(this.NoteEcartTauxPromotion, dos);

				// Integer

				writeInteger(this.NoteHautesRemunerations, dos);

				// Integer

				writeInteger(this.NoteIndex, dos);

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

				writeString(this.Structure, dos);

				// String

				writeString(this.TrancheEffectif, dos);

				// String

				writeString(this.SIREN, dos);

				// String

				writeString(this.RaisonSociale, dos);

				// String

				writeString(this.Region, dos);

				// String

				writeString(this.Departement, dos);

				// String

				writeString(this.Pays, dos);

				// String

				writeString(this.CodeNAF, dos);

				// Integer

				writeInteger(this.NoteEcartRemuneration, dos);

				// Integer

				writeInteger(this.NoteEcartTauxPromotion, dos);

				// Integer

				writeInteger(this.NoteHautesRemunerations, dos);

				// Integer

				writeInteger(this.NoteIndex, dos);

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
			sb.append(",Structure=" + Structure);
			sb.append(",TrancheEffectif=" + TrancheEffectif);
			sb.append(",SIREN=" + SIREN);
			sb.append(",RaisonSociale=" + RaisonSociale);
			sb.append(",Region=" + Region);
			sb.append(",Departement=" + Departement);
			sb.append(",Pays=" + Pays);
			sb.append(",CodeNAF=" + CodeNAF);
			sb.append(",NoteEcartRemuneration=" + String.valueOf(NoteEcartRemuneration));
			sb.append(",NoteEcartTauxPromotion=" + String.valueOf(NoteEcartTauxPromotion));
			sb.append(",NoteHautesRemunerations=" + String.valueOf(NoteHautesRemunerations));
			sb.append(",NoteIndex=" + String.valueOf(NoteIndex));
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

	public static class row3Struct implements routines.system.IPersistableRow<row3Struct> {
		final static byte[] commonByteArrayLock_DATAMSPR_EgaliteHF = new byte[0];
		static byte[] commonByteArray_DATAMSPR_EgaliteHF = new byte[0];
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

		public String Structure;

		public String getStructure() {
			return this.Structure;
		}

		public String TrancheEffectif;

		public String getTrancheEffectif() {
			return this.TrancheEffectif;
		}

		public String SIREN;

		public String getSIREN() {
			return this.SIREN;
		}

		public String RaisonSociale;

		public String getRaisonSociale() {
			return this.RaisonSociale;
		}

		public String Region;

		public String getRegion() {
			return this.Region;
		}

		public String Departement;

		public String getDepartement() {
			return this.Departement;
		}

		public String Pays;

		public String getPays() {
			return this.Pays;
		}

		public String CodeNAF;

		public String getCodeNAF() {
			return this.CodeNAF;
		}

		public Integer NoteEcartRemuneration;

		public Integer getNoteEcartRemuneration() {
			return this.NoteEcartRemuneration;
		}

		public Integer NoteEcartTauxPromotion;

		public Integer getNoteEcartTauxPromotion() {
			return this.NoteEcartTauxPromotion;
		}

		public Integer NoteHautesRemunerations;

		public Integer getNoteHautesRemunerations() {
			return this.NoteHautesRemunerations;
		}

		public Integer NoteIndex;

		public Integer getNoteIndex() {
			return this.NoteIndex;
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
			final row3Struct other = (row3Struct) obj;

			if (this.Id != other.Id)
				return false;

			return true;
		}

		public void copyDataTo(row3Struct other) {

			other.Id = this.Id;
			other.Annee = this.Annee;
			other.Structure = this.Structure;
			other.TrancheEffectif = this.TrancheEffectif;
			other.SIREN = this.SIREN;
			other.RaisonSociale = this.RaisonSociale;
			other.Region = this.Region;
			other.Departement = this.Departement;
			other.Pays = this.Pays;
			other.CodeNAF = this.CodeNAF;
			other.NoteEcartRemuneration = this.NoteEcartRemuneration;
			other.NoteEcartTauxPromotion = this.NoteEcartTauxPromotion;
			other.NoteHautesRemunerations = this.NoteHautesRemunerations;
			other.NoteIndex = this.NoteIndex;

		}

		public void copyKeysDataTo(row3Struct other) {

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
				if (length > commonByteArray_DATAMSPR_EgaliteHF.length) {
					if (length < 1024 && commonByteArray_DATAMSPR_EgaliteHF.length == 0) {
						commonByteArray_DATAMSPR_EgaliteHF = new byte[1024];
					} else {
						commonByteArray_DATAMSPR_EgaliteHF = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_DATAMSPR_EgaliteHF, 0, length);
				strReturn = new String(commonByteArray_DATAMSPR_EgaliteHF, 0, length, utf8Charset);
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
				if (length > commonByteArray_DATAMSPR_EgaliteHF.length) {
					if (length < 1024 && commonByteArray_DATAMSPR_EgaliteHF.length == 0) {
						commonByteArray_DATAMSPR_EgaliteHF = new byte[1024];
					} else {
						commonByteArray_DATAMSPR_EgaliteHF = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_DATAMSPR_EgaliteHF, 0, length);
				strReturn = new String(commonByteArray_DATAMSPR_EgaliteHF, 0, length, utf8Charset);
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

			synchronized (commonByteArrayLock_DATAMSPR_EgaliteHF) {

				try {

					int length = 0;

					this.Id = dis.readInt();

					this.Annee = readInteger(dis);

					this.Structure = readString(dis);

					this.TrancheEffectif = readString(dis);

					this.SIREN = readString(dis);

					this.RaisonSociale = readString(dis);

					this.Region = readString(dis);

					this.Departement = readString(dis);

					this.Pays = readString(dis);

					this.CodeNAF = readString(dis);

					this.NoteEcartRemuneration = readInteger(dis);

					this.NoteEcartTauxPromotion = readInteger(dis);

					this.NoteHautesRemunerations = readInteger(dis);

					this.NoteIndex = readInteger(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_DATAMSPR_EgaliteHF) {

				try {

					int length = 0;

					this.Id = dis.readInt();

					this.Annee = readInteger(dis);

					this.Structure = readString(dis);

					this.TrancheEffectif = readString(dis);

					this.SIREN = readString(dis);

					this.RaisonSociale = readString(dis);

					this.Region = readString(dis);

					this.Departement = readString(dis);

					this.Pays = readString(dis);

					this.CodeNAF = readString(dis);

					this.NoteEcartRemuneration = readInteger(dis);

					this.NoteEcartTauxPromotion = readInteger(dis);

					this.NoteHautesRemunerations = readInteger(dis);

					this.NoteIndex = readInteger(dis);

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

				writeString(this.Structure, dos);

				// String

				writeString(this.TrancheEffectif, dos);

				// String

				writeString(this.SIREN, dos);

				// String

				writeString(this.RaisonSociale, dos);

				// String

				writeString(this.Region, dos);

				// String

				writeString(this.Departement, dos);

				// String

				writeString(this.Pays, dos);

				// String

				writeString(this.CodeNAF, dos);

				// Integer

				writeInteger(this.NoteEcartRemuneration, dos);

				// Integer

				writeInteger(this.NoteEcartTauxPromotion, dos);

				// Integer

				writeInteger(this.NoteHautesRemunerations, dos);

				// Integer

				writeInteger(this.NoteIndex, dos);

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

				writeString(this.Structure, dos);

				// String

				writeString(this.TrancheEffectif, dos);

				// String

				writeString(this.SIREN, dos);

				// String

				writeString(this.RaisonSociale, dos);

				// String

				writeString(this.Region, dos);

				// String

				writeString(this.Departement, dos);

				// String

				writeString(this.Pays, dos);

				// String

				writeString(this.CodeNAF, dos);

				// Integer

				writeInteger(this.NoteEcartRemuneration, dos);

				// Integer

				writeInteger(this.NoteEcartTauxPromotion, dos);

				// Integer

				writeInteger(this.NoteHautesRemunerations, dos);

				// Integer

				writeInteger(this.NoteIndex, dos);

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
			sb.append(",Structure=" + Structure);
			sb.append(",TrancheEffectif=" + TrancheEffectif);
			sb.append(",SIREN=" + SIREN);
			sb.append(",RaisonSociale=" + RaisonSociale);
			sb.append(",Region=" + Region);
			sb.append(",Departement=" + Departement);
			sb.append(",Pays=" + Pays);
			sb.append(",CodeNAF=" + CodeNAF);
			sb.append(",NoteEcartRemuneration=" + String.valueOf(NoteEcartRemuneration));
			sb.append(",NoteEcartTauxPromotion=" + String.valueOf(NoteEcartTauxPromotion));
			sb.append(",NoteHautesRemunerations=" + String.valueOf(NoteHautesRemunerations));
			sb.append(",NoteIndex=" + String.valueOf(NoteIndex));
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(row3Struct other) {

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
		final static byte[] commonByteArrayLock_DATAMSPR_EgaliteHF = new byte[0];
		static byte[] commonByteArray_DATAMSPR_EgaliteHF = new byte[0];
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

		public String Structure;

		public String getStructure() {
			return this.Structure;
		}

		public String TrancheEffectif;

		public String getTrancheEffectif() {
			return this.TrancheEffectif;
		}

		public String SIREN;

		public String getSIREN() {
			return this.SIREN;
		}

		public String RaisonSociale;

		public String getRaisonSociale() {
			return this.RaisonSociale;
		}

		public String Region;

		public String getRegion() {
			return this.Region;
		}

		public String Departement;

		public String getDepartement() {
			return this.Departement;
		}

		public String Pays;

		public String getPays() {
			return this.Pays;
		}

		public String CodeNAF;

		public String getCodeNAF() {
			return this.CodeNAF;
		}

		public Integer NoteEcartRemuneration;

		public Integer getNoteEcartRemuneration() {
			return this.NoteEcartRemuneration;
		}

		public Integer NoteEcartTauxPromotion;

		public Integer getNoteEcartTauxPromotion() {
			return this.NoteEcartTauxPromotion;
		}

		public Integer NoteHautesRemunerations;

		public Integer getNoteHautesRemunerations() {
			return this.NoteHautesRemunerations;
		}

		public Integer NoteIndex;

		public Integer getNoteIndex() {
			return this.NoteIndex;
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
			final row2Struct other = (row2Struct) obj;

			if (this.Id != other.Id)
				return false;

			return true;
		}

		public void copyDataTo(row2Struct other) {

			other.Id = this.Id;
			other.Annee = this.Annee;
			other.Structure = this.Structure;
			other.TrancheEffectif = this.TrancheEffectif;
			other.SIREN = this.SIREN;
			other.RaisonSociale = this.RaisonSociale;
			other.Region = this.Region;
			other.Departement = this.Departement;
			other.Pays = this.Pays;
			other.CodeNAF = this.CodeNAF;
			other.NoteEcartRemuneration = this.NoteEcartRemuneration;
			other.NoteEcartTauxPromotion = this.NoteEcartTauxPromotion;
			other.NoteHautesRemunerations = this.NoteHautesRemunerations;
			other.NoteIndex = this.NoteIndex;

		}

		public void copyKeysDataTo(row2Struct other) {

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
				if (length > commonByteArray_DATAMSPR_EgaliteHF.length) {
					if (length < 1024 && commonByteArray_DATAMSPR_EgaliteHF.length == 0) {
						commonByteArray_DATAMSPR_EgaliteHF = new byte[1024];
					} else {
						commonByteArray_DATAMSPR_EgaliteHF = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_DATAMSPR_EgaliteHF, 0, length);
				strReturn = new String(commonByteArray_DATAMSPR_EgaliteHF, 0, length, utf8Charset);
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
				if (length > commonByteArray_DATAMSPR_EgaliteHF.length) {
					if (length < 1024 && commonByteArray_DATAMSPR_EgaliteHF.length == 0) {
						commonByteArray_DATAMSPR_EgaliteHF = new byte[1024];
					} else {
						commonByteArray_DATAMSPR_EgaliteHF = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_DATAMSPR_EgaliteHF, 0, length);
				strReturn = new String(commonByteArray_DATAMSPR_EgaliteHF, 0, length, utf8Charset);
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

			synchronized (commonByteArrayLock_DATAMSPR_EgaliteHF) {

				try {

					int length = 0;

					this.Id = dis.readInt();

					this.Annee = readInteger(dis);

					this.Structure = readString(dis);

					this.TrancheEffectif = readString(dis);

					this.SIREN = readString(dis);

					this.RaisonSociale = readString(dis);

					this.Region = readString(dis);

					this.Departement = readString(dis);

					this.Pays = readString(dis);

					this.CodeNAF = readString(dis);

					this.NoteEcartRemuneration = readInteger(dis);

					this.NoteEcartTauxPromotion = readInteger(dis);

					this.NoteHautesRemunerations = readInteger(dis);

					this.NoteIndex = readInteger(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_DATAMSPR_EgaliteHF) {

				try {

					int length = 0;

					this.Id = dis.readInt();

					this.Annee = readInteger(dis);

					this.Structure = readString(dis);

					this.TrancheEffectif = readString(dis);

					this.SIREN = readString(dis);

					this.RaisonSociale = readString(dis);

					this.Region = readString(dis);

					this.Departement = readString(dis);

					this.Pays = readString(dis);

					this.CodeNAF = readString(dis);

					this.NoteEcartRemuneration = readInteger(dis);

					this.NoteEcartTauxPromotion = readInteger(dis);

					this.NoteHautesRemunerations = readInteger(dis);

					this.NoteIndex = readInteger(dis);

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

				writeString(this.Structure, dos);

				// String

				writeString(this.TrancheEffectif, dos);

				// String

				writeString(this.SIREN, dos);

				// String

				writeString(this.RaisonSociale, dos);

				// String

				writeString(this.Region, dos);

				// String

				writeString(this.Departement, dos);

				// String

				writeString(this.Pays, dos);

				// String

				writeString(this.CodeNAF, dos);

				// Integer

				writeInteger(this.NoteEcartRemuneration, dos);

				// Integer

				writeInteger(this.NoteEcartTauxPromotion, dos);

				// Integer

				writeInteger(this.NoteHautesRemunerations, dos);

				// Integer

				writeInteger(this.NoteIndex, dos);

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

				writeString(this.Structure, dos);

				// String

				writeString(this.TrancheEffectif, dos);

				// String

				writeString(this.SIREN, dos);

				// String

				writeString(this.RaisonSociale, dos);

				// String

				writeString(this.Region, dos);

				// String

				writeString(this.Departement, dos);

				// String

				writeString(this.Pays, dos);

				// String

				writeString(this.CodeNAF, dos);

				// Integer

				writeInteger(this.NoteEcartRemuneration, dos);

				// Integer

				writeInteger(this.NoteEcartTauxPromotion, dos);

				// Integer

				writeInteger(this.NoteHautesRemunerations, dos);

				// Integer

				writeInteger(this.NoteIndex, dos);

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
			sb.append(",Structure=" + Structure);
			sb.append(",TrancheEffectif=" + TrancheEffectif);
			sb.append(",SIREN=" + SIREN);
			sb.append(",RaisonSociale=" + RaisonSociale);
			sb.append(",Region=" + Region);
			sb.append(",Departement=" + Departement);
			sb.append(",Pays=" + Pays);
			sb.append(",CodeNAF=" + CodeNAF);
			sb.append(",NoteEcartRemuneration=" + String.valueOf(NoteEcartRemuneration));
			sb.append(",NoteEcartTauxPromotion=" + String.valueOf(NoteEcartTauxPromotion));
			sb.append(",NoteHautesRemunerations=" + String.valueOf(NoteHautesRemunerations));
			sb.append(",NoteIndex=" + String.valueOf(NoteIndex));
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(row2Struct other) {

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
		final static byte[] commonByteArrayLock_DATAMSPR_EgaliteHF = new byte[0];
		static byte[] commonByteArray_DATAMSPR_EgaliteHF = new byte[0];
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

		public String Structure;

		public String getStructure() {
			return this.Structure;
		}

		public String TrancheEffectif;

		public String getTrancheEffectif() {
			return this.TrancheEffectif;
		}

		public String SIREN;

		public String getSIREN() {
			return this.SIREN;
		}

		public String RaisonSociale;

		public String getRaisonSociale() {
			return this.RaisonSociale;
		}

		public String Region;

		public String getRegion() {
			return this.Region;
		}

		public String Departement;

		public String getDepartement() {
			return this.Departement;
		}

		public String Pays;

		public String getPays() {
			return this.Pays;
		}

		public String CodeNAF;

		public String getCodeNAF() {
			return this.CodeNAF;
		}

		public Integer NoteEcartRemuneration;

		public Integer getNoteEcartRemuneration() {
			return this.NoteEcartRemuneration;
		}

		public Integer NoteEcartTauxPromotion;

		public Integer getNoteEcartTauxPromotion() {
			return this.NoteEcartTauxPromotion;
		}

		public Integer NoteHautesRemunerations;

		public Integer getNoteHautesRemunerations() {
			return this.NoteHautesRemunerations;
		}

		public Integer NoteIndex;

		public Integer getNoteIndex() {
			return this.NoteIndex;
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
			other.Structure = this.Structure;
			other.TrancheEffectif = this.TrancheEffectif;
			other.SIREN = this.SIREN;
			other.RaisonSociale = this.RaisonSociale;
			other.Region = this.Region;
			other.Departement = this.Departement;
			other.Pays = this.Pays;
			other.CodeNAF = this.CodeNAF;
			other.NoteEcartRemuneration = this.NoteEcartRemuneration;
			other.NoteEcartTauxPromotion = this.NoteEcartTauxPromotion;
			other.NoteHautesRemunerations = this.NoteHautesRemunerations;
			other.NoteIndex = this.NoteIndex;

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
				if (length > commonByteArray_DATAMSPR_EgaliteHF.length) {
					if (length < 1024 && commonByteArray_DATAMSPR_EgaliteHF.length == 0) {
						commonByteArray_DATAMSPR_EgaliteHF = new byte[1024];
					} else {
						commonByteArray_DATAMSPR_EgaliteHF = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_DATAMSPR_EgaliteHF, 0, length);
				strReturn = new String(commonByteArray_DATAMSPR_EgaliteHF, 0, length, utf8Charset);
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
				if (length > commonByteArray_DATAMSPR_EgaliteHF.length) {
					if (length < 1024 && commonByteArray_DATAMSPR_EgaliteHF.length == 0) {
						commonByteArray_DATAMSPR_EgaliteHF = new byte[1024];
					} else {
						commonByteArray_DATAMSPR_EgaliteHF = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_DATAMSPR_EgaliteHF, 0, length);
				strReturn = new String(commonByteArray_DATAMSPR_EgaliteHF, 0, length, utf8Charset);
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

			synchronized (commonByteArrayLock_DATAMSPR_EgaliteHF) {

				try {

					int length = 0;

					this.Id = dis.readInt();

					this.Annee = readInteger(dis);

					this.Structure = readString(dis);

					this.TrancheEffectif = readString(dis);

					this.SIREN = readString(dis);

					this.RaisonSociale = readString(dis);

					this.Region = readString(dis);

					this.Departement = readString(dis);

					this.Pays = readString(dis);

					this.CodeNAF = readString(dis);

					this.NoteEcartRemuneration = readInteger(dis);

					this.NoteEcartTauxPromotion = readInteger(dis);

					this.NoteHautesRemunerations = readInteger(dis);

					this.NoteIndex = readInteger(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_DATAMSPR_EgaliteHF) {

				try {

					int length = 0;

					this.Id = dis.readInt();

					this.Annee = readInteger(dis);

					this.Structure = readString(dis);

					this.TrancheEffectif = readString(dis);

					this.SIREN = readString(dis);

					this.RaisonSociale = readString(dis);

					this.Region = readString(dis);

					this.Departement = readString(dis);

					this.Pays = readString(dis);

					this.CodeNAF = readString(dis);

					this.NoteEcartRemuneration = readInteger(dis);

					this.NoteEcartTauxPromotion = readInteger(dis);

					this.NoteHautesRemunerations = readInteger(dis);

					this.NoteIndex = readInteger(dis);

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

				writeString(this.Structure, dos);

				// String

				writeString(this.TrancheEffectif, dos);

				// String

				writeString(this.SIREN, dos);

				// String

				writeString(this.RaisonSociale, dos);

				// String

				writeString(this.Region, dos);

				// String

				writeString(this.Departement, dos);

				// String

				writeString(this.Pays, dos);

				// String

				writeString(this.CodeNAF, dos);

				// Integer

				writeInteger(this.NoteEcartRemuneration, dos);

				// Integer

				writeInteger(this.NoteEcartTauxPromotion, dos);

				// Integer

				writeInteger(this.NoteHautesRemunerations, dos);

				// Integer

				writeInteger(this.NoteIndex, dos);

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

				writeString(this.Structure, dos);

				// String

				writeString(this.TrancheEffectif, dos);

				// String

				writeString(this.SIREN, dos);

				// String

				writeString(this.RaisonSociale, dos);

				// String

				writeString(this.Region, dos);

				// String

				writeString(this.Departement, dos);

				// String

				writeString(this.Pays, dos);

				// String

				writeString(this.CodeNAF, dos);

				// Integer

				writeInteger(this.NoteEcartRemuneration, dos);

				// Integer

				writeInteger(this.NoteEcartTauxPromotion, dos);

				// Integer

				writeInteger(this.NoteHautesRemunerations, dos);

				// Integer

				writeInteger(this.NoteIndex, dos);

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
			sb.append(",Structure=" + Structure);
			sb.append(",TrancheEffectif=" + TrancheEffectif);
			sb.append(",SIREN=" + SIREN);
			sb.append(",RaisonSociale=" + RaisonSociale);
			sb.append(",Region=" + Region);
			sb.append(",Departement=" + Departement);
			sb.append(",Pays=" + Pays);
			sb.append(",CodeNAF=" + CodeNAF);
			sb.append(",NoteEcartRemuneration=" + String.valueOf(NoteEcartRemuneration));
			sb.append(",NoteEcartTauxPromotion=" + String.valueOf(NoteEcartTauxPromotion));
			sb.append(",NoteHautesRemunerations=" + String.valueOf(NoteHautesRemunerations));
			sb.append(",NoteIndex=" + String.valueOf(NoteIndex));
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

	public static class row1Struct implements routines.system.IPersistableRow<row1Struct> {
		final static byte[] commonByteArrayLock_DATAMSPR_EgaliteHF = new byte[0];
		static byte[] commonByteArray_DATAMSPR_EgaliteHF = new byte[0];

		public Integer Annee;

		public Integer getAnnee() {
			return this.Annee;
		}

		public String Structure;

		public String getStructure() {
			return this.Structure;
		}

		public String TrancheEffectif;

		public String getTrancheEffectif() {
			return this.TrancheEffectif;
		}

		public String SIREN;

		public String getSIREN() {
			return this.SIREN;
		}

		public String RaisonSociale;

		public String getRaisonSociale() {
			return this.RaisonSociale;
		}

		public String NomUES;

		public String getNomUES() {
			return this.NomUES;
		}

		public String EntrepriseUES;

		public String getEntrepriseUES() {
			return this.EntrepriseUES;
		}

		public String Region;

		public String getRegion() {
			return this.Region;
		}

		public String Departement;

		public String getDepartement() {
			return this.Departement;
		}

		public String Pays;

		public String getPays() {
			return this.Pays;
		}

		public String CodeNAF;

		public String getCodeNAF() {
			return this.CodeNAF;
		}

		public String NoteEcartRemuneration;

		public String getNoteEcartRemuneration() {
			return this.NoteEcartRemuneration;
		}

		public String NoteEcartTauxAugmentationHorsPromo;

		public String getNoteEcartTauxAugmentationHorsPromo() {
			return this.NoteEcartTauxAugmentationHorsPromo;
		}

		public String NoteEcartTauxPromotion;

		public String getNoteEcartTauxPromotion() {
			return this.NoteEcartTauxPromotion;
		}

		public String NoteEcartTauxAugmentation;

		public String getNoteEcartTauxAugmentation() {
			return this.NoteEcartTauxAugmentation;
		}

		public String NoteRetourCongeMaternite;

		public String getNoteRetourCongeMaternite() {
			return this.NoteRetourCongeMaternite;
		}

		public String NoteHautesRemunerations;

		public String getNoteHautesRemunerations() {
			return this.NoteHautesRemunerations;
		}

		public String NoteIndex;

		public String getNoteIndex() {
			return this.NoteIndex;
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
				if (length > commonByteArray_DATAMSPR_EgaliteHF.length) {
					if (length < 1024 && commonByteArray_DATAMSPR_EgaliteHF.length == 0) {
						commonByteArray_DATAMSPR_EgaliteHF = new byte[1024];
					} else {
						commonByteArray_DATAMSPR_EgaliteHF = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_DATAMSPR_EgaliteHF, 0, length);
				strReturn = new String(commonByteArray_DATAMSPR_EgaliteHF, 0, length, utf8Charset);
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
				if (length > commonByteArray_DATAMSPR_EgaliteHF.length) {
					if (length < 1024 && commonByteArray_DATAMSPR_EgaliteHF.length == 0) {
						commonByteArray_DATAMSPR_EgaliteHF = new byte[1024];
					} else {
						commonByteArray_DATAMSPR_EgaliteHF = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_DATAMSPR_EgaliteHF, 0, length);
				strReturn = new String(commonByteArray_DATAMSPR_EgaliteHF, 0, length, utf8Charset);
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

			synchronized (commonByteArrayLock_DATAMSPR_EgaliteHF) {

				try {

					int length = 0;

					this.Annee = readInteger(dis);

					this.Structure = readString(dis);

					this.TrancheEffectif = readString(dis);

					this.SIREN = readString(dis);

					this.RaisonSociale = readString(dis);

					this.NomUES = readString(dis);

					this.EntrepriseUES = readString(dis);

					this.Region = readString(dis);

					this.Departement = readString(dis);

					this.Pays = readString(dis);

					this.CodeNAF = readString(dis);

					this.NoteEcartRemuneration = readString(dis);

					this.NoteEcartTauxAugmentationHorsPromo = readString(dis);

					this.NoteEcartTauxPromotion = readString(dis);

					this.NoteEcartTauxAugmentation = readString(dis);

					this.NoteRetourCongeMaternite = readString(dis);

					this.NoteHautesRemunerations = readString(dis);

					this.NoteIndex = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_DATAMSPR_EgaliteHF) {

				try {

					int length = 0;

					this.Annee = readInteger(dis);

					this.Structure = readString(dis);

					this.TrancheEffectif = readString(dis);

					this.SIREN = readString(dis);

					this.RaisonSociale = readString(dis);

					this.NomUES = readString(dis);

					this.EntrepriseUES = readString(dis);

					this.Region = readString(dis);

					this.Departement = readString(dis);

					this.Pays = readString(dis);

					this.CodeNAF = readString(dis);

					this.NoteEcartRemuneration = readString(dis);

					this.NoteEcartTauxAugmentationHorsPromo = readString(dis);

					this.NoteEcartTauxPromotion = readString(dis);

					this.NoteEcartTauxAugmentation = readString(dis);

					this.NoteRetourCongeMaternite = readString(dis);

					this.NoteHautesRemunerations = readString(dis);

					this.NoteIndex = readString(dis);

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

				writeString(this.Structure, dos);

				// String

				writeString(this.TrancheEffectif, dos);

				// String

				writeString(this.SIREN, dos);

				// String

				writeString(this.RaisonSociale, dos);

				// String

				writeString(this.NomUES, dos);

				// String

				writeString(this.EntrepriseUES, dos);

				// String

				writeString(this.Region, dos);

				// String

				writeString(this.Departement, dos);

				// String

				writeString(this.Pays, dos);

				// String

				writeString(this.CodeNAF, dos);

				// String

				writeString(this.NoteEcartRemuneration, dos);

				// String

				writeString(this.NoteEcartTauxAugmentationHorsPromo, dos);

				// String

				writeString(this.NoteEcartTauxPromotion, dos);

				// String

				writeString(this.NoteEcartTauxAugmentation, dos);

				// String

				writeString(this.NoteRetourCongeMaternite, dos);

				// String

				writeString(this.NoteHautesRemunerations, dos);

				// String

				writeString(this.NoteIndex, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// Integer

				writeInteger(this.Annee, dos);

				// String

				writeString(this.Structure, dos);

				// String

				writeString(this.TrancheEffectif, dos);

				// String

				writeString(this.SIREN, dos);

				// String

				writeString(this.RaisonSociale, dos);

				// String

				writeString(this.NomUES, dos);

				// String

				writeString(this.EntrepriseUES, dos);

				// String

				writeString(this.Region, dos);

				// String

				writeString(this.Departement, dos);

				// String

				writeString(this.Pays, dos);

				// String

				writeString(this.CodeNAF, dos);

				// String

				writeString(this.NoteEcartRemuneration, dos);

				// String

				writeString(this.NoteEcartTauxAugmentationHorsPromo, dos);

				// String

				writeString(this.NoteEcartTauxPromotion, dos);

				// String

				writeString(this.NoteEcartTauxAugmentation, dos);

				// String

				writeString(this.NoteRetourCongeMaternite, dos);

				// String

				writeString(this.NoteHautesRemunerations, dos);

				// String

				writeString(this.NoteIndex, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("Annee=" + String.valueOf(Annee));
			sb.append(",Structure=" + Structure);
			sb.append(",TrancheEffectif=" + TrancheEffectif);
			sb.append(",SIREN=" + SIREN);
			sb.append(",RaisonSociale=" + RaisonSociale);
			sb.append(",NomUES=" + NomUES);
			sb.append(",EntrepriseUES=" + EntrepriseUES);
			sb.append(",Region=" + Region);
			sb.append(",Departement=" + Departement);
			sb.append(",Pays=" + Pays);
			sb.append(",CodeNAF=" + CodeNAF);
			sb.append(",NoteEcartRemuneration=" + NoteEcartRemuneration);
			sb.append(",NoteEcartTauxAugmentationHorsPromo=" + NoteEcartTauxAugmentationHorsPromo);
			sb.append(",NoteEcartTauxPromotion=" + NoteEcartTauxPromotion);
			sb.append(",NoteEcartTauxAugmentation=" + NoteEcartTauxAugmentation);
			sb.append(",NoteRetourCongeMaternite=" + NoteRetourCongeMaternite);
			sb.append(",NoteHautesRemunerations=" + NoteHautesRemunerations);
			sb.append(",NoteIndex=" + NoteIndex);
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

	public void tFileInputExcel_1Process(final java.util.Map<String, Object> globalMap) throws TalendException {
		globalMap.put("tFileInputExcel_1_SUBPROCESS_STATE", 0);

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
				out1Struct out1 = new out1Struct();
				out1Struct row2 = out1;
				row4Struct row4 = new row4Struct();
				row3Struct row3 = new row3Struct();

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
						"C:/Program Files (x86)/TOS_DI-8.0.1/studio/workspace/DATAMSPR/_output/EgaliteHF.csv"))
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
					outtFileOutputDelimited_1.write("Structure");
					outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
					outtFileOutputDelimited_1.write("TrancheEffectif");
					outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
					outtFileOutputDelimited_1.write("SIREN");
					outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
					outtFileOutputDelimited_1.write("RaisonSociale");
					outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
					outtFileOutputDelimited_1.write("Region");
					outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
					outtFileOutputDelimited_1.write("Departement");
					outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
					outtFileOutputDelimited_1.write("Pays");
					outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
					outtFileOutputDelimited_1.write("CodeNAF");
					outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
					outtFileOutputDelimited_1.write("NoteEcartRemuneration");
					outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
					outtFileOutputDelimited_1.write("NoteEcartTauxPromotion");
					outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
					outtFileOutputDelimited_1.write("NoteHautesRemunerations");
					outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
					outtFileOutputDelimited_1.write("NoteIndex");
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
					runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "row3");
				}

				int tos_count_tDBOutput_1 = 0;

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

				String tableName_tDBOutput_1 = "EgaliteHF";
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
						.decryptPassword("enc:routine.encryption.key.v1:Y2+t3UoPRND+R4h6GMFAtzNSgo0VAEtzuMyVZzedlww=");

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
					if (table_tDBOutput_1.equalsIgnoreCase("EgaliteHF")) {
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
							+ "`(`Id` INT(255)   not null ,`Annee` INT(255)  ,`Structure` VARCHAR(255)  ,`TrancheEffectif` VARCHAR(255)  ,`SIREN` VARCHAR(255)  ,`RaisonSociale` VARCHAR(255)  ,`Region` VARCHAR(255)  ,`Departement` VARCHAR(255)  ,`Pays` VARCHAR(255)  ,`CodeNAF` VARCHAR(255)  ,`NoteEcartRemuneration` INT(255)  ,`NoteEcartTauxPromotion` INT(255)  ,`NoteHautesRemunerations` INT(255)  ,`NoteIndex` INT(255)  ,primary key(`Id`))");
				}

				String insert_tDBOutput_1 = "INSERT INTO `" + "EgaliteHF"
						+ "` (`Id`,`Annee`,`Structure`,`TrancheEffectif`,`SIREN`,`RaisonSociale`,`Region`,`Departement`,`Pays`,`CodeNAF`,`NoteEcartRemuneration`,`NoteEcartTauxPromotion`,`NoteHautesRemunerations`,`NoteIndex`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
				int batchSize_tDBOutput_1 = 100;
				int batchSizeCounter_tDBOutput_1 = 0;

				java.sql.PreparedStatement pstmt_tDBOutput_1 = conn_tDBOutput_1.prepareStatement(insert_tDBOutput_1);
				resourceMap.put("pstmt_tDBOutput_1", pstmt_tDBOutput_1);

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
					runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "row2");
				}

				int tos_count_tReplicate_1 = 0;

				/**
				 * [tReplicate_1 begin ] stop
				 */

				/**
				 * [tLogRow_1 begin ] start
				 */

				ok_Hash.put("tLogRow_1", false);
				start_Hash.put("tLogRow_1", System.currentTimeMillis());

				currentComponent = "tLogRow_1";

				if (execStat) {
					runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "out1");
				}

				int tos_count_tLogRow_1 = 0;

				///////////////////////

				class Util_tLogRow_1 {

					String[] des_top = { ".", ".", "-", "+" };

					String[] des_head = { "|=", "=|", "-", "+" };

					String[] des_bottom = { "'", "'", "-", "+" };

					String name = "";

					java.util.List<String[]> list = new java.util.ArrayList<String[]>();

					int[] colLengths = new int[14];

					public void addRow(String[] row) {

						for (int i = 0; i < 14; i++) {
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
						for (k = 0; k < (totals + 13 - name.length()) / 2; k++) {
							sb.append(' ');
						}
						sb.append(name);
						for (int i = 0; i < totals + 13 - name.length() - k; i++) {
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

						// last column
						for (int i = 0; i < colLengths[13] - fillChars[1].length() + 1; i++) {
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
				util_tLogRow_1.addRow(new String[] { "Id", "Annee", "Structure", "TrancheEffectif", "SIREN",
						"RaisonSociale", "Region", "Departement", "Pays", "CodeNAF", "NoteEcartRemuneration",
						"NoteEcartTauxPromotion", "NoteHautesRemunerations", "NoteIndex", });
				StringBuilder strBuffer_tLogRow_1 = null;
				int nb_line_tLogRow_1 = 0;
///////////////////////    			

				/**
				 * [tLogRow_1 begin ] stop
				 */

				/**
				 * [tMap_1 begin ] start
				 */

				ok_Hash.put("tMap_1", false);
				start_Hash.put("tMap_1", System.currentTimeMillis());

				currentComponent = "tMap_1";

				if (execStat) {
					runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "row1");
				}

				int tos_count_tMap_1 = 0;

// ###############################
// # Lookup's keys initialization
// ###############################        

// ###############################
// # Vars initialization
				class Var__tMap_1__Struct {
					int Id;
					int NoteEcartRemuneration;
					int NoteEcartTauxPromotion;
					int NoteHautesRemunerations;
					int NoteIndex;
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
				 * [tFileInputExcel_1 begin ] start
				 */

				ok_Hash.put("tFileInputExcel_1", false);
				start_Hash.put("tFileInputExcel_1", System.currentTimeMillis());

				currentComponent = "tFileInputExcel_1";

				int tos_count_tFileInputExcel_1 = 0;

				final String decryptedPassword_tFileInputExcel_1 = routines.system.PasswordEncryptUtil
						.decryptPassword("enc:routine.encryption.key.v1:LU6bl+DGqVzOurofoDfADyrHTkSqS3De2Xgzog==");
				String password_tFileInputExcel_1 = decryptedPassword_tFileInputExcel_1;
				if (password_tFileInputExcel_1.isEmpty()) {
					password_tFileInputExcel_1 = null;
				}
				Object source_tFileInputExcel_1 = "C:/Program Files (x86)/TOS_DI-8.0.1/studio/workspace/DATAMSPR/_input/index-egalite-fh.xlsx";
				com.talend.excel.xssf.event.ExcelReader excelReader_tFileInputExcel_1 = null;

				if (source_tFileInputExcel_1 instanceof java.io.InputStream
						|| source_tFileInputExcel_1 instanceof String) {
					excelReader_tFileInputExcel_1 = new com.talend.excel.xssf.event.ExcelReader();
					excelReader_tFileInputExcel_1.setIncludePhoneticRuns(true);
				} else {
					throw new java.lang.Exception("The data source should be specified as Inputstream or File Path!");
				}

				try {
					excelReader_tFileInputExcel_1.addSheetName("Données publiques Index Egapro", false);
					int start_column_tFileInputExcel_1 = 1 - 1;
					int end_column_tFileInputExcel_1 = -1;
					if (start_column_tFileInputExcel_1 >= 0) {// follow start column

						end_column_tFileInputExcel_1 = start_column_tFileInputExcel_1 + 18 - 1;

					} else if (end_column_tFileInputExcel_1 >= 0) {// follow end column
						start_column_tFileInputExcel_1 = end_column_tFileInputExcel_1 - 18 + 1;
					}

					if (end_column_tFileInputExcel_1 < 0 || start_column_tFileInputExcel_1 < 0) {
						throw new RuntimeException("Error start column and end column.");
					}
					int actual_end_column_tFileInputExcel_1 = end_column_tFileInputExcel_1;

					int header_tFileInputExcel_1 = 1;
					int limit_tFileInputExcel_1 = -1;

					int nb_line_tFileInputExcel_1 = 0;

					// for the number format
					java.text.DecimalFormat df_tFileInputExcel_1 = new java.text.DecimalFormat(
							"#.####################################");
					char decimalChar_tFileInputExcel_1 = df_tFileInputExcel_1.getDecimalFormatSymbols()
							.getDecimalSeparator();

					if (source_tFileInputExcel_1 instanceof String) {
						excelReader_tFileInputExcel_1.parse((String) source_tFileInputExcel_1, "UTF-8",
								password_tFileInputExcel_1);
					} else if (source_tFileInputExcel_1 instanceof java.io.InputStream) {
						excelReader_tFileInputExcel_1.parse((java.io.InputStream) source_tFileInputExcel_1, "UTF-8",
								password_tFileInputExcel_1);
					}

					while ((header_tFileInputExcel_1--) > 0 && excelReader_tFileInputExcel_1.hasNext()) {// skip the
																											// header
						excelReader_tFileInputExcel_1.next();
					}

					while (excelReader_tFileInputExcel_1.hasNext()) {
						int emptyColumnCount_tFileInputExcel_1 = 0;

						if (limit_tFileInputExcel_1 != -1 && nb_line_tFileInputExcel_1 >= limit_tFileInputExcel_1) {
							excelReader_tFileInputExcel_1.stopRead();
							break;
						}

						java.util.List<String> row_tFileInputExcel_1 = excelReader_tFileInputExcel_1.next();
						row1 = null;
						int tempRowLength_tFileInputExcel_1 = 18;

						int columnIndex_tFileInputExcel_1 = 0;

						String[] temp_row_tFileInputExcel_1 = new String[tempRowLength_tFileInputExcel_1];

						for (int i_tFileInputExcel_1 = 0; i_tFileInputExcel_1 < tempRowLength_tFileInputExcel_1; i_tFileInputExcel_1++) {
							int current_tFileInputExcel_1 = i_tFileInputExcel_1 + start_column_tFileInputExcel_1;
							if (current_tFileInputExcel_1 <= actual_end_column_tFileInputExcel_1) {
								if (current_tFileInputExcel_1 < row_tFileInputExcel_1.size()) {
									String column_tFileInputExcel_1 = row_tFileInputExcel_1
											.get(current_tFileInputExcel_1);
									if (column_tFileInputExcel_1 != null) {
										temp_row_tFileInputExcel_1[i_tFileInputExcel_1] = column_tFileInputExcel_1;
									} else {
										temp_row_tFileInputExcel_1[i_tFileInputExcel_1] = "";
									}
								} else {
									temp_row_tFileInputExcel_1[i_tFileInputExcel_1] = "";
								}
							} else {
								temp_row_tFileInputExcel_1[i_tFileInputExcel_1] = "";
							}
						}

						boolean whetherReject_tFileInputExcel_1 = false;
						row1 = new row1Struct();
						int curColNum_tFileInputExcel_1 = -1;
						String curColName_tFileInputExcel_1 = "";

						try {
							columnIndex_tFileInputExcel_1 = 0;

							if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].trim().length() > 0) {
								curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
										+ start_column_tFileInputExcel_1 + 1;
								curColName_tFileInputExcel_1 = "Annee";

								row1.Annee = ParserUtils.parseTo_Integer(ParserUtils.parseTo_Number(
										temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].trim(), null,
										'.' == decimalChar_tFileInputExcel_1 ? null : decimalChar_tFileInputExcel_1));
							} else {
								row1.Annee = null;
								emptyColumnCount_tFileInputExcel_1++;
							}
							columnIndex_tFileInputExcel_1 = 1;

							if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].trim().length() > 0) {
								curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
										+ start_column_tFileInputExcel_1 + 1;
								curColName_tFileInputExcel_1 = "Structure";

								row1.Structure = temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].trim();
							} else {
								row1.Structure = null;
								emptyColumnCount_tFileInputExcel_1++;
							}
							columnIndex_tFileInputExcel_1 = 2;

							if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].trim().length() > 0) {
								curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
										+ start_column_tFileInputExcel_1 + 1;
								curColName_tFileInputExcel_1 = "TrancheEffectif";

								row1.TrancheEffectif = temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].trim();
							} else {
								row1.TrancheEffectif = null;
								emptyColumnCount_tFileInputExcel_1++;
							}
							columnIndex_tFileInputExcel_1 = 3;

							if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].trim().length() > 0) {
								curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
										+ start_column_tFileInputExcel_1 + 1;
								curColName_tFileInputExcel_1 = "SIREN";

								row1.SIREN = temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].trim();
							} else {
								row1.SIREN = null;
								emptyColumnCount_tFileInputExcel_1++;
							}
							columnIndex_tFileInputExcel_1 = 4;

							if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].trim().length() > 0) {
								curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
										+ start_column_tFileInputExcel_1 + 1;
								curColName_tFileInputExcel_1 = "RaisonSociale";

								row1.RaisonSociale = temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].trim();
							} else {
								row1.RaisonSociale = null;
								emptyColumnCount_tFileInputExcel_1++;
							}
							columnIndex_tFileInputExcel_1 = 5;

							if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].trim().length() > 0) {
								curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
										+ start_column_tFileInputExcel_1 + 1;
								curColName_tFileInputExcel_1 = "NomUES";

								row1.NomUES = temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].trim();
							} else {
								row1.NomUES = null;
								emptyColumnCount_tFileInputExcel_1++;
							}
							columnIndex_tFileInputExcel_1 = 6;

							if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].trim().length() > 0) {
								curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
										+ start_column_tFileInputExcel_1 + 1;
								curColName_tFileInputExcel_1 = "EntrepriseUES";

								row1.EntrepriseUES = temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].trim();
							} else {
								row1.EntrepriseUES = null;
								emptyColumnCount_tFileInputExcel_1++;
							}
							columnIndex_tFileInputExcel_1 = 7;

							if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].trim().length() > 0) {
								curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
										+ start_column_tFileInputExcel_1 + 1;
								curColName_tFileInputExcel_1 = "Region";

								row1.Region = temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].trim();
							} else {
								row1.Region = null;
								emptyColumnCount_tFileInputExcel_1++;
							}
							columnIndex_tFileInputExcel_1 = 8;

							if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].trim().length() > 0) {
								curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
										+ start_column_tFileInputExcel_1 + 1;
								curColName_tFileInputExcel_1 = "Departement";

								row1.Departement = temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].trim();
							} else {
								row1.Departement = null;
								emptyColumnCount_tFileInputExcel_1++;
							}
							columnIndex_tFileInputExcel_1 = 9;

							if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].trim().length() > 0) {
								curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
										+ start_column_tFileInputExcel_1 + 1;
								curColName_tFileInputExcel_1 = "Pays";

								row1.Pays = temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].trim();
							} else {
								row1.Pays = null;
								emptyColumnCount_tFileInputExcel_1++;
							}
							columnIndex_tFileInputExcel_1 = 10;

							if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].trim().length() > 0) {
								curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
										+ start_column_tFileInputExcel_1 + 1;
								curColName_tFileInputExcel_1 = "CodeNAF";

								row1.CodeNAF = temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].trim();
							} else {
								row1.CodeNAF = null;
								emptyColumnCount_tFileInputExcel_1++;
							}
							columnIndex_tFileInputExcel_1 = 11;

							if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].trim().length() > 0) {
								curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
										+ start_column_tFileInputExcel_1 + 1;
								curColName_tFileInputExcel_1 = "NoteEcartRemuneration";

								row1.NoteEcartRemuneration = temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1]
										.trim();
							} else {
								row1.NoteEcartRemuneration = null;
								emptyColumnCount_tFileInputExcel_1++;
							}
							columnIndex_tFileInputExcel_1 = 12;

							if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].trim().length() > 0) {
								curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
										+ start_column_tFileInputExcel_1 + 1;
								curColName_tFileInputExcel_1 = "NoteEcartTauxAugmentationHorsPromo";

								row1.NoteEcartTauxAugmentationHorsPromo = temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1]
										.trim();
							} else {
								row1.NoteEcartTauxAugmentationHorsPromo = null;
								emptyColumnCount_tFileInputExcel_1++;
							}
							columnIndex_tFileInputExcel_1 = 13;

							if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].trim().length() > 0) {
								curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
										+ start_column_tFileInputExcel_1 + 1;
								curColName_tFileInputExcel_1 = "NoteEcartTauxPromotion";

								row1.NoteEcartTauxPromotion = temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1]
										.trim();
							} else {
								row1.NoteEcartTauxPromotion = null;
								emptyColumnCount_tFileInputExcel_1++;
							}
							columnIndex_tFileInputExcel_1 = 14;

							if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].trim().length() > 0) {
								curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
										+ start_column_tFileInputExcel_1 + 1;
								curColName_tFileInputExcel_1 = "NoteEcartTauxAugmentation";

								row1.NoteEcartTauxAugmentation = temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1]
										.trim();
							} else {
								row1.NoteEcartTauxAugmentation = null;
								emptyColumnCount_tFileInputExcel_1++;
							}
							columnIndex_tFileInputExcel_1 = 15;

							if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].trim().length() > 0) {
								curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
										+ start_column_tFileInputExcel_1 + 1;
								curColName_tFileInputExcel_1 = "NoteRetourCongeMaternite";

								row1.NoteRetourCongeMaternite = temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1]
										.trim();
							} else {
								row1.NoteRetourCongeMaternite = null;
								emptyColumnCount_tFileInputExcel_1++;
							}
							columnIndex_tFileInputExcel_1 = 16;

							if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].trim().length() > 0) {
								curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
										+ start_column_tFileInputExcel_1 + 1;
								curColName_tFileInputExcel_1 = "NoteHautesRemunerations";

								row1.NoteHautesRemunerations = temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1]
										.trim();
							} else {
								row1.NoteHautesRemunerations = null;
								emptyColumnCount_tFileInputExcel_1++;
							}
							columnIndex_tFileInputExcel_1 = 17;

							if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].trim().length() > 0) {
								curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
										+ start_column_tFileInputExcel_1 + 1;
								curColName_tFileInputExcel_1 = "NoteIndex";

								row1.NoteIndex = temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].trim();
							} else {
								row1.NoteIndex = null;
								emptyColumnCount_tFileInputExcel_1++;
							}
							nb_line_tFileInputExcel_1++;

						} catch (java.lang.Exception e) {
							globalMap.put("tFileInputExcel_1_ERROR_MESSAGE", e.getMessage());
							whetherReject_tFileInputExcel_1 = true;
							System.err.println(e.getMessage());
							row1 = null;
						}

						/**
						 * [tFileInputExcel_1 begin ] stop
						 */

						/**
						 * [tFileInputExcel_1 main ] start
						 */

						currentComponent = "tFileInputExcel_1";

						tos_count_tFileInputExcel_1++;

						/**
						 * [tFileInputExcel_1 main ] stop
						 */

						/**
						 * [tFileInputExcel_1 process_data_begin ] start
						 */

						currentComponent = "tFileInputExcel_1";

						/**
						 * [tFileInputExcel_1 process_data_begin ] stop
						 */
// Start of branch "row1"
						if (row1 != null) {

							/**
							 * [tMap_1 main ] start
							 */

							currentComponent = "tMap_1";

							if (execStat) {
								runStat.updateStatOnConnection(iterateId, 1, 1

										, "row1"

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
								Var.Id = Numeric.sequence("Id", 1, 1);
								Var.NoteEcartRemuneration = Integer.parseInt(
										row1.NoteEcartRemuneration.equals("NC") ? "0" : row1.NoteEcartRemuneration);
								Var.NoteEcartTauxPromotion = Integer.parseInt(
										(row1.NoteEcartRemuneration == null || row1.NoteEcartRemuneration.equals("NC"))
												? "0"
												: row1.NoteEcartRemuneration);
								Var.NoteHautesRemunerations = Integer.parseInt(
										row1.NoteHautesRemunerations.equals("NC") ? "0" : row1.NoteHautesRemunerations);
								Var.NoteIndex = Integer.parseInt(row1.NoteIndex.equals("NC") ? "0" : row1.NoteIndex);// ###############################
								// ###############################
								// # Output tables

								out1 = null;

// # Output table : 'out1'
								out1_tmp.Id = Var.Id;
								out1_tmp.Annee = row1.Annee;
								out1_tmp.Structure = row1.Structure;
								out1_tmp.TrancheEffectif = row1.TrancheEffectif;
								out1_tmp.SIREN = row1.SIREN;
								out1_tmp.RaisonSociale = row1.RaisonSociale;
								out1_tmp.Region = row1.Region;
								out1_tmp.Departement = row1.Departement;
								out1_tmp.Pays = row1.Pays;
								out1_tmp.CodeNAF = row1.CodeNAF;
								out1_tmp.NoteEcartRemuneration = Var.NoteEcartRemuneration;
								out1_tmp.NoteEcartTauxPromotion = Var.NoteEcartTauxPromotion;
								out1_tmp.NoteHautesRemunerations = Var.NoteHautesRemunerations;
								out1_tmp.NoteIndex = Var.NoteIndex;
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
								 * [tLogRow_1 main ] start
								 */

								currentComponent = "tLogRow_1";

								if (execStat) {
									runStat.updateStatOnConnection(iterateId, 1, 1

											, "out1"

									);
								}

///////////////////////		

								String[] row_tLogRow_1 = new String[14];

								row_tLogRow_1[0] = String.valueOf(out1.Id);

								if (out1.Annee != null) { //
									row_tLogRow_1[1] = String.valueOf(out1.Annee);

								} //

								if (out1.Structure != null) { //
									row_tLogRow_1[2] = String.valueOf(out1.Structure);

								} //

								if (out1.TrancheEffectif != null) { //
									row_tLogRow_1[3] = String.valueOf(out1.TrancheEffectif);

								} //

								if (out1.SIREN != null) { //
									row_tLogRow_1[4] = String.valueOf(out1.SIREN);

								} //

								if (out1.RaisonSociale != null) { //
									row_tLogRow_1[5] = String.valueOf(out1.RaisonSociale);

								} //

								if (out1.Region != null) { //
									row_tLogRow_1[6] = String.valueOf(out1.Region);

								} //

								if (out1.Departement != null) { //
									row_tLogRow_1[7] = String.valueOf(out1.Departement);

								} //

								if (out1.Pays != null) { //
									row_tLogRow_1[8] = String.valueOf(out1.Pays);

								} //

								if (out1.CodeNAF != null) { //
									row_tLogRow_1[9] = String.valueOf(out1.CodeNAF);

								} //

								if (out1.NoteEcartRemuneration != null) { //
									row_tLogRow_1[10] = String.valueOf(out1.NoteEcartRemuneration);

								} //

								if (out1.NoteEcartTauxPromotion != null) { //
									row_tLogRow_1[11] = String.valueOf(out1.NoteEcartTauxPromotion);

								} //

								if (out1.NoteHautesRemunerations != null) { //
									row_tLogRow_1[12] = String.valueOf(out1.NoteHautesRemunerations);

								} //

								if (out1.NoteIndex != null) { //
									row_tLogRow_1[13] = String.valueOf(out1.NoteIndex);

								} //

								util_tLogRow_1.addRow(row_tLogRow_1);
								nb_line_tLogRow_1++;
//////

//////                    

///////////////////////    			

								row2 = out1;

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
								 * [tReplicate_1 main ] start
								 */

								currentComponent = "tReplicate_1";

								if (execStat) {
									runStat.updateStatOnConnection(iterateId, 1, 1

											, "row2"

									);
								}

								row4 = new row4Struct();

								row4.Id = row2.Id;
								row4.Annee = row2.Annee;
								row4.Structure = row2.Structure;
								row4.TrancheEffectif = row2.TrancheEffectif;
								row4.SIREN = row2.SIREN;
								row4.RaisonSociale = row2.RaisonSociale;
								row4.Region = row2.Region;
								row4.Departement = row2.Departement;
								row4.Pays = row2.Pays;
								row4.CodeNAF = row2.CodeNAF;
								row4.NoteEcartRemuneration = row2.NoteEcartRemuneration;
								row4.NoteEcartTauxPromotion = row2.NoteEcartTauxPromotion;
								row4.NoteHautesRemunerations = row2.NoteHautesRemunerations;
								row4.NoteIndex = row2.NoteIndex;
								row3 = new row3Struct();

								row3.Id = row2.Id;
								row3.Annee = row2.Annee;
								row3.Structure = row2.Structure;
								row3.TrancheEffectif = row2.TrancheEffectif;
								row3.SIREN = row2.SIREN;
								row3.RaisonSociale = row2.RaisonSociale;
								row3.Region = row2.Region;
								row3.Departement = row2.Departement;
								row3.Pays = row2.Pays;
								row3.CodeNAF = row2.CodeNAF;
								row3.NoteEcartRemuneration = row2.NoteEcartRemuneration;
								row3.NoteEcartTauxPromotion = row2.NoteEcartTauxPromotion;
								row3.NoteHautesRemunerations = row2.NoteHautesRemunerations;
								row3.NoteIndex = row2.NoteIndex;

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
								if (row4.Structure != null) {
									sb_tFileOutputDelimited_1.append(row4.Structure);
								}
								sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
								if (row4.TrancheEffectif != null) {
									sb_tFileOutputDelimited_1.append(row4.TrancheEffectif);
								}
								sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
								if (row4.SIREN != null) {
									sb_tFileOutputDelimited_1.append(row4.SIREN);
								}
								sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
								if (row4.RaisonSociale != null) {
									sb_tFileOutputDelimited_1.append(row4.RaisonSociale);
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
								if (row4.Pays != null) {
									sb_tFileOutputDelimited_1.append(row4.Pays);
								}
								sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
								if (row4.CodeNAF != null) {
									sb_tFileOutputDelimited_1.append(row4.CodeNAF);
								}
								sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
								if (row4.NoteEcartRemuneration != null) {
									sb_tFileOutputDelimited_1.append(row4.NoteEcartRemuneration);
								}
								sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
								if (row4.NoteEcartTauxPromotion != null) {
									sb_tFileOutputDelimited_1.append(row4.NoteEcartTauxPromotion);
								}
								sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
								if (row4.NoteHautesRemunerations != null) {
									sb_tFileOutputDelimited_1.append(row4.NoteHautesRemunerations);
								}
								sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
								if (row4.NoteIndex != null) {
									sb_tFileOutputDelimited_1.append(row4.NoteIndex);
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

											, "row3"

									);
								}

								whetherReject_tDBOutput_1 = false;
								pstmt_tDBOutput_1.setInt(1, row3.Id);

								if (row3.Annee == null) {
									pstmt_tDBOutput_1.setNull(2, java.sql.Types.INTEGER);
								} else {
									pstmt_tDBOutput_1.setInt(2, row3.Annee);
								}

								if (row3.Structure == null) {
									pstmt_tDBOutput_1.setNull(3, java.sql.Types.VARCHAR);
								} else {
									pstmt_tDBOutput_1.setString(3, row3.Structure);
								}

								if (row3.TrancheEffectif == null) {
									pstmt_tDBOutput_1.setNull(4, java.sql.Types.VARCHAR);
								} else {
									pstmt_tDBOutput_1.setString(4, row3.TrancheEffectif);
								}

								if (row3.SIREN == null) {
									pstmt_tDBOutput_1.setNull(5, java.sql.Types.VARCHAR);
								} else {
									pstmt_tDBOutput_1.setString(5, row3.SIREN);
								}

								if (row3.RaisonSociale == null) {
									pstmt_tDBOutput_1.setNull(6, java.sql.Types.VARCHAR);
								} else {
									pstmt_tDBOutput_1.setString(6, row3.RaisonSociale);
								}

								if (row3.Region == null) {
									pstmt_tDBOutput_1.setNull(7, java.sql.Types.VARCHAR);
								} else {
									pstmt_tDBOutput_1.setString(7, row3.Region);
								}

								if (row3.Departement == null) {
									pstmt_tDBOutput_1.setNull(8, java.sql.Types.VARCHAR);
								} else {
									pstmt_tDBOutput_1.setString(8, row3.Departement);
								}

								if (row3.Pays == null) {
									pstmt_tDBOutput_1.setNull(9, java.sql.Types.VARCHAR);
								} else {
									pstmt_tDBOutput_1.setString(9, row3.Pays);
								}

								if (row3.CodeNAF == null) {
									pstmt_tDBOutput_1.setNull(10, java.sql.Types.VARCHAR);
								} else {
									pstmt_tDBOutput_1.setString(10, row3.CodeNAF);
								}

								if (row3.NoteEcartRemuneration == null) {
									pstmt_tDBOutput_1.setNull(11, java.sql.Types.INTEGER);
								} else {
									pstmt_tDBOutput_1.setInt(11, row3.NoteEcartRemuneration);
								}

								if (row3.NoteEcartTauxPromotion == null) {
									pstmt_tDBOutput_1.setNull(12, java.sql.Types.INTEGER);
								} else {
									pstmt_tDBOutput_1.setInt(12, row3.NoteEcartTauxPromotion);
								}

								if (row3.NoteHautesRemunerations == null) {
									pstmt_tDBOutput_1.setNull(13, java.sql.Types.INTEGER);
								} else {
									pstmt_tDBOutput_1.setInt(13, row3.NoteHautesRemunerations);
								}

								if (row3.NoteIndex == null) {
									pstmt_tDBOutput_1.setNull(14, java.sql.Types.INTEGER);
								} else {
									pstmt_tDBOutput_1.setInt(14, row3.NoteIndex);
								}

								pstmt_tDBOutput_1.addBatch();
								nb_line_tDBOutput_1++;

								batchSizeCounter_tDBOutput_1++;
								if (batchSize_tDBOutput_1 <= batchSizeCounter_tDBOutput_1) {
									try {
										int countSum_tDBOutput_1 = 0;
										for (int countEach_tDBOutput_1 : pstmt_tDBOutput_1.executeBatch()) {
											countSum_tDBOutput_1 += (countEach_tDBOutput_1 == java.sql.Statement.EXECUTE_FAILED
													? 0
													: 1);
										}
										rowsToCommitCount_tDBOutput_1 += countSum_tDBOutput_1;
										insertedCount_tDBOutput_1 += countSum_tDBOutput_1;
									} catch (java.sql.BatchUpdateException e) {
										globalMap.put("tDBOutput_1_ERROR_MESSAGE", e.getMessage());
										int countSum_tDBOutput_1 = 0;
										for (int countEach_tDBOutput_1 : e.getUpdateCounts()) {
											countSum_tDBOutput_1 += (countEach_tDBOutput_1 < 0 ? 0
													: countEach_tDBOutput_1);
										}
										rowsToCommitCount_tDBOutput_1 += countSum_tDBOutput_1;
										insertedCount_tDBOutput_1 += countSum_tDBOutput_1;
										System.err.println(e.getMessage());
									}

									batchSizeCounter_tDBOutput_1 = 0;
								}
								commitCounter_tDBOutput_1++;

								if (commitEvery_tDBOutput_1 <= commitCounter_tDBOutput_1) {

									try {
										int countSum_tDBOutput_1 = 0;
										for (int countEach_tDBOutput_1 : pstmt_tDBOutput_1.executeBatch()) {
											countSum_tDBOutput_1 += (countEach_tDBOutput_1 < 0 ? 0 : 1);
										}
										rowsToCommitCount_tDBOutput_1 += countSum_tDBOutput_1;
										insertedCount_tDBOutput_1 += countSum_tDBOutput_1;
									} catch (java.sql.BatchUpdateException e) {
										globalMap.put("tDBOutput_1_ERROR_MESSAGE", e.getMessage());
										int countSum_tDBOutput_1 = 0;
										for (int countEach_tDBOutput_1 : e.getUpdateCounts()) {
											countSum_tDBOutput_1 += (countEach_tDBOutput_1 < 0 ? 0
													: countEach_tDBOutput_1);
										}
										rowsToCommitCount_tDBOutput_1 += countSum_tDBOutput_1;
										insertedCount_tDBOutput_1 += countSum_tDBOutput_1;
										System.err.println(e.getMessage());

									}
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

								/**
								 * [tLogRow_1 process_data_end ] start
								 */

								currentComponent = "tLogRow_1";

								/**
								 * [tLogRow_1 process_data_end ] stop
								 */

							} // End of branch "out1"

							/**
							 * [tMap_1 process_data_end ] start
							 */

							currentComponent = "tMap_1";

							/**
							 * [tMap_1 process_data_end ] stop
							 */

						} // End of branch "row1"

						/**
						 * [tFileInputExcel_1 process_data_end ] start
						 */

						currentComponent = "tFileInputExcel_1";

						/**
						 * [tFileInputExcel_1 process_data_end ] stop
						 */

						/**
						 * [tFileInputExcel_1 end ] start
						 */

						currentComponent = "tFileInputExcel_1";

					}

					try {
						if (excelReader_tFileInputExcel_1 != null) {
							excelReader_tFileInputExcel_1.handleException();
						}
					} catch (java.lang.Exception e_tFileInputExcel_1) {
						globalMap.put("tFileInputExcel_1_ERROR_MESSAGE", e_tFileInputExcel_1.getMessage());
						if (!(e_tFileInputExcel_1
								.getCause() instanceof com.talend.excel.xssf.event.EnoughDataException)) {

							System.err.println(e_tFileInputExcel_1.getMessage());

						}
					}

					globalMap.put("tFileInputExcel_1_NB_LINE", nb_line_tFileInputExcel_1);

				} finally {

				}

				ok_Hash.put("tFileInputExcel_1", true);
				end_Hash.put("tFileInputExcel_1", System.currentTimeMillis());

				/**
				 * [tFileInputExcel_1 end ] stop
				 */

				/**
				 * [tMap_1 end ] start
				 */

				currentComponent = "tMap_1";

// ###############################
// # Lookup hashes releasing
// ###############################      

				if (execStat) {
					runStat.updateStat(resourceMap, iterateId, 2, 0, "row1");
				}

				ok_Hash.put("tMap_1", true);
				end_Hash.put("tMap_1", System.currentTimeMillis());

				/**
				 * [tMap_1 end ] stop
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
					runStat.updateStat(resourceMap, iterateId, 2, 0, "out1");
				}

				ok_Hash.put("tLogRow_1", true);
				end_Hash.put("tLogRow_1", System.currentTimeMillis());

				/**
				 * [tLogRow_1 end ] stop
				 */

				/**
				 * [tReplicate_1 end ] start
				 */

				currentComponent = "tReplicate_1";

				if (execStat) {
					runStat.updateStat(resourceMap, iterateId, 2, 0, "row2");
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

				try {
					if (batchSizeCounter_tDBOutput_1 != 0) {
						int countSum_tDBOutput_1 = 0;

						for (int countEach_tDBOutput_1 : pstmt_tDBOutput_1.executeBatch()) {
							countSum_tDBOutput_1 += (countEach_tDBOutput_1 == java.sql.Statement.EXECUTE_FAILED ? 0
									: 1);
						}
						rowsToCommitCount_tDBOutput_1 += countSum_tDBOutput_1;

						insertedCount_tDBOutput_1 += countSum_tDBOutput_1;

					}

				} catch (java.sql.BatchUpdateException e) {
					globalMap.put(currentComponent + "_ERROR_MESSAGE", e.getMessage());

					int countSum_tDBOutput_1 = 0;
					for (int countEach_tDBOutput_1 : e.getUpdateCounts()) {
						countSum_tDBOutput_1 += (countEach_tDBOutput_1 < 0 ? 0 : countEach_tDBOutput_1);
					}
					rowsToCommitCount_tDBOutput_1 += countSum_tDBOutput_1;

					insertedCount_tDBOutput_1 += countSum_tDBOutput_1;

					System.err.println(e.getMessage());

				}
				batchSizeCounter_tDBOutput_1 = 0;

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
					runStat.updateStat(resourceMap, iterateId, 2, 0, "row3");
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
				 * [tFileInputExcel_1 finally ] start
				 */

				currentComponent = "tFileInputExcel_1";

				/**
				 * [tFileInputExcel_1 finally ] stop
				 */

				/**
				 * [tMap_1 finally ] start
				 */

				currentComponent = "tMap_1";

				/**
				 * [tMap_1 finally ] stop
				 */

				/**
				 * [tLogRow_1 finally ] start
				 */

				currentComponent = "tLogRow_1";

				/**
				 * [tLogRow_1 finally ] stop
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

		globalMap.put("tFileInputExcel_1_SUBPROCESS_STATE", 1);
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
		final EgaliteHF EgaliteHFClass = new EgaliteHF();

		int exitCode = EgaliteHFClass.runJobInTOS(args);

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
			java.io.InputStream inContext = EgaliteHF.class.getClassLoader()
					.getResourceAsStream("datamspr/egalitehf_0_1/contexts/" + contextStr + ".properties");
			if (inContext == null) {
				inContext = EgaliteHF.class.getClassLoader()
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
			tFileInputExcel_1Process(globalMap);
			if (!"failure".equals(status)) {
				status = "end";
			}
		} catch (TalendException e_tFileInputExcel_1) {
			globalMap.put("tFileInputExcel_1_SUBPROCESS_STATE", -1);

			e_tFileInputExcel_1.printStackTrace();

		}

		this.globalResumeTicket = true;// to run tPostJob

		end = System.currentTimeMillis();

		if (watch) {
			System.out.println((end - startTime) + " milliseconds");
		}

		endUsedMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
		if (false) {
			System.out.println((endUsedMemory - startUsedMemory) + " bytes memory increase when running : EgaliteHF");
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
 * 159280 characters generated by Talend Open Studio for Data Integration on the
 * 15 avril 2024, 17:48:44 CEST
 ************************************************************************************************/