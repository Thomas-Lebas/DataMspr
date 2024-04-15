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

package datamspr.mariage_0_1;

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
 * Job: Mariage Purpose: <br>
 * Description: <br>
 * 
 * @author user@talend.com
 * @version 8.0.1.20211109_1610
 * @status
 */
public class Mariage implements TalendJob {

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
	private final String jobName = "Mariage";
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
					Mariage.this.exception = e;
				}
			}
			if (!(e instanceof TalendException)) {
				try {
					for (java.lang.reflect.Method m : this.getClass().getEnclosingClass().getMethods()) {
						if (m.getName().compareTo(currentComponent + "_error") == 0) {
							m.invoke(Mariage.this, new Object[] { e, currentComponent, globalMap });
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

	public void tMap_1_error(Exception exception, String errorComponent, final java.util.Map<String, Object> globalMap)
			throws TalendException {

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
		final static byte[] commonByteArrayLock_DATAMSPR_Mariage = new byte[0];
		static byte[] commonByteArray_DATAMSPR_Mariage = new byte[0];
		protected static final int DEFAULT_HASHCODE = 1;
		protected static final int PRIME = 31;
		protected int hashCode = DEFAULT_HASHCODE;
		public boolean hashCodeDirty = true;

		public String loopKey;

		public int Id;

		public int getId() {
			return this.Id;
		}

		public Integer AnneeMariage;

		public Integer getAnneeMariage() {
			return this.AnneeMariage;
		}

		public Integer AnneeNaissanceMere;

		public Integer getAnneeNaissanceMere() {
			return this.AnneeNaissanceMere;
		}

		public Integer AnneeNaissancePere;

		public Integer getAnneeNaissancePere() {
			return this.AnneeNaissancePere;
		}

		public String DepartementDomicleApresMariage;

		public String getDepartementDomicleApresMariage() {
			return this.DepartementDomicleApresMariage;
		}

		public String DepartementMariage;

		public String getDepartementMariage() {
			return this.DepartementMariage;
		}

		public String DepartementNaissanceConjoint1;

		public String getDepartementNaissanceConjoint1() {
			return this.DepartementNaissanceConjoint1;
		}

		public String DepartementNaissanceConjoint2;

		public String getDepartementNaissanceConjoint2() {
			return this.DepartementNaissanceConjoint2;
		}

		public String EtatMatrimonialAnterieurConjoint1;

		public String getEtatMatrimonialAnterieurConjoint1() {
			return this.EtatMatrimonialAnterieurConjoint1;
		}

		public String EtatMatrimonialAnterieurConjoint2;

		public String getEtatMatrimonialAnterieurConjoint2() {
			return this.EtatMatrimonialAnterieurConjoint2;
		}

		public String IndicateurNationaliteConjoint1;

		public String getIndicateurNationaliteConjoint1() {
			return this.IndicateurNationaliteConjoint1;
		}

		public String IndicateurNationaliteConjoint2;

		public String getIndicateurNationaliteConjoint2() {
			return this.IndicateurNationaliteConjoint2;
		}

		public String JourMariage;

		public String getJourMariage() {
			return this.JourMariage;
		}

		public Integer MoisMariage;

		public Integer getMoisMariage() {
			return this.MoisMariage;
		}

		public String EnfantCommunMariage;

		public String getEnfantCommunMariage() {
			return this.EnfantCommunMariage;
		}

		public String SexeConjoint1;

		public String getSexeConjoint1() {
			return this.SexeConjoint1;
		}

		public String SexeConjoint2;

		public String getSexeConjoint2() {
			return this.SexeConjoint2;
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
			other.AnneeMariage = this.AnneeMariage;
			other.AnneeNaissanceMere = this.AnneeNaissanceMere;
			other.AnneeNaissancePere = this.AnneeNaissancePere;
			other.DepartementDomicleApresMariage = this.DepartementDomicleApresMariage;
			other.DepartementMariage = this.DepartementMariage;
			other.DepartementNaissanceConjoint1 = this.DepartementNaissanceConjoint1;
			other.DepartementNaissanceConjoint2 = this.DepartementNaissanceConjoint2;
			other.EtatMatrimonialAnterieurConjoint1 = this.EtatMatrimonialAnterieurConjoint1;
			other.EtatMatrimonialAnterieurConjoint2 = this.EtatMatrimonialAnterieurConjoint2;
			other.IndicateurNationaliteConjoint1 = this.IndicateurNationaliteConjoint1;
			other.IndicateurNationaliteConjoint2 = this.IndicateurNationaliteConjoint2;
			other.JourMariage = this.JourMariage;
			other.MoisMariage = this.MoisMariage;
			other.EnfantCommunMariage = this.EnfantCommunMariage;
			other.SexeConjoint1 = this.SexeConjoint1;
			other.SexeConjoint2 = this.SexeConjoint2;

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
				if (length > commonByteArray_DATAMSPR_Mariage.length) {
					if (length < 1024 && commonByteArray_DATAMSPR_Mariage.length == 0) {
						commonByteArray_DATAMSPR_Mariage = new byte[1024];
					} else {
						commonByteArray_DATAMSPR_Mariage = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_DATAMSPR_Mariage, 0, length);
				strReturn = new String(commonByteArray_DATAMSPR_Mariage, 0, length, utf8Charset);
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
				if (length > commonByteArray_DATAMSPR_Mariage.length) {
					if (length < 1024 && commonByteArray_DATAMSPR_Mariage.length == 0) {
						commonByteArray_DATAMSPR_Mariage = new byte[1024];
					} else {
						commonByteArray_DATAMSPR_Mariage = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_DATAMSPR_Mariage, 0, length);
				strReturn = new String(commonByteArray_DATAMSPR_Mariage, 0, length, utf8Charset);
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

			synchronized (commonByteArrayLock_DATAMSPR_Mariage) {

				try {

					int length = 0;

					this.Id = dis.readInt();

					this.AnneeMariage = readInteger(dis);

					this.AnneeNaissanceMere = readInteger(dis);

					this.AnneeNaissancePere = readInteger(dis);

					this.DepartementDomicleApresMariage = readString(dis);

					this.DepartementMariage = readString(dis);

					this.DepartementNaissanceConjoint1 = readString(dis);

					this.DepartementNaissanceConjoint2 = readString(dis);

					this.EtatMatrimonialAnterieurConjoint1 = readString(dis);

					this.EtatMatrimonialAnterieurConjoint2 = readString(dis);

					this.IndicateurNationaliteConjoint1 = readString(dis);

					this.IndicateurNationaliteConjoint2 = readString(dis);

					this.JourMariage = readString(dis);

					this.MoisMariage = readInteger(dis);

					this.EnfantCommunMariage = readString(dis);

					this.SexeConjoint1 = readString(dis);

					this.SexeConjoint2 = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_DATAMSPR_Mariage) {

				try {

					int length = 0;

					this.Id = dis.readInt();

					this.AnneeMariage = readInteger(dis);

					this.AnneeNaissanceMere = readInteger(dis);

					this.AnneeNaissancePere = readInteger(dis);

					this.DepartementDomicleApresMariage = readString(dis);

					this.DepartementMariage = readString(dis);

					this.DepartementNaissanceConjoint1 = readString(dis);

					this.DepartementNaissanceConjoint2 = readString(dis);

					this.EtatMatrimonialAnterieurConjoint1 = readString(dis);

					this.EtatMatrimonialAnterieurConjoint2 = readString(dis);

					this.IndicateurNationaliteConjoint1 = readString(dis);

					this.IndicateurNationaliteConjoint2 = readString(dis);

					this.JourMariage = readString(dis);

					this.MoisMariage = readInteger(dis);

					this.EnfantCommunMariage = readString(dis);

					this.SexeConjoint1 = readString(dis);

					this.SexeConjoint2 = readString(dis);

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

				writeInteger(this.AnneeMariage, dos);

				// Integer

				writeInteger(this.AnneeNaissanceMere, dos);

				// Integer

				writeInteger(this.AnneeNaissancePere, dos);

				// String

				writeString(this.DepartementDomicleApresMariage, dos);

				// String

				writeString(this.DepartementMariage, dos);

				// String

				writeString(this.DepartementNaissanceConjoint1, dos);

				// String

				writeString(this.DepartementNaissanceConjoint2, dos);

				// String

				writeString(this.EtatMatrimonialAnterieurConjoint1, dos);

				// String

				writeString(this.EtatMatrimonialAnterieurConjoint2, dos);

				// String

				writeString(this.IndicateurNationaliteConjoint1, dos);

				// String

				writeString(this.IndicateurNationaliteConjoint2, dos);

				// String

				writeString(this.JourMariage, dos);

				// Integer

				writeInteger(this.MoisMariage, dos);

				// String

				writeString(this.EnfantCommunMariage, dos);

				// String

				writeString(this.SexeConjoint1, dos);

				// String

				writeString(this.SexeConjoint2, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// int

				dos.writeInt(this.Id);

				// Integer

				writeInteger(this.AnneeMariage, dos);

				// Integer

				writeInteger(this.AnneeNaissanceMere, dos);

				// Integer

				writeInteger(this.AnneeNaissancePere, dos);

				// String

				writeString(this.DepartementDomicleApresMariage, dos);

				// String

				writeString(this.DepartementMariage, dos);

				// String

				writeString(this.DepartementNaissanceConjoint1, dos);

				// String

				writeString(this.DepartementNaissanceConjoint2, dos);

				// String

				writeString(this.EtatMatrimonialAnterieurConjoint1, dos);

				// String

				writeString(this.EtatMatrimonialAnterieurConjoint2, dos);

				// String

				writeString(this.IndicateurNationaliteConjoint1, dos);

				// String

				writeString(this.IndicateurNationaliteConjoint2, dos);

				// String

				writeString(this.JourMariage, dos);

				// Integer

				writeInteger(this.MoisMariage, dos);

				// String

				writeString(this.EnfantCommunMariage, dos);

				// String

				writeString(this.SexeConjoint1, dos);

				// String

				writeString(this.SexeConjoint2, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("Id=" + String.valueOf(Id));
			sb.append(",AnneeMariage=" + String.valueOf(AnneeMariage));
			sb.append(",AnneeNaissanceMere=" + String.valueOf(AnneeNaissanceMere));
			sb.append(",AnneeNaissancePere=" + String.valueOf(AnneeNaissancePere));
			sb.append(",DepartementDomicleApresMariage=" + DepartementDomicleApresMariage);
			sb.append(",DepartementMariage=" + DepartementMariage);
			sb.append(",DepartementNaissanceConjoint1=" + DepartementNaissanceConjoint1);
			sb.append(",DepartementNaissanceConjoint2=" + DepartementNaissanceConjoint2);
			sb.append(",EtatMatrimonialAnterieurConjoint1=" + EtatMatrimonialAnterieurConjoint1);
			sb.append(",EtatMatrimonialAnterieurConjoint2=" + EtatMatrimonialAnterieurConjoint2);
			sb.append(",IndicateurNationaliteConjoint1=" + IndicateurNationaliteConjoint1);
			sb.append(",IndicateurNationaliteConjoint2=" + IndicateurNationaliteConjoint2);
			sb.append(",JourMariage=" + JourMariage);
			sb.append(",MoisMariage=" + String.valueOf(MoisMariage));
			sb.append(",EnfantCommunMariage=" + EnfantCommunMariage);
			sb.append(",SexeConjoint1=" + SexeConjoint1);
			sb.append(",SexeConjoint2=" + SexeConjoint2);
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
		final static byte[] commonByteArrayLock_DATAMSPR_Mariage = new byte[0];
		static byte[] commonByteArray_DATAMSPR_Mariage = new byte[0];
		protected static final int DEFAULT_HASHCODE = 1;
		protected static final int PRIME = 31;
		protected int hashCode = DEFAULT_HASHCODE;
		public boolean hashCodeDirty = true;

		public String loopKey;

		public int Id;

		public int getId() {
			return this.Id;
		}

		public Integer AnneeMariage;

		public Integer getAnneeMariage() {
			return this.AnneeMariage;
		}

		public Integer AnneeNaissanceMere;

		public Integer getAnneeNaissanceMere() {
			return this.AnneeNaissanceMere;
		}

		public Integer AnneeNaissancePere;

		public Integer getAnneeNaissancePere() {
			return this.AnneeNaissancePere;
		}

		public String DepartementDomicleApresMariage;

		public String getDepartementDomicleApresMariage() {
			return this.DepartementDomicleApresMariage;
		}

		public String DepartementMariage;

		public String getDepartementMariage() {
			return this.DepartementMariage;
		}

		public String DepartementNaissanceConjoint1;

		public String getDepartementNaissanceConjoint1() {
			return this.DepartementNaissanceConjoint1;
		}

		public String DepartementNaissanceConjoint2;

		public String getDepartementNaissanceConjoint2() {
			return this.DepartementNaissanceConjoint2;
		}

		public String EtatMatrimonialAnterieurConjoint1;

		public String getEtatMatrimonialAnterieurConjoint1() {
			return this.EtatMatrimonialAnterieurConjoint1;
		}

		public String EtatMatrimonialAnterieurConjoint2;

		public String getEtatMatrimonialAnterieurConjoint2() {
			return this.EtatMatrimonialAnterieurConjoint2;
		}

		public String IndicateurNationaliteConjoint1;

		public String getIndicateurNationaliteConjoint1() {
			return this.IndicateurNationaliteConjoint1;
		}

		public String IndicateurNationaliteConjoint2;

		public String getIndicateurNationaliteConjoint2() {
			return this.IndicateurNationaliteConjoint2;
		}

		public String JourMariage;

		public String getJourMariage() {
			return this.JourMariage;
		}

		public Integer MoisMariage;

		public Integer getMoisMariage() {
			return this.MoisMariage;
		}

		public String EnfantCommunMariage;

		public String getEnfantCommunMariage() {
			return this.EnfantCommunMariage;
		}

		public String SexeConjoint1;

		public String getSexeConjoint1() {
			return this.SexeConjoint1;
		}

		public String SexeConjoint2;

		public String getSexeConjoint2() {
			return this.SexeConjoint2;
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
			other.AnneeMariage = this.AnneeMariage;
			other.AnneeNaissanceMere = this.AnneeNaissanceMere;
			other.AnneeNaissancePere = this.AnneeNaissancePere;
			other.DepartementDomicleApresMariage = this.DepartementDomicleApresMariage;
			other.DepartementMariage = this.DepartementMariage;
			other.DepartementNaissanceConjoint1 = this.DepartementNaissanceConjoint1;
			other.DepartementNaissanceConjoint2 = this.DepartementNaissanceConjoint2;
			other.EtatMatrimonialAnterieurConjoint1 = this.EtatMatrimonialAnterieurConjoint1;
			other.EtatMatrimonialAnterieurConjoint2 = this.EtatMatrimonialAnterieurConjoint2;
			other.IndicateurNationaliteConjoint1 = this.IndicateurNationaliteConjoint1;
			other.IndicateurNationaliteConjoint2 = this.IndicateurNationaliteConjoint2;
			other.JourMariage = this.JourMariage;
			other.MoisMariage = this.MoisMariage;
			other.EnfantCommunMariage = this.EnfantCommunMariage;
			other.SexeConjoint1 = this.SexeConjoint1;
			other.SexeConjoint2 = this.SexeConjoint2;

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
				if (length > commonByteArray_DATAMSPR_Mariage.length) {
					if (length < 1024 && commonByteArray_DATAMSPR_Mariage.length == 0) {
						commonByteArray_DATAMSPR_Mariage = new byte[1024];
					} else {
						commonByteArray_DATAMSPR_Mariage = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_DATAMSPR_Mariage, 0, length);
				strReturn = new String(commonByteArray_DATAMSPR_Mariage, 0, length, utf8Charset);
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
				if (length > commonByteArray_DATAMSPR_Mariage.length) {
					if (length < 1024 && commonByteArray_DATAMSPR_Mariage.length == 0) {
						commonByteArray_DATAMSPR_Mariage = new byte[1024];
					} else {
						commonByteArray_DATAMSPR_Mariage = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_DATAMSPR_Mariage, 0, length);
				strReturn = new String(commonByteArray_DATAMSPR_Mariage, 0, length, utf8Charset);
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

			synchronized (commonByteArrayLock_DATAMSPR_Mariage) {

				try {

					int length = 0;

					this.Id = dis.readInt();

					this.AnneeMariage = readInteger(dis);

					this.AnneeNaissanceMere = readInteger(dis);

					this.AnneeNaissancePere = readInteger(dis);

					this.DepartementDomicleApresMariage = readString(dis);

					this.DepartementMariage = readString(dis);

					this.DepartementNaissanceConjoint1 = readString(dis);

					this.DepartementNaissanceConjoint2 = readString(dis);

					this.EtatMatrimonialAnterieurConjoint1 = readString(dis);

					this.EtatMatrimonialAnterieurConjoint2 = readString(dis);

					this.IndicateurNationaliteConjoint1 = readString(dis);

					this.IndicateurNationaliteConjoint2 = readString(dis);

					this.JourMariage = readString(dis);

					this.MoisMariage = readInteger(dis);

					this.EnfantCommunMariage = readString(dis);

					this.SexeConjoint1 = readString(dis);

					this.SexeConjoint2 = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_DATAMSPR_Mariage) {

				try {

					int length = 0;

					this.Id = dis.readInt();

					this.AnneeMariage = readInteger(dis);

					this.AnneeNaissanceMere = readInteger(dis);

					this.AnneeNaissancePere = readInteger(dis);

					this.DepartementDomicleApresMariage = readString(dis);

					this.DepartementMariage = readString(dis);

					this.DepartementNaissanceConjoint1 = readString(dis);

					this.DepartementNaissanceConjoint2 = readString(dis);

					this.EtatMatrimonialAnterieurConjoint1 = readString(dis);

					this.EtatMatrimonialAnterieurConjoint2 = readString(dis);

					this.IndicateurNationaliteConjoint1 = readString(dis);

					this.IndicateurNationaliteConjoint2 = readString(dis);

					this.JourMariage = readString(dis);

					this.MoisMariage = readInteger(dis);

					this.EnfantCommunMariage = readString(dis);

					this.SexeConjoint1 = readString(dis);

					this.SexeConjoint2 = readString(dis);

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

				writeInteger(this.AnneeMariage, dos);

				// Integer

				writeInteger(this.AnneeNaissanceMere, dos);

				// Integer

				writeInteger(this.AnneeNaissancePere, dos);

				// String

				writeString(this.DepartementDomicleApresMariage, dos);

				// String

				writeString(this.DepartementMariage, dos);

				// String

				writeString(this.DepartementNaissanceConjoint1, dos);

				// String

				writeString(this.DepartementNaissanceConjoint2, dos);

				// String

				writeString(this.EtatMatrimonialAnterieurConjoint1, dos);

				// String

				writeString(this.EtatMatrimonialAnterieurConjoint2, dos);

				// String

				writeString(this.IndicateurNationaliteConjoint1, dos);

				// String

				writeString(this.IndicateurNationaliteConjoint2, dos);

				// String

				writeString(this.JourMariage, dos);

				// Integer

				writeInteger(this.MoisMariage, dos);

				// String

				writeString(this.EnfantCommunMariage, dos);

				// String

				writeString(this.SexeConjoint1, dos);

				// String

				writeString(this.SexeConjoint2, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// int

				dos.writeInt(this.Id);

				// Integer

				writeInteger(this.AnneeMariage, dos);

				// Integer

				writeInteger(this.AnneeNaissanceMere, dos);

				// Integer

				writeInteger(this.AnneeNaissancePere, dos);

				// String

				writeString(this.DepartementDomicleApresMariage, dos);

				// String

				writeString(this.DepartementMariage, dos);

				// String

				writeString(this.DepartementNaissanceConjoint1, dos);

				// String

				writeString(this.DepartementNaissanceConjoint2, dos);

				// String

				writeString(this.EtatMatrimonialAnterieurConjoint1, dos);

				// String

				writeString(this.EtatMatrimonialAnterieurConjoint2, dos);

				// String

				writeString(this.IndicateurNationaliteConjoint1, dos);

				// String

				writeString(this.IndicateurNationaliteConjoint2, dos);

				// String

				writeString(this.JourMariage, dos);

				// Integer

				writeInteger(this.MoisMariage, dos);

				// String

				writeString(this.EnfantCommunMariage, dos);

				// String

				writeString(this.SexeConjoint1, dos);

				// String

				writeString(this.SexeConjoint2, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("Id=" + String.valueOf(Id));
			sb.append(",AnneeMariage=" + String.valueOf(AnneeMariage));
			sb.append(",AnneeNaissanceMere=" + String.valueOf(AnneeNaissanceMere));
			sb.append(",AnneeNaissancePere=" + String.valueOf(AnneeNaissancePere));
			sb.append(",DepartementDomicleApresMariage=" + DepartementDomicleApresMariage);
			sb.append(",DepartementMariage=" + DepartementMariage);
			sb.append(",DepartementNaissanceConjoint1=" + DepartementNaissanceConjoint1);
			sb.append(",DepartementNaissanceConjoint2=" + DepartementNaissanceConjoint2);
			sb.append(",EtatMatrimonialAnterieurConjoint1=" + EtatMatrimonialAnterieurConjoint1);
			sb.append(",EtatMatrimonialAnterieurConjoint2=" + EtatMatrimonialAnterieurConjoint2);
			sb.append(",IndicateurNationaliteConjoint1=" + IndicateurNationaliteConjoint1);
			sb.append(",IndicateurNationaliteConjoint2=" + IndicateurNationaliteConjoint2);
			sb.append(",JourMariage=" + JourMariage);
			sb.append(",MoisMariage=" + String.valueOf(MoisMariage));
			sb.append(",EnfantCommunMariage=" + EnfantCommunMariage);
			sb.append(",SexeConjoint1=" + SexeConjoint1);
			sb.append(",SexeConjoint2=" + SexeConjoint2);
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
		final static byte[] commonByteArrayLock_DATAMSPR_Mariage = new byte[0];
		static byte[] commonByteArray_DATAMSPR_Mariage = new byte[0];
		protected static final int DEFAULT_HASHCODE = 1;
		protected static final int PRIME = 31;
		protected int hashCode = DEFAULT_HASHCODE;
		public boolean hashCodeDirty = true;

		public String loopKey;

		public int Id;

		public int getId() {
			return this.Id;
		}

		public Integer AnneeMariage;

		public Integer getAnneeMariage() {
			return this.AnneeMariage;
		}

		public Integer AnneeNaissanceMere;

		public Integer getAnneeNaissanceMere() {
			return this.AnneeNaissanceMere;
		}

		public Integer AnneeNaissancePere;

		public Integer getAnneeNaissancePere() {
			return this.AnneeNaissancePere;
		}

		public String DepartementDomicleApresMariage;

		public String getDepartementDomicleApresMariage() {
			return this.DepartementDomicleApresMariage;
		}

		public String DepartementMariage;

		public String getDepartementMariage() {
			return this.DepartementMariage;
		}

		public String DepartementNaissanceConjoint1;

		public String getDepartementNaissanceConjoint1() {
			return this.DepartementNaissanceConjoint1;
		}

		public String DepartementNaissanceConjoint2;

		public String getDepartementNaissanceConjoint2() {
			return this.DepartementNaissanceConjoint2;
		}

		public String EtatMatrimonialAnterieurConjoint1;

		public String getEtatMatrimonialAnterieurConjoint1() {
			return this.EtatMatrimonialAnterieurConjoint1;
		}

		public String EtatMatrimonialAnterieurConjoint2;

		public String getEtatMatrimonialAnterieurConjoint2() {
			return this.EtatMatrimonialAnterieurConjoint2;
		}

		public String IndicateurNationaliteConjoint1;

		public String getIndicateurNationaliteConjoint1() {
			return this.IndicateurNationaliteConjoint1;
		}

		public String IndicateurNationaliteConjoint2;

		public String getIndicateurNationaliteConjoint2() {
			return this.IndicateurNationaliteConjoint2;
		}

		public String JourMariage;

		public String getJourMariage() {
			return this.JourMariage;
		}

		public Integer MoisMariage;

		public Integer getMoisMariage() {
			return this.MoisMariage;
		}

		public String EnfantCommunMariage;

		public String getEnfantCommunMariage() {
			return this.EnfantCommunMariage;
		}

		public String SexeConjoint1;

		public String getSexeConjoint1() {
			return this.SexeConjoint1;
		}

		public String SexeConjoint2;

		public String getSexeConjoint2() {
			return this.SexeConjoint2;
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
			other.AnneeMariage = this.AnneeMariage;
			other.AnneeNaissanceMere = this.AnneeNaissanceMere;
			other.AnneeNaissancePere = this.AnneeNaissancePere;
			other.DepartementDomicleApresMariage = this.DepartementDomicleApresMariage;
			other.DepartementMariage = this.DepartementMariage;
			other.DepartementNaissanceConjoint1 = this.DepartementNaissanceConjoint1;
			other.DepartementNaissanceConjoint2 = this.DepartementNaissanceConjoint2;
			other.EtatMatrimonialAnterieurConjoint1 = this.EtatMatrimonialAnterieurConjoint1;
			other.EtatMatrimonialAnterieurConjoint2 = this.EtatMatrimonialAnterieurConjoint2;
			other.IndicateurNationaliteConjoint1 = this.IndicateurNationaliteConjoint1;
			other.IndicateurNationaliteConjoint2 = this.IndicateurNationaliteConjoint2;
			other.JourMariage = this.JourMariage;
			other.MoisMariage = this.MoisMariage;
			other.EnfantCommunMariage = this.EnfantCommunMariage;
			other.SexeConjoint1 = this.SexeConjoint1;
			other.SexeConjoint2 = this.SexeConjoint2;

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
				if (length > commonByteArray_DATAMSPR_Mariage.length) {
					if (length < 1024 && commonByteArray_DATAMSPR_Mariage.length == 0) {
						commonByteArray_DATAMSPR_Mariage = new byte[1024];
					} else {
						commonByteArray_DATAMSPR_Mariage = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_DATAMSPR_Mariage, 0, length);
				strReturn = new String(commonByteArray_DATAMSPR_Mariage, 0, length, utf8Charset);
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
				if (length > commonByteArray_DATAMSPR_Mariage.length) {
					if (length < 1024 && commonByteArray_DATAMSPR_Mariage.length == 0) {
						commonByteArray_DATAMSPR_Mariage = new byte[1024];
					} else {
						commonByteArray_DATAMSPR_Mariage = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_DATAMSPR_Mariage, 0, length);
				strReturn = new String(commonByteArray_DATAMSPR_Mariage, 0, length, utf8Charset);
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

			synchronized (commonByteArrayLock_DATAMSPR_Mariage) {

				try {

					int length = 0;

					this.Id = dis.readInt();

					this.AnneeMariage = readInteger(dis);

					this.AnneeNaissanceMere = readInteger(dis);

					this.AnneeNaissancePere = readInteger(dis);

					this.DepartementDomicleApresMariage = readString(dis);

					this.DepartementMariage = readString(dis);

					this.DepartementNaissanceConjoint1 = readString(dis);

					this.DepartementNaissanceConjoint2 = readString(dis);

					this.EtatMatrimonialAnterieurConjoint1 = readString(dis);

					this.EtatMatrimonialAnterieurConjoint2 = readString(dis);

					this.IndicateurNationaliteConjoint1 = readString(dis);

					this.IndicateurNationaliteConjoint2 = readString(dis);

					this.JourMariage = readString(dis);

					this.MoisMariage = readInteger(dis);

					this.EnfantCommunMariage = readString(dis);

					this.SexeConjoint1 = readString(dis);

					this.SexeConjoint2 = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_DATAMSPR_Mariage) {

				try {

					int length = 0;

					this.Id = dis.readInt();

					this.AnneeMariage = readInteger(dis);

					this.AnneeNaissanceMere = readInteger(dis);

					this.AnneeNaissancePere = readInteger(dis);

					this.DepartementDomicleApresMariage = readString(dis);

					this.DepartementMariage = readString(dis);

					this.DepartementNaissanceConjoint1 = readString(dis);

					this.DepartementNaissanceConjoint2 = readString(dis);

					this.EtatMatrimonialAnterieurConjoint1 = readString(dis);

					this.EtatMatrimonialAnterieurConjoint2 = readString(dis);

					this.IndicateurNationaliteConjoint1 = readString(dis);

					this.IndicateurNationaliteConjoint2 = readString(dis);

					this.JourMariage = readString(dis);

					this.MoisMariage = readInteger(dis);

					this.EnfantCommunMariage = readString(dis);

					this.SexeConjoint1 = readString(dis);

					this.SexeConjoint2 = readString(dis);

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

				writeInteger(this.AnneeMariage, dos);

				// Integer

				writeInteger(this.AnneeNaissanceMere, dos);

				// Integer

				writeInteger(this.AnneeNaissancePere, dos);

				// String

				writeString(this.DepartementDomicleApresMariage, dos);

				// String

				writeString(this.DepartementMariage, dos);

				// String

				writeString(this.DepartementNaissanceConjoint1, dos);

				// String

				writeString(this.DepartementNaissanceConjoint2, dos);

				// String

				writeString(this.EtatMatrimonialAnterieurConjoint1, dos);

				// String

				writeString(this.EtatMatrimonialAnterieurConjoint2, dos);

				// String

				writeString(this.IndicateurNationaliteConjoint1, dos);

				// String

				writeString(this.IndicateurNationaliteConjoint2, dos);

				// String

				writeString(this.JourMariage, dos);

				// Integer

				writeInteger(this.MoisMariage, dos);

				// String

				writeString(this.EnfantCommunMariage, dos);

				// String

				writeString(this.SexeConjoint1, dos);

				// String

				writeString(this.SexeConjoint2, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// int

				dos.writeInt(this.Id);

				// Integer

				writeInteger(this.AnneeMariage, dos);

				// Integer

				writeInteger(this.AnneeNaissanceMere, dos);

				// Integer

				writeInteger(this.AnneeNaissancePere, dos);

				// String

				writeString(this.DepartementDomicleApresMariage, dos);

				// String

				writeString(this.DepartementMariage, dos);

				// String

				writeString(this.DepartementNaissanceConjoint1, dos);

				// String

				writeString(this.DepartementNaissanceConjoint2, dos);

				// String

				writeString(this.EtatMatrimonialAnterieurConjoint1, dos);

				// String

				writeString(this.EtatMatrimonialAnterieurConjoint2, dos);

				// String

				writeString(this.IndicateurNationaliteConjoint1, dos);

				// String

				writeString(this.IndicateurNationaliteConjoint2, dos);

				// String

				writeString(this.JourMariage, dos);

				// Integer

				writeInteger(this.MoisMariage, dos);

				// String

				writeString(this.EnfantCommunMariage, dos);

				// String

				writeString(this.SexeConjoint1, dos);

				// String

				writeString(this.SexeConjoint2, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("Id=" + String.valueOf(Id));
			sb.append(",AnneeMariage=" + String.valueOf(AnneeMariage));
			sb.append(",AnneeNaissanceMere=" + String.valueOf(AnneeNaissanceMere));
			sb.append(",AnneeNaissancePere=" + String.valueOf(AnneeNaissancePere));
			sb.append(",DepartementDomicleApresMariage=" + DepartementDomicleApresMariage);
			sb.append(",DepartementMariage=" + DepartementMariage);
			sb.append(",DepartementNaissanceConjoint1=" + DepartementNaissanceConjoint1);
			sb.append(",DepartementNaissanceConjoint2=" + DepartementNaissanceConjoint2);
			sb.append(",EtatMatrimonialAnterieurConjoint1=" + EtatMatrimonialAnterieurConjoint1);
			sb.append(",EtatMatrimonialAnterieurConjoint2=" + EtatMatrimonialAnterieurConjoint2);
			sb.append(",IndicateurNationaliteConjoint1=" + IndicateurNationaliteConjoint1);
			sb.append(",IndicateurNationaliteConjoint2=" + IndicateurNationaliteConjoint2);
			sb.append(",JourMariage=" + JourMariage);
			sb.append(",MoisMariage=" + String.valueOf(MoisMariage));
			sb.append(",EnfantCommunMariage=" + EnfantCommunMariage);
			sb.append(",SexeConjoint1=" + SexeConjoint1);
			sb.append(",SexeConjoint2=" + SexeConjoint2);
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
		final static byte[] commonByteArrayLock_DATAMSPR_Mariage = new byte[0];
		static byte[] commonByteArray_DATAMSPR_Mariage = new byte[0];
		protected static final int DEFAULT_HASHCODE = 1;
		protected static final int PRIME = 31;
		protected int hashCode = DEFAULT_HASHCODE;
		public boolean hashCodeDirty = true;

		public String loopKey;

		public int Id;

		public int getId() {
			return this.Id;
		}

		public Integer AnneeMariage;

		public Integer getAnneeMariage() {
			return this.AnneeMariage;
		}

		public Integer AnneeNaissanceMere;

		public Integer getAnneeNaissanceMere() {
			return this.AnneeNaissanceMere;
		}

		public Integer AnneeNaissancePere;

		public Integer getAnneeNaissancePere() {
			return this.AnneeNaissancePere;
		}

		public String DepartementDomicleApresMariage;

		public String getDepartementDomicleApresMariage() {
			return this.DepartementDomicleApresMariage;
		}

		public String DepartementMariage;

		public String getDepartementMariage() {
			return this.DepartementMariage;
		}

		public String DepartementNaissanceConjoint1;

		public String getDepartementNaissanceConjoint1() {
			return this.DepartementNaissanceConjoint1;
		}

		public String DepartementNaissanceConjoint2;

		public String getDepartementNaissanceConjoint2() {
			return this.DepartementNaissanceConjoint2;
		}

		public String EtatMatrimonialAnterieurConjoint1;

		public String getEtatMatrimonialAnterieurConjoint1() {
			return this.EtatMatrimonialAnterieurConjoint1;
		}

		public String EtatMatrimonialAnterieurConjoint2;

		public String getEtatMatrimonialAnterieurConjoint2() {
			return this.EtatMatrimonialAnterieurConjoint2;
		}

		public String IndicateurNationaliteConjoint1;

		public String getIndicateurNationaliteConjoint1() {
			return this.IndicateurNationaliteConjoint1;
		}

		public String IndicateurNationaliteConjoint2;

		public String getIndicateurNationaliteConjoint2() {
			return this.IndicateurNationaliteConjoint2;
		}

		public String JourMariage;

		public String getJourMariage() {
			return this.JourMariage;
		}

		public Integer MoisMariage;

		public Integer getMoisMariage() {
			return this.MoisMariage;
		}

		public String EnfantCommunMariage;

		public String getEnfantCommunMariage() {
			return this.EnfantCommunMariage;
		}

		public String SexeConjoint1;

		public String getSexeConjoint1() {
			return this.SexeConjoint1;
		}

		public String SexeConjoint2;

		public String getSexeConjoint2() {
			return this.SexeConjoint2;
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
			other.AnneeMariage = this.AnneeMariage;
			other.AnneeNaissanceMere = this.AnneeNaissanceMere;
			other.AnneeNaissancePere = this.AnneeNaissancePere;
			other.DepartementDomicleApresMariage = this.DepartementDomicleApresMariage;
			other.DepartementMariage = this.DepartementMariage;
			other.DepartementNaissanceConjoint1 = this.DepartementNaissanceConjoint1;
			other.DepartementNaissanceConjoint2 = this.DepartementNaissanceConjoint2;
			other.EtatMatrimonialAnterieurConjoint1 = this.EtatMatrimonialAnterieurConjoint1;
			other.EtatMatrimonialAnterieurConjoint2 = this.EtatMatrimonialAnterieurConjoint2;
			other.IndicateurNationaliteConjoint1 = this.IndicateurNationaliteConjoint1;
			other.IndicateurNationaliteConjoint2 = this.IndicateurNationaliteConjoint2;
			other.JourMariage = this.JourMariage;
			other.MoisMariage = this.MoisMariage;
			other.EnfantCommunMariage = this.EnfantCommunMariage;
			other.SexeConjoint1 = this.SexeConjoint1;
			other.SexeConjoint2 = this.SexeConjoint2;

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
				if (length > commonByteArray_DATAMSPR_Mariage.length) {
					if (length < 1024 && commonByteArray_DATAMSPR_Mariage.length == 0) {
						commonByteArray_DATAMSPR_Mariage = new byte[1024];
					} else {
						commonByteArray_DATAMSPR_Mariage = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_DATAMSPR_Mariage, 0, length);
				strReturn = new String(commonByteArray_DATAMSPR_Mariage, 0, length, utf8Charset);
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
				if (length > commonByteArray_DATAMSPR_Mariage.length) {
					if (length < 1024 && commonByteArray_DATAMSPR_Mariage.length == 0) {
						commonByteArray_DATAMSPR_Mariage = new byte[1024];
					} else {
						commonByteArray_DATAMSPR_Mariage = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_DATAMSPR_Mariage, 0, length);
				strReturn = new String(commonByteArray_DATAMSPR_Mariage, 0, length, utf8Charset);
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

			synchronized (commonByteArrayLock_DATAMSPR_Mariage) {

				try {

					int length = 0;

					this.Id = dis.readInt();

					this.AnneeMariage = readInteger(dis);

					this.AnneeNaissanceMere = readInteger(dis);

					this.AnneeNaissancePere = readInteger(dis);

					this.DepartementDomicleApresMariage = readString(dis);

					this.DepartementMariage = readString(dis);

					this.DepartementNaissanceConjoint1 = readString(dis);

					this.DepartementNaissanceConjoint2 = readString(dis);

					this.EtatMatrimonialAnterieurConjoint1 = readString(dis);

					this.EtatMatrimonialAnterieurConjoint2 = readString(dis);

					this.IndicateurNationaliteConjoint1 = readString(dis);

					this.IndicateurNationaliteConjoint2 = readString(dis);

					this.JourMariage = readString(dis);

					this.MoisMariage = readInteger(dis);

					this.EnfantCommunMariage = readString(dis);

					this.SexeConjoint1 = readString(dis);

					this.SexeConjoint2 = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_DATAMSPR_Mariage) {

				try {

					int length = 0;

					this.Id = dis.readInt();

					this.AnneeMariage = readInteger(dis);

					this.AnneeNaissanceMere = readInteger(dis);

					this.AnneeNaissancePere = readInteger(dis);

					this.DepartementDomicleApresMariage = readString(dis);

					this.DepartementMariage = readString(dis);

					this.DepartementNaissanceConjoint1 = readString(dis);

					this.DepartementNaissanceConjoint2 = readString(dis);

					this.EtatMatrimonialAnterieurConjoint1 = readString(dis);

					this.EtatMatrimonialAnterieurConjoint2 = readString(dis);

					this.IndicateurNationaliteConjoint1 = readString(dis);

					this.IndicateurNationaliteConjoint2 = readString(dis);

					this.JourMariage = readString(dis);

					this.MoisMariage = readInteger(dis);

					this.EnfantCommunMariage = readString(dis);

					this.SexeConjoint1 = readString(dis);

					this.SexeConjoint2 = readString(dis);

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

				writeInteger(this.AnneeMariage, dos);

				// Integer

				writeInteger(this.AnneeNaissanceMere, dos);

				// Integer

				writeInteger(this.AnneeNaissancePere, dos);

				// String

				writeString(this.DepartementDomicleApresMariage, dos);

				// String

				writeString(this.DepartementMariage, dos);

				// String

				writeString(this.DepartementNaissanceConjoint1, dos);

				// String

				writeString(this.DepartementNaissanceConjoint2, dos);

				// String

				writeString(this.EtatMatrimonialAnterieurConjoint1, dos);

				// String

				writeString(this.EtatMatrimonialAnterieurConjoint2, dos);

				// String

				writeString(this.IndicateurNationaliteConjoint1, dos);

				// String

				writeString(this.IndicateurNationaliteConjoint2, dos);

				// String

				writeString(this.JourMariage, dos);

				// Integer

				writeInteger(this.MoisMariage, dos);

				// String

				writeString(this.EnfantCommunMariage, dos);

				// String

				writeString(this.SexeConjoint1, dos);

				// String

				writeString(this.SexeConjoint2, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// int

				dos.writeInt(this.Id);

				// Integer

				writeInteger(this.AnneeMariage, dos);

				// Integer

				writeInteger(this.AnneeNaissanceMere, dos);

				// Integer

				writeInteger(this.AnneeNaissancePere, dos);

				// String

				writeString(this.DepartementDomicleApresMariage, dos);

				// String

				writeString(this.DepartementMariage, dos);

				// String

				writeString(this.DepartementNaissanceConjoint1, dos);

				// String

				writeString(this.DepartementNaissanceConjoint2, dos);

				// String

				writeString(this.EtatMatrimonialAnterieurConjoint1, dos);

				// String

				writeString(this.EtatMatrimonialAnterieurConjoint2, dos);

				// String

				writeString(this.IndicateurNationaliteConjoint1, dos);

				// String

				writeString(this.IndicateurNationaliteConjoint2, dos);

				// String

				writeString(this.JourMariage, dos);

				// Integer

				writeInteger(this.MoisMariage, dos);

				// String

				writeString(this.EnfantCommunMariage, dos);

				// String

				writeString(this.SexeConjoint1, dos);

				// String

				writeString(this.SexeConjoint2, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("Id=" + String.valueOf(Id));
			sb.append(",AnneeMariage=" + String.valueOf(AnneeMariage));
			sb.append(",AnneeNaissanceMere=" + String.valueOf(AnneeNaissanceMere));
			sb.append(",AnneeNaissancePere=" + String.valueOf(AnneeNaissancePere));
			sb.append(",DepartementDomicleApresMariage=" + DepartementDomicleApresMariage);
			sb.append(",DepartementMariage=" + DepartementMariage);
			sb.append(",DepartementNaissanceConjoint1=" + DepartementNaissanceConjoint1);
			sb.append(",DepartementNaissanceConjoint2=" + DepartementNaissanceConjoint2);
			sb.append(",EtatMatrimonialAnterieurConjoint1=" + EtatMatrimonialAnterieurConjoint1);
			sb.append(",EtatMatrimonialAnterieurConjoint2=" + EtatMatrimonialAnterieurConjoint2);
			sb.append(",IndicateurNationaliteConjoint1=" + IndicateurNationaliteConjoint1);
			sb.append(",IndicateurNationaliteConjoint2=" + IndicateurNationaliteConjoint2);
			sb.append(",JourMariage=" + JourMariage);
			sb.append(",MoisMariage=" + String.valueOf(MoisMariage));
			sb.append(",EnfantCommunMariage=" + EnfantCommunMariage);
			sb.append(",SexeConjoint1=" + SexeConjoint1);
			sb.append(",SexeConjoint2=" + SexeConjoint2);
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
		final static byte[] commonByteArrayLock_DATAMSPR_Mariage = new byte[0];
		static byte[] commonByteArray_DATAMSPR_Mariage = new byte[0];

		public Integer AnneeMariage;

		public Integer getAnneeMariage() {
			return this.AnneeMariage;
		}

		public Integer AnneeNaissanceMere;

		public Integer getAnneeNaissanceMere() {
			return this.AnneeNaissanceMere;
		}

		public Integer AnneeNaissancePere;

		public Integer getAnneeNaissancePere() {
			return this.AnneeNaissancePere;
		}

		public String DepartementDomicleApresMariage;

		public String getDepartementDomicleApresMariage() {
			return this.DepartementDomicleApresMariage;
		}

		public String DepartementMariage;

		public String getDepartementMariage() {
			return this.DepartementMariage;
		}

		public String DepartementNaissanceConjoint1;

		public String getDepartementNaissanceConjoint1() {
			return this.DepartementNaissanceConjoint1;
		}

		public String DepartementNaissanceConjoint2;

		public String getDepartementNaissanceConjoint2() {
			return this.DepartementNaissanceConjoint2;
		}

		public String EtatMatrimonialAnterieurConjoint1;

		public String getEtatMatrimonialAnterieurConjoint1() {
			return this.EtatMatrimonialAnterieurConjoint1;
		}

		public String EtatMatrimonialAnterieurConjoint2;

		public String getEtatMatrimonialAnterieurConjoint2() {
			return this.EtatMatrimonialAnterieurConjoint2;
		}

		public String IndicateurNationaliteConjoint1;

		public String getIndicateurNationaliteConjoint1() {
			return this.IndicateurNationaliteConjoint1;
		}

		public String IndicateurNationaliteConjoint2;

		public String getIndicateurNationaliteConjoint2() {
			return this.IndicateurNationaliteConjoint2;
		}

		public String JourMariage;

		public String getJourMariage() {
			return this.JourMariage;
		}

		public Integer MoisMariage;

		public Integer getMoisMariage() {
			return this.MoisMariage;
		}

		public String EnfantCommunMariage;

		public String getEnfantCommunMariage() {
			return this.EnfantCommunMariage;
		}

		public String SexeConjoint1;

		public String getSexeConjoint1() {
			return this.SexeConjoint1;
		}

		public String SexeConjoint2;

		public String getSexeConjoint2() {
			return this.SexeConjoint2;
		}

		public String TrancheCommuneDomicileEpoux;

		public String getTrancheCommuneDomicileEpoux() {
			return this.TrancheCommuneDomicileEpoux;
		}

		public String TrancheUniteUrbaineDomicileEpoux;

		public String getTrancheUniteUrbaineDomicileEpoux() {
			return this.TrancheUniteUrbaineDomicileEpoux;
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
				if (length > commonByteArray_DATAMSPR_Mariage.length) {
					if (length < 1024 && commonByteArray_DATAMSPR_Mariage.length == 0) {
						commonByteArray_DATAMSPR_Mariage = new byte[1024];
					} else {
						commonByteArray_DATAMSPR_Mariage = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_DATAMSPR_Mariage, 0, length);
				strReturn = new String(commonByteArray_DATAMSPR_Mariage, 0, length, utf8Charset);
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
				if (length > commonByteArray_DATAMSPR_Mariage.length) {
					if (length < 1024 && commonByteArray_DATAMSPR_Mariage.length == 0) {
						commonByteArray_DATAMSPR_Mariage = new byte[1024];
					} else {
						commonByteArray_DATAMSPR_Mariage = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_DATAMSPR_Mariage, 0, length);
				strReturn = new String(commonByteArray_DATAMSPR_Mariage, 0, length, utf8Charset);
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

			synchronized (commonByteArrayLock_DATAMSPR_Mariage) {

				try {

					int length = 0;

					this.AnneeMariage = readInteger(dis);

					this.AnneeNaissanceMere = readInteger(dis);

					this.AnneeNaissancePere = readInteger(dis);

					this.DepartementDomicleApresMariage = readString(dis);

					this.DepartementMariage = readString(dis);

					this.DepartementNaissanceConjoint1 = readString(dis);

					this.DepartementNaissanceConjoint2 = readString(dis);

					this.EtatMatrimonialAnterieurConjoint1 = readString(dis);

					this.EtatMatrimonialAnterieurConjoint2 = readString(dis);

					this.IndicateurNationaliteConjoint1 = readString(dis);

					this.IndicateurNationaliteConjoint2 = readString(dis);

					this.JourMariage = readString(dis);

					this.MoisMariage = readInteger(dis);

					this.EnfantCommunMariage = readString(dis);

					this.SexeConjoint1 = readString(dis);

					this.SexeConjoint2 = readString(dis);

					this.TrancheCommuneDomicileEpoux = readString(dis);

					this.TrancheUniteUrbaineDomicileEpoux = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_DATAMSPR_Mariage) {

				try {

					int length = 0;

					this.AnneeMariage = readInteger(dis);

					this.AnneeNaissanceMere = readInteger(dis);

					this.AnneeNaissancePere = readInteger(dis);

					this.DepartementDomicleApresMariage = readString(dis);

					this.DepartementMariage = readString(dis);

					this.DepartementNaissanceConjoint1 = readString(dis);

					this.DepartementNaissanceConjoint2 = readString(dis);

					this.EtatMatrimonialAnterieurConjoint1 = readString(dis);

					this.EtatMatrimonialAnterieurConjoint2 = readString(dis);

					this.IndicateurNationaliteConjoint1 = readString(dis);

					this.IndicateurNationaliteConjoint2 = readString(dis);

					this.JourMariage = readString(dis);

					this.MoisMariage = readInteger(dis);

					this.EnfantCommunMariage = readString(dis);

					this.SexeConjoint1 = readString(dis);

					this.SexeConjoint2 = readString(dis);

					this.TrancheCommuneDomicileEpoux = readString(dis);

					this.TrancheUniteUrbaineDomicileEpoux = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// Integer

				writeInteger(this.AnneeMariage, dos);

				// Integer

				writeInteger(this.AnneeNaissanceMere, dos);

				// Integer

				writeInteger(this.AnneeNaissancePere, dos);

				// String

				writeString(this.DepartementDomicleApresMariage, dos);

				// String

				writeString(this.DepartementMariage, dos);

				// String

				writeString(this.DepartementNaissanceConjoint1, dos);

				// String

				writeString(this.DepartementNaissanceConjoint2, dos);

				// String

				writeString(this.EtatMatrimonialAnterieurConjoint1, dos);

				// String

				writeString(this.EtatMatrimonialAnterieurConjoint2, dos);

				// String

				writeString(this.IndicateurNationaliteConjoint1, dos);

				// String

				writeString(this.IndicateurNationaliteConjoint2, dos);

				// String

				writeString(this.JourMariage, dos);

				// Integer

				writeInteger(this.MoisMariage, dos);

				// String

				writeString(this.EnfantCommunMariage, dos);

				// String

				writeString(this.SexeConjoint1, dos);

				// String

				writeString(this.SexeConjoint2, dos);

				// String

				writeString(this.TrancheCommuneDomicileEpoux, dos);

				// String

				writeString(this.TrancheUniteUrbaineDomicileEpoux, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// Integer

				writeInteger(this.AnneeMariage, dos);

				// Integer

				writeInteger(this.AnneeNaissanceMere, dos);

				// Integer

				writeInteger(this.AnneeNaissancePere, dos);

				// String

				writeString(this.DepartementDomicleApresMariage, dos);

				// String

				writeString(this.DepartementMariage, dos);

				// String

				writeString(this.DepartementNaissanceConjoint1, dos);

				// String

				writeString(this.DepartementNaissanceConjoint2, dos);

				// String

				writeString(this.EtatMatrimonialAnterieurConjoint1, dos);

				// String

				writeString(this.EtatMatrimonialAnterieurConjoint2, dos);

				// String

				writeString(this.IndicateurNationaliteConjoint1, dos);

				// String

				writeString(this.IndicateurNationaliteConjoint2, dos);

				// String

				writeString(this.JourMariage, dos);

				// Integer

				writeInteger(this.MoisMariage, dos);

				// String

				writeString(this.EnfantCommunMariage, dos);

				// String

				writeString(this.SexeConjoint1, dos);

				// String

				writeString(this.SexeConjoint2, dos);

				// String

				writeString(this.TrancheCommuneDomicileEpoux, dos);

				// String

				writeString(this.TrancheUniteUrbaineDomicileEpoux, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("AnneeMariage=" + String.valueOf(AnneeMariage));
			sb.append(",AnneeNaissanceMere=" + String.valueOf(AnneeNaissanceMere));
			sb.append(",AnneeNaissancePere=" + String.valueOf(AnneeNaissancePere));
			sb.append(",DepartementDomicleApresMariage=" + DepartementDomicleApresMariage);
			sb.append(",DepartementMariage=" + DepartementMariage);
			sb.append(",DepartementNaissanceConjoint1=" + DepartementNaissanceConjoint1);
			sb.append(",DepartementNaissanceConjoint2=" + DepartementNaissanceConjoint2);
			sb.append(",EtatMatrimonialAnterieurConjoint1=" + EtatMatrimonialAnterieurConjoint1);
			sb.append(",EtatMatrimonialAnterieurConjoint2=" + EtatMatrimonialAnterieurConjoint2);
			sb.append(",IndicateurNationaliteConjoint1=" + IndicateurNationaliteConjoint1);
			sb.append(",IndicateurNationaliteConjoint2=" + IndicateurNationaliteConjoint2);
			sb.append(",JourMariage=" + JourMariage);
			sb.append(",MoisMariage=" + String.valueOf(MoisMariage));
			sb.append(",EnfantCommunMariage=" + EnfantCommunMariage);
			sb.append(",SexeConjoint1=" + SexeConjoint1);
			sb.append(",SexeConjoint2=" + SexeConjoint2);
			sb.append(",TrancheCommuneDomicileEpoux=" + TrancheCommuneDomicileEpoux);
			sb.append(",TrancheUniteUrbaineDomicileEpoux=" + TrancheUniteUrbaineDomicileEpoux);
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
						"C:/Program Files (x86)/TOS_DI-8.0.1/studio/workspace/DATAMSPR/_output/Mariage.csv"))
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
					outtFileOutputDelimited_1.write("AnneeMariage");
					outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
					outtFileOutputDelimited_1.write("AnneeNaissanceMere");
					outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
					outtFileOutputDelimited_1.write("AnneeNaissancePere");
					outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
					outtFileOutputDelimited_1.write("DepartementDomicleApresMariage");
					outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
					outtFileOutputDelimited_1.write("DepartementMariage");
					outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
					outtFileOutputDelimited_1.write("DepartementNaissanceConjoint1");
					outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
					outtFileOutputDelimited_1.write("DepartementNaissanceConjoint2");
					outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
					outtFileOutputDelimited_1.write("EtatMatrimonialAnterieurConjoint1");
					outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
					outtFileOutputDelimited_1.write("EtatMatrimonialAnterieurConjoint2");
					outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
					outtFileOutputDelimited_1.write("IndicateurNationaliteConjoint1");
					outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
					outtFileOutputDelimited_1.write("IndicateurNationaliteConjoint2");
					outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
					outtFileOutputDelimited_1.write("JourMariage");
					outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
					outtFileOutputDelimited_1.write("MoisMariage");
					outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
					outtFileOutputDelimited_1.write("EnfantCommunMariage");
					outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
					outtFileOutputDelimited_1.write("SexeConjoint1");
					outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
					outtFileOutputDelimited_1.write("SexeConjoint2");
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

				String tableName_tDBOutput_1 = "Mariage";
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
						.decryptPassword("enc:routine.encryption.key.v1:mtDTV60Axq0ujOKI1K/MVvNPW+l2rNjwmxgiR2pt5VY=");

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
					if (table_tDBOutput_1.equalsIgnoreCase("Mariage")) {
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
							+ "`(`Id` INT(255)   not null ,`AnneeMariage` INT(255)  ,`AnneeNaissanceMere` INT(255)  ,`AnneeNaissancePere` INT(255)  ,`DepartementDomicleApresMariage` VARCHAR(255)  ,`DepartementMariage` VARCHAR(255)  ,`DepartementNaissanceConjoint1` VARCHAR(255)  ,`DepartementNaissanceConjoint2` VARCHAR(255)  ,`EtatMatrimonialAnterieurConjoint1` VARCHAR(255)  ,`EtatMatrimonialAnterieurConjoint2` VARCHAR(255)  ,`IndicateurNationaliteConjoint1` VARCHAR(255)  ,`IndicateurNationaliteConjoint2` VARCHAR(255)  ,`JourMariage` VARCHAR(255)  ,`MoisMariage` INT(255)  ,`EnfantCommunMariage` VARCHAR(255)  ,`SexeConjoint1` VARCHAR(255)  ,`SexeConjoint2` VARCHAR(255)  ,primary key(`Id`))");
				}

				String insert_tDBOutput_1 = "INSERT INTO `" + "Mariage"
						+ "` (`Id`,`AnneeMariage`,`AnneeNaissanceMere`,`AnneeNaissancePere`,`DepartementDomicleApresMariage`,`DepartementMariage`,`DepartementNaissanceConjoint1`,`DepartementNaissanceConjoint2`,`EtatMatrimonialAnterieurConjoint1`,`EtatMatrimonialAnterieurConjoint2`,`IndicateurNationaliteConjoint1`,`IndicateurNationaliteConjoint2`,`JourMariage`,`MoisMariage`,`EnfantCommunMariage`,`SexeConjoint1`,`SexeConjoint2`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
				int batchSize_tDBOutput_1 = 10000;
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

					int[] colLengths = new int[17];

					public void addRow(String[] row) {

						for (int i = 0; i < 17; i++) {
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
						for (k = 0; k < (totals + 16 - name.length()) / 2; k++) {
							sb.append(' ');
						}
						sb.append(name);
						for (int i = 0; i < totals + 16 - name.length() - k; i++) {
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

						// last column
						for (int i = 0; i < colLengths[16] - fillChars[1].length() + 1; i++) {
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
				util_tLogRow_1.addRow(new String[] { "Id", "AnneeMariage", "AnneeNaissanceMere", "AnneeNaissancePere",
						"DepartementDomicleApresMariage", "DepartementMariage", "DepartementNaissanceConjoint1",
						"DepartementNaissanceConjoint2", "EtatMatrimonialAnterieurConjoint1",
						"EtatMatrimonialAnterieurConjoint2", "IndicateurNationaliteConjoint1",
						"IndicateurNationaliteConjoint2", "JourMariage", "MoisMariage", "EnfantCommunMariage",
						"SexeConjoint1", "SexeConjoint2", });
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
					String EtatMatrimonialAnterieurConjoint1;
					String EtatMatrimonialAnterieurConjoint2;
					String IndicateurNationaliteConjoint1;
					String IndicateurNationaliteConjoint2;
					String JourMariage;
					String EnfantCommunMariage;
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

					Object filename_tFileInputDelimited_1 = "C:/Program Files (x86)/TOS_DI-8.0.1/studio/workspace/DATAMSPR/_input/FD_MAR_2019.csv";
					if (filename_tFileInputDelimited_1 instanceof java.io.InputStream) {

						int footer_value_tFileInputDelimited_1 = 0, random_value_tFileInputDelimited_1 = -1;
						if (footer_value_tFileInputDelimited_1 > 0 || random_value_tFileInputDelimited_1 > 0) {
							throw new java.lang.Exception(
									"When the input source is a stream,footer and random shouldn't be bigger than 0.");
						}

					}
					try {
						fid_tFileInputDelimited_1 = new org.talend.fileprocess.FileInputDelimited(
								"C:/Program Files (x86)/TOS_DI-8.0.1/studio/workspace/DATAMSPR/_input/FD_MAR_2019.csv",
								"UTF-8", ";", "\n", false, 1, 0, limit_tFileInputDelimited_1, -1, false);
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

									row1.AnneeMariage = ParserUtils.parseTo_Integer(temp);

								} catch (java.lang.Exception ex_tFileInputDelimited_1) {
									globalMap.put("tFileInputDelimited_1_ERROR_MESSAGE",
											ex_tFileInputDelimited_1.getMessage());
									rowstate_tFileInputDelimited_1.setException(new RuntimeException(String.format(
											"Couldn't parse value for column '%s' in '%s', value is '%s'. Details: %s",
											"AnneeMariage", "row1", temp, ex_tFileInputDelimited_1),
											ex_tFileInputDelimited_1));
								}

							} else {

								row1.AnneeMariage = null;

							}

							columnIndexWithD_tFileInputDelimited_1 = 1;

							temp = fid_tFileInputDelimited_1.get(columnIndexWithD_tFileInputDelimited_1);
							if (temp.length() > 0) {

								try {

									row1.AnneeNaissanceMere = ParserUtils.parseTo_Integer(temp);

								} catch (java.lang.Exception ex_tFileInputDelimited_1) {
									globalMap.put("tFileInputDelimited_1_ERROR_MESSAGE",
											ex_tFileInputDelimited_1.getMessage());
									rowstate_tFileInputDelimited_1.setException(new RuntimeException(String.format(
											"Couldn't parse value for column '%s' in '%s', value is '%s'. Details: %s",
											"AnneeNaissanceMere", "row1", temp, ex_tFileInputDelimited_1),
											ex_tFileInputDelimited_1));
								}

							} else {

								row1.AnneeNaissanceMere = null;

							}

							columnIndexWithD_tFileInputDelimited_1 = 2;

							temp = fid_tFileInputDelimited_1.get(columnIndexWithD_tFileInputDelimited_1);
							if (temp.length() > 0) {

								try {

									row1.AnneeNaissancePere = ParserUtils.parseTo_Integer(temp);

								} catch (java.lang.Exception ex_tFileInputDelimited_1) {
									globalMap.put("tFileInputDelimited_1_ERROR_MESSAGE",
											ex_tFileInputDelimited_1.getMessage());
									rowstate_tFileInputDelimited_1.setException(new RuntimeException(String.format(
											"Couldn't parse value for column '%s' in '%s', value is '%s'. Details: %s",
											"AnneeNaissancePere", "row1", temp, ex_tFileInputDelimited_1),
											ex_tFileInputDelimited_1));
								}

							} else {

								row1.AnneeNaissancePere = null;

							}

							columnIndexWithD_tFileInputDelimited_1 = 3;

							row1.DepartementDomicleApresMariage = fid_tFileInputDelimited_1
									.get(columnIndexWithD_tFileInputDelimited_1);

							columnIndexWithD_tFileInputDelimited_1 = 4;

							row1.DepartementMariage = fid_tFileInputDelimited_1
									.get(columnIndexWithD_tFileInputDelimited_1);

							columnIndexWithD_tFileInputDelimited_1 = 5;

							row1.DepartementNaissanceConjoint1 = fid_tFileInputDelimited_1
									.get(columnIndexWithD_tFileInputDelimited_1);

							columnIndexWithD_tFileInputDelimited_1 = 6;

							row1.DepartementNaissanceConjoint2 = fid_tFileInputDelimited_1
									.get(columnIndexWithD_tFileInputDelimited_1);

							columnIndexWithD_tFileInputDelimited_1 = 7;

							row1.EtatMatrimonialAnterieurConjoint1 = fid_tFileInputDelimited_1
									.get(columnIndexWithD_tFileInputDelimited_1);

							columnIndexWithD_tFileInputDelimited_1 = 8;

							row1.EtatMatrimonialAnterieurConjoint2 = fid_tFileInputDelimited_1
									.get(columnIndexWithD_tFileInputDelimited_1);

							columnIndexWithD_tFileInputDelimited_1 = 9;

							row1.IndicateurNationaliteConjoint1 = fid_tFileInputDelimited_1
									.get(columnIndexWithD_tFileInputDelimited_1);

							columnIndexWithD_tFileInputDelimited_1 = 10;

							row1.IndicateurNationaliteConjoint2 = fid_tFileInputDelimited_1
									.get(columnIndexWithD_tFileInputDelimited_1);

							columnIndexWithD_tFileInputDelimited_1 = 11;

							row1.JourMariage = fid_tFileInputDelimited_1.get(columnIndexWithD_tFileInputDelimited_1);

							columnIndexWithD_tFileInputDelimited_1 = 12;

							temp = fid_tFileInputDelimited_1.get(columnIndexWithD_tFileInputDelimited_1);
							if (temp.length() > 0) {

								try {

									row1.MoisMariage = ParserUtils.parseTo_Integer(temp);

								} catch (java.lang.Exception ex_tFileInputDelimited_1) {
									globalMap.put("tFileInputDelimited_1_ERROR_MESSAGE",
											ex_tFileInputDelimited_1.getMessage());
									rowstate_tFileInputDelimited_1.setException(new RuntimeException(String.format(
											"Couldn't parse value for column '%s' in '%s', value is '%s'. Details: %s",
											"MoisMariage", "row1", temp, ex_tFileInputDelimited_1),
											ex_tFileInputDelimited_1));
								}

							} else {

								row1.MoisMariage = null;

							}

							columnIndexWithD_tFileInputDelimited_1 = 13;

							row1.EnfantCommunMariage = fid_tFileInputDelimited_1
									.get(columnIndexWithD_tFileInputDelimited_1);

							columnIndexWithD_tFileInputDelimited_1 = 14;

							row1.SexeConjoint1 = fid_tFileInputDelimited_1.get(columnIndexWithD_tFileInputDelimited_1);

							columnIndexWithD_tFileInputDelimited_1 = 15;

							row1.SexeConjoint2 = fid_tFileInputDelimited_1.get(columnIndexWithD_tFileInputDelimited_1);

							columnIndexWithD_tFileInputDelimited_1 = 16;

							row1.TrancheCommuneDomicileEpoux = fid_tFileInputDelimited_1
									.get(columnIndexWithD_tFileInputDelimited_1);

							columnIndexWithD_tFileInputDelimited_1 = 17;

							row1.TrancheUniteUrbaineDomicileEpoux = fid_tFileInputDelimited_1
									.get(columnIndexWithD_tFileInputDelimited_1);

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
								Var.EtatMatrimonialAnterieurConjoint1 = row1.EtatMatrimonialAnterieurConjoint1
										.equals("1")
												? "célibataire"
												: row1.EtatMatrimonialAnterieurConjoint1.equals("3") ? "veuf"
														: row1.EtatMatrimonialAnterieurConjoint1.equals("4") ? "divorcé"
																: "valeur inconnue";
								Var.EtatMatrimonialAnterieurConjoint2 = row1.EtatMatrimonialAnterieurConjoint2
										.equals("1")
												? "célibataire"
												: row1.EtatMatrimonialAnterieurConjoint2.equals("3") ? "veuf"
														: row1.EtatMatrimonialAnterieurConjoint2.equals("4") ? "divorcé"
																: "valeur inconnue";
								Var.IndicateurNationaliteConjoint1 = row1.IndicateurNationaliteConjoint1.equals("1")
										? "française"
										: row1.IndicateurNationaliteConjoint1.equals("2") ? "étrangère"
												: "valeur inconnue";
								Var.IndicateurNationaliteConjoint2 = row1.IndicateurNationaliteConjoint2.equals("1")
										? "française"
										: row1.IndicateurNationaliteConjoint2.equals("2") ? "étrangère"
												: "valeur inconnue";
								Var.JourMariage = row1.JourMariage.equals("1") ? "lundi"
										: row1.JourMariage.equals("2") ? "mardi"
												: row1.JourMariage.equals("3") ? "mercredi"
														: row1.JourMariage.equals("4") ? "jeudi"
																: row1.JourMariage.equals("5") ? "vendredi"
																		: row1.JourMariage.equals("6") ? "samedi"
																				: row1.JourMariage.equals("7")
																						? "dimanche"
																						: "valeur inconnue";
								Var.EnfantCommunMariage = row1.EnfantCommunMariage.equals("N")
										? "pas d'enfant en commun"
										: row1.EnfantCommunMariage.equals("O") ? "présence d'enfants en commun"
												: "valeur inconnue";// ###############################
																	// ###############################
																	// # Output tables

								out1 = null;

// # Output table : 'out1'
								out1_tmp.Id = Var.Id;
								out1_tmp.AnneeMariage = row1.AnneeMariage;
								out1_tmp.AnneeNaissanceMere = row1.AnneeNaissanceMere;
								out1_tmp.AnneeNaissancePere = row1.AnneeNaissancePere;
								out1_tmp.DepartementDomicleApresMariage = row1.DepartementDomicleApresMariage;
								out1_tmp.DepartementMariage = row1.DepartementMariage;
								out1_tmp.DepartementNaissanceConjoint1 = row1.DepartementNaissanceConjoint1;
								out1_tmp.DepartementNaissanceConjoint2 = row1.DepartementNaissanceConjoint2;
								out1_tmp.EtatMatrimonialAnterieurConjoint1 = Var.EtatMatrimonialAnterieurConjoint1;
								out1_tmp.EtatMatrimonialAnterieurConjoint2 = Var.EtatMatrimonialAnterieurConjoint2;
								out1_tmp.IndicateurNationaliteConjoint1 = Var.IndicateurNationaliteConjoint1;
								out1_tmp.IndicateurNationaliteConjoint2 = Var.IndicateurNationaliteConjoint2;
								out1_tmp.JourMariage = Var.JourMariage;
								out1_tmp.MoisMariage = row1.MoisMariage;
								out1_tmp.EnfantCommunMariage = Var.EnfantCommunMariage;
								out1_tmp.SexeConjoint1 = row1.SexeConjoint1;
								out1_tmp.SexeConjoint2 = row1.SexeConjoint2;
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

								String[] row_tLogRow_1 = new String[17];

								row_tLogRow_1[0] = String.valueOf(out1.Id);

								if (out1.AnneeMariage != null) { //
									row_tLogRow_1[1] = String.valueOf(out1.AnneeMariage);

								} //

								if (out1.AnneeNaissanceMere != null) { //
									row_tLogRow_1[2] = String.valueOf(out1.AnneeNaissanceMere);

								} //

								if (out1.AnneeNaissancePere != null) { //
									row_tLogRow_1[3] = String.valueOf(out1.AnneeNaissancePere);

								} //

								if (out1.DepartementDomicleApresMariage != null) { //
									row_tLogRow_1[4] = String.valueOf(out1.DepartementDomicleApresMariage);

								} //

								if (out1.DepartementMariage != null) { //
									row_tLogRow_1[5] = String.valueOf(out1.DepartementMariage);

								} //

								if (out1.DepartementNaissanceConjoint1 != null) { //
									row_tLogRow_1[6] = String.valueOf(out1.DepartementNaissanceConjoint1);

								} //

								if (out1.DepartementNaissanceConjoint2 != null) { //
									row_tLogRow_1[7] = String.valueOf(out1.DepartementNaissanceConjoint2);

								} //

								if (out1.EtatMatrimonialAnterieurConjoint1 != null) { //
									row_tLogRow_1[8] = String.valueOf(out1.EtatMatrimonialAnterieurConjoint1);

								} //

								if (out1.EtatMatrimonialAnterieurConjoint2 != null) { //
									row_tLogRow_1[9] = String.valueOf(out1.EtatMatrimonialAnterieurConjoint2);

								} //

								if (out1.IndicateurNationaliteConjoint1 != null) { //
									row_tLogRow_1[10] = String.valueOf(out1.IndicateurNationaliteConjoint1);

								} //

								if (out1.IndicateurNationaliteConjoint2 != null) { //
									row_tLogRow_1[11] = String.valueOf(out1.IndicateurNationaliteConjoint2);

								} //

								if (out1.JourMariage != null) { //
									row_tLogRow_1[12] = String.valueOf(out1.JourMariage);

								} //

								if (out1.MoisMariage != null) { //
									row_tLogRow_1[13] = String.valueOf(out1.MoisMariage);

								} //

								if (out1.EnfantCommunMariage != null) { //
									row_tLogRow_1[14] = String.valueOf(out1.EnfantCommunMariage);

								} //

								if (out1.SexeConjoint1 != null) { //
									row_tLogRow_1[15] = String.valueOf(out1.SexeConjoint1);

								} //

								if (out1.SexeConjoint2 != null) { //
									row_tLogRow_1[16] = String.valueOf(out1.SexeConjoint2);

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
								row4.AnneeMariage = row2.AnneeMariage;
								row4.AnneeNaissanceMere = row2.AnneeNaissanceMere;
								row4.AnneeNaissancePere = row2.AnneeNaissancePere;
								row4.DepartementDomicleApresMariage = row2.DepartementDomicleApresMariage;
								row4.DepartementMariage = row2.DepartementMariage;
								row4.DepartementNaissanceConjoint1 = row2.DepartementNaissanceConjoint1;
								row4.DepartementNaissanceConjoint2 = row2.DepartementNaissanceConjoint2;
								row4.EtatMatrimonialAnterieurConjoint1 = row2.EtatMatrimonialAnterieurConjoint1;
								row4.EtatMatrimonialAnterieurConjoint2 = row2.EtatMatrimonialAnterieurConjoint2;
								row4.IndicateurNationaliteConjoint1 = row2.IndicateurNationaliteConjoint1;
								row4.IndicateurNationaliteConjoint2 = row2.IndicateurNationaliteConjoint2;
								row4.JourMariage = row2.JourMariage;
								row4.MoisMariage = row2.MoisMariage;
								row4.EnfantCommunMariage = row2.EnfantCommunMariage;
								row4.SexeConjoint1 = row2.SexeConjoint1;
								row4.SexeConjoint2 = row2.SexeConjoint2;
								row3 = new row3Struct();

								row3.Id = row2.Id;
								row3.AnneeMariage = row2.AnneeMariage;
								row3.AnneeNaissanceMere = row2.AnneeNaissanceMere;
								row3.AnneeNaissancePere = row2.AnneeNaissancePere;
								row3.DepartementDomicleApresMariage = row2.DepartementDomicleApresMariage;
								row3.DepartementMariage = row2.DepartementMariage;
								row3.DepartementNaissanceConjoint1 = row2.DepartementNaissanceConjoint1;
								row3.DepartementNaissanceConjoint2 = row2.DepartementNaissanceConjoint2;
								row3.EtatMatrimonialAnterieurConjoint1 = row2.EtatMatrimonialAnterieurConjoint1;
								row3.EtatMatrimonialAnterieurConjoint2 = row2.EtatMatrimonialAnterieurConjoint2;
								row3.IndicateurNationaliteConjoint1 = row2.IndicateurNationaliteConjoint1;
								row3.IndicateurNationaliteConjoint2 = row2.IndicateurNationaliteConjoint2;
								row3.JourMariage = row2.JourMariage;
								row3.MoisMariage = row2.MoisMariage;
								row3.EnfantCommunMariage = row2.EnfantCommunMariage;
								row3.SexeConjoint1 = row2.SexeConjoint1;
								row3.SexeConjoint2 = row2.SexeConjoint2;

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
								if (row4.AnneeMariage != null) {
									sb_tFileOutputDelimited_1.append(row4.AnneeMariage);
								}
								sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
								if (row4.AnneeNaissanceMere != null) {
									sb_tFileOutputDelimited_1.append(row4.AnneeNaissanceMere);
								}
								sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
								if (row4.AnneeNaissancePere != null) {
									sb_tFileOutputDelimited_1.append(row4.AnneeNaissancePere);
								}
								sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
								if (row4.DepartementDomicleApresMariage != null) {
									sb_tFileOutputDelimited_1.append(row4.DepartementDomicleApresMariage);
								}
								sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
								if (row4.DepartementMariage != null) {
									sb_tFileOutputDelimited_1.append(row4.DepartementMariage);
								}
								sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
								if (row4.DepartementNaissanceConjoint1 != null) {
									sb_tFileOutputDelimited_1.append(row4.DepartementNaissanceConjoint1);
								}
								sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
								if (row4.DepartementNaissanceConjoint2 != null) {
									sb_tFileOutputDelimited_1.append(row4.DepartementNaissanceConjoint2);
								}
								sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
								if (row4.EtatMatrimonialAnterieurConjoint1 != null) {
									sb_tFileOutputDelimited_1.append(row4.EtatMatrimonialAnterieurConjoint1);
								}
								sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
								if (row4.EtatMatrimonialAnterieurConjoint2 != null) {
									sb_tFileOutputDelimited_1.append(row4.EtatMatrimonialAnterieurConjoint2);
								}
								sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
								if (row4.IndicateurNationaliteConjoint1 != null) {
									sb_tFileOutputDelimited_1.append(row4.IndicateurNationaliteConjoint1);
								}
								sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
								if (row4.IndicateurNationaliteConjoint2 != null) {
									sb_tFileOutputDelimited_1.append(row4.IndicateurNationaliteConjoint2);
								}
								sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
								if (row4.JourMariage != null) {
									sb_tFileOutputDelimited_1.append(row4.JourMariage);
								}
								sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
								if (row4.MoisMariage != null) {
									sb_tFileOutputDelimited_1.append(row4.MoisMariage);
								}
								sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
								if (row4.EnfantCommunMariage != null) {
									sb_tFileOutputDelimited_1.append(row4.EnfantCommunMariage);
								}
								sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
								if (row4.SexeConjoint1 != null) {
									sb_tFileOutputDelimited_1.append(row4.SexeConjoint1);
								}
								sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
								if (row4.SexeConjoint2 != null) {
									sb_tFileOutputDelimited_1.append(row4.SexeConjoint2);
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

								if (row3.AnneeMariage == null) {
									pstmt_tDBOutput_1.setNull(2, java.sql.Types.INTEGER);
								} else {
									pstmt_tDBOutput_1.setInt(2, row3.AnneeMariage);
								}

								if (row3.AnneeNaissanceMere == null) {
									pstmt_tDBOutput_1.setNull(3, java.sql.Types.INTEGER);
								} else {
									pstmt_tDBOutput_1.setInt(3, row3.AnneeNaissanceMere);
								}

								if (row3.AnneeNaissancePere == null) {
									pstmt_tDBOutput_1.setNull(4, java.sql.Types.INTEGER);
								} else {
									pstmt_tDBOutput_1.setInt(4, row3.AnneeNaissancePere);
								}

								if (row3.DepartementDomicleApresMariage == null) {
									pstmt_tDBOutput_1.setNull(5, java.sql.Types.VARCHAR);
								} else {
									pstmt_tDBOutput_1.setString(5, row3.DepartementDomicleApresMariage);
								}

								if (row3.DepartementMariage == null) {
									pstmt_tDBOutput_1.setNull(6, java.sql.Types.VARCHAR);
								} else {
									pstmt_tDBOutput_1.setString(6, row3.DepartementMariage);
								}

								if (row3.DepartementNaissanceConjoint1 == null) {
									pstmt_tDBOutput_1.setNull(7, java.sql.Types.VARCHAR);
								} else {
									pstmt_tDBOutput_1.setString(7, row3.DepartementNaissanceConjoint1);
								}

								if (row3.DepartementNaissanceConjoint2 == null) {
									pstmt_tDBOutput_1.setNull(8, java.sql.Types.VARCHAR);
								} else {
									pstmt_tDBOutput_1.setString(8, row3.DepartementNaissanceConjoint2);
								}

								if (row3.EtatMatrimonialAnterieurConjoint1 == null) {
									pstmt_tDBOutput_1.setNull(9, java.sql.Types.VARCHAR);
								} else {
									pstmt_tDBOutput_1.setString(9, row3.EtatMatrimonialAnterieurConjoint1);
								}

								if (row3.EtatMatrimonialAnterieurConjoint2 == null) {
									pstmt_tDBOutput_1.setNull(10, java.sql.Types.VARCHAR);
								} else {
									pstmt_tDBOutput_1.setString(10, row3.EtatMatrimonialAnterieurConjoint2);
								}

								if (row3.IndicateurNationaliteConjoint1 == null) {
									pstmt_tDBOutput_1.setNull(11, java.sql.Types.VARCHAR);
								} else {
									pstmt_tDBOutput_1.setString(11, row3.IndicateurNationaliteConjoint1);
								}

								if (row3.IndicateurNationaliteConjoint2 == null) {
									pstmt_tDBOutput_1.setNull(12, java.sql.Types.VARCHAR);
								} else {
									pstmt_tDBOutput_1.setString(12, row3.IndicateurNationaliteConjoint2);
								}

								if (row3.JourMariage == null) {
									pstmt_tDBOutput_1.setNull(13, java.sql.Types.VARCHAR);
								} else {
									pstmt_tDBOutput_1.setString(13, row3.JourMariage);
								}

								if (row3.MoisMariage == null) {
									pstmt_tDBOutput_1.setNull(14, java.sql.Types.INTEGER);
								} else {
									pstmt_tDBOutput_1.setInt(14, row3.MoisMariage);
								}

								if (row3.EnfantCommunMariage == null) {
									pstmt_tDBOutput_1.setNull(15, java.sql.Types.VARCHAR);
								} else {
									pstmt_tDBOutput_1.setString(15, row3.EnfantCommunMariage);
								}

								if (row3.SexeConjoint1 == null) {
									pstmt_tDBOutput_1.setNull(16, java.sql.Types.VARCHAR);
								} else {
									pstmt_tDBOutput_1.setString(16, row3.SexeConjoint1);
								}

								if (row3.SexeConjoint2 == null) {
									pstmt_tDBOutput_1.setNull(17, java.sql.Types.VARCHAR);
								} else {
									pstmt_tDBOutput_1.setString(17, row3.SexeConjoint2);
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
					if (!((Object) ("C:/Program Files (x86)/TOS_DI-8.0.1/studio/workspace/DATAMSPR/_input/FD_MAR_2019.csv") instanceof java.io.InputStream)) {
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
				 * [tFileInputDelimited_1 finally ] start
				 */

				currentComponent = "tFileInputDelimited_1";

				/**
				 * [tFileInputDelimited_1 finally ] stop
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
		final Mariage MariageClass = new Mariage();

		int exitCode = MariageClass.runJobInTOS(args);

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
			java.io.InputStream inContext = Mariage.class.getClassLoader()
					.getResourceAsStream("datamspr/mariage_0_1/contexts/" + contextStr + ".properties");
			if (inContext == null) {
				inContext = Mariage.class.getClassLoader()
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
			System.out.println((endUsedMemory - startUsedMemory) + " bytes memory increase when running : Mariage");
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
 * 176404 characters generated by Talend Open Studio for Data Integration on the
 * 15 avril 2024, 17:50:37 CEST
 ************************************************************************************************/