# A JAVA package providing Reading/Writing interface for NIfTi MRS format



---
	

## Introduction

NIfTI MRS is a standard format for accessing and sharing spectroscopic data ([link to NIfTI MRS](https://github.com/wexeee/mrs_nifti_standard)). The purpose of this repository is to provide a robust tool for reading and writing NIfTI MRS format in JAVA programming languages.
:warning: UNDER DEVELOPMENT

## Features

This package can be implemented into any JAVA +8 program. The library gets powered by google GSON library for reading and writing JSON files. 

A cross-platform graphical user interface will be developed as a handy tool for viewing NIfTi MRS format.


## Installation

Developers can either include source code into their project or add the maven dependency to their project.


### Maven


```
<dependency>
```


`  Will be uploded `


```
</dependency>
```



## Examples

A test class has been created to demonstrate examples in the provisional format documentation. Developers can read the documentation [here](https://docs.google.com/document/d/1tC4ugzGUPLoqHRGrWvOcGCuCh_Dogx_uu0cxKub0EsM/edit?usp=sharing). 

The first step is instantiating a org.NifTiMRS.NiftiMRS object.


```java
   org.NifTiMRS.JsonExtention JsonNiftiMRS = new org.NifTiMRS.JsonExtention();
```


To assign the usage of each dimension as specified by keys in the “dim_5”, “dim_6” and “dim_7” fields, developers can use specific setters.


```java
JsonNiftiMRS.setDim_5(org.NifTiMRS.DIM_KEYS.DIM_INDIRECT_0);
```


org.NifTiMRS.DIM_KEYS is an enum that contains specified keys.

If developers want to made dimensions’ information more explicit, setters and getters for “dim_5_info”, “dim_6_info” and “dim_7_info” fields are available. 


```java
JsonNiftiMRS.setDim_5_info("Echo time increment");
```


If the dimension is used to increment over defined metadata key(s) then this can be conferred by using the optional “dim_5_header”, “dim_6_header” and “dim_7_header” fields.

If the metadata key is predefined in the format documentation, developers can use the `setDim_X_header(org.NifTiMRS.TAGS tag , List&lt;>) `method:


```java
JsonNiftiMRS.setDim_5_header(org.NifTiMRS.TAGS.EchoTime, Arrays.asList(new Double[]{0.03, 0.04}));
JsonNiftiMRS.setDim_5_header(org.NifTiMRS.TAGS.RepetitionTime, Arrays.asList(new Double[]{1d, 1.10}));
```


The output of the above code is:


```json
{
 "dim_5": "DIM_INDIRECT_0",
 "dim_5_info": "Echo time increment",
 "dim_5_header": {
   "EchoTime": [
     0.03,
     0.04
   ],
   "RepetitionTime": [
     1.0,
     1.1
   ]
   }
}
```
org.NifTiMRS.TAGS is an enum that contains specified metadata.

Users should follow a slightly different approach to define their own metadata. A class, called org.NifTiMRS.Metadata, is created for this purpose.


```java
org.NifTiMRS.Metadata userDefinedMetadata_1 = new org.NifTiMRS.Metadata(Arrays.asList(3), "duration of the excitation pulse", "ms", false);

JsonNiftiMRS.setDim_5_header("Excitation pulse duration", userDefinedMetadata_1);
```


For nested structures:


```java
org.NifTiMRS.Metadata userDefinedMetadata_nes1 = new org.NifTiMRS.Metadata("Duration",Arrays.asList(3), "Duration of the excitation pulse", "ms", false);
org.NifTiMRS.Metadata userDefinedMetadata_nes2 = new org.NifTiMRS.Metadata("Pulse name",Arrays.asList("SINC"), "Excitation pulse description", null, false);

ArrayList<org.NifTiMRS.Metadata> userDefinedMetadataArrayList = new ArrayList<>();
userDefinedMetadataArrayList.add(userDefinedMetadata_nes1);
userDefinedMetadataArrayList.add(userDefinedMetadata_nes2);

JsonNiftiMRS.setDim_5_header("Excitation pulse information", userDefinedMetadataArrayList);
```

The final output: 
```json
{
 "dim_5": "DIM_INDIRECT_0",
 "dim_5_info": "Echo time increment",
 "dim_5_header": {
   "EchoTime": [
     0.03,
     0.04
   ],
   "RepetitionTime": [
     1.0,
     1.1
   ],
   "Excitation pulse duration": {
     "Value": [
       3
     ],
     "Description": "duration of the excitation pulse",
     "Unit": "ms",
     "Delete_for_anon": false
   },
   "Excitation pulse information": {
     "Duration": {
       "Value": [
         3
       ],
       "Description": "Duration of the excitation pulse",
       "Unit": "ms",
       "Delete_for_anon": false
     },
     "Pulse name": {
       "Value": [
         "SINC"
       ],
       "Description": "Excitation pulse description",
       "Delete_for_anon": false
     }
   }
 },
 "dim_6_header": {},
 "dim_7_header": {}
}
```
another example for storing json along nifti:
```java
        org.NifTiMRS.NiftiMRS niftiMRS = new org.NifTiMRS.NiftiMRS(new int[] {1,1,1,2048});

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j <2 ; j++) {
                niftiMRS.getNifti().data.set(new int[] {j,0,0,i},Math.random());
            }
        }

        niftiMRS.getNifti().header.pixdim[4] = (float) 0.00025;
        niftiMRS.getJson().ResonantNucleus = new String[] {org.NifTiMRS.Nucleus.N_1H.toString()};
        niftiMRS.getJson().SpectrometerFrequency = new Double[] {400d};

        niftiMRS.write("testNiftiHeadandVol.nii", false, false);
```

## Applications

_The possible applications of this package are the following software:_



1. jMRUI: The jMRUI Software Package plays an important role in quantification of MRS, MRSI and HRMAS signals. Most of the reported computational tools have been united in the Java version 5.0, including preparation of a database with NMR-SCOPE and NMR-SCOPE-B. (Graveron-Demilly, D., 2014. Quantification in magnetic resonance spectroscopy based on semi-parametric approaches. Magnetic Resonance Materials in Physics, Biology and Medicine. doi:10.1007/s10334-013-0393-4)
2. SpectrIm: a preclinical tool for quantification of MRSI data_
3. FitAid: a JAVA tool for quantification of 2D-NMR data (Chong, D.G.Q., Kreis, R., Bolliger, C.S. et al. Two-dimensional linear-combination model fitting of magnetic resonance spectra to define the macromolecule baseline using FiTAID, a Fitting Tool for Arrays of Interrelated Datasets. Magn Reson Mater Phy 24, 147–164 (2011). https://doi.org/10.1007/s10334-011-0246-y)
