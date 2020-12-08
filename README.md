<!-- Copy and paste the converted output. -->

<!-----
NEW: Check the "Suppress top comment" option to remove this info from the output.

Conversion time: 0.407 seconds.


Using this Markdown file:

1. Paste this output into your source file.
2. See the notes and action items below regarding this conversion run.
3. Check the rendered output (headings, lists, code blocks, tables) for proper
   formatting and use a linkchecker before you publish this page.

Conversion notes:

* Docs to Markdown version 1.0β29
* Tue Dec 08 2020 10:29:31 GMT-0800 (PST)
* Source doc: Untitled document
----->



# A JAVA package providing Reading/Writing interface for NIfTi MRS format



---



## Introduction

NIfTI MRS is a standard format for accessing and sharing spectroscopic data. The purpose of this repository is to provide a robust tool for reading and writing NIfTI MRS format in JAVA programming languages.


## Features

This package can be implemented into any JAVA +8 program. The library gets powered by google GSON library for reading and writing JSON files. 

A cross-platform graphical user interface will be developed as a handy tool for viewing NIfTi MRS format.


## Installation

Developers can either include source code into their project or add the maven dependency to their project.


### Maven


```
<dependency>
```


`  `Will be uploded


```
</dependency>
```



## Examples

A test class has been created to demonstrate examples in the provisional format documentation. Developers can read the documentation [here](https://docs.google.com/document/d/1tC4ugzGUPLoqHRGrWvOcGCuCh_Dogx_uu0cxKub0EsM/edit?usp=sharing). 

The first step is instantiating a NiftiMRS object.


```
NiftiMRS niftiMRS = new NiftiMRS();
```


To assign the usage of each dimension as specified by keys in the “dim_5”, “dim_6” and “dim_7” fields, developers can use specific setters.


```
niftiMRS.setDim_5(DIM_KEYS.DIM_INDIRECT_0);
```


DIM_KEYS is an enum that contains specified keys.

If developers want to made dimensions’ information more explicit, setters and getters for “dim_5_info”, “dim_6_info” and “dim_7_info” fields are available. 


```
niftiMRS.setDim_5_info("Echo time increment");
```


If the dimension is used to increment over defined metadata key(s) then this can be conferred by using the optional “dim_5_header”, “dim_6_header” and “dim_7_header” fields.

If the metadata key is predefined in the format documentation, developers can use the `setDim_X_header(TAGS tag , List&lt;>) `method:


```
niftiMRS.setDim_5_header(TAGS.EchoTime, Arrays.asList(new Double[]{0.03, 0.04}));
niftiMRS.setDim_5_header(TAGS.RepetitionTime, Arrays.asList(new Double[]{1d, 1.10}));
```


TAGS is an enum that contains specified metadata.

Users should follow a slightly different approach to define their own metadata. A class, called Metadata, is created for this purpose.


```
Metadata userDefinedMetadata_1 = new Metadata(Arrays.asList(3), "duration of the excitation pulse", "ms", false);

niftiMRS.setDim_5_header("Excitation pulse duration", userDefinedMetadata_1);
```


For nested structures:


```
Metadata userDefinedMetadata_nes1 = new Metadata("Duration",Arrays.asList(3), "Duration of the excitation pulse", "ms", false);
Metadata userDefinedMetadata_nes2 = new Metadata("Pulse name",Arrays.asList("SINC"), "Excitation pulse description", null, false);

ArrayList<Metadata> userDefinedMetadataArrayList = new ArrayList<>();
userDefinedMetadataArrayList.add(userDefinedMetadata_nes1);
userDefinedMetadataArrayList.add(userDefinedMetadata_nes2);

niftiMRS.setDim_5_header("Excitation pulse information", userDefinedMetadataArrayList);
```



## Applications

_The possible applications of this package are the following software:_



1. jMRUI: The jMRUI Software Package plays an important role in quantification of MRS, MRSI and HRMAS signals. Most of the reported computational tools have been united in the Java version 5.0, including preparation of a database with NMR-SCOPE and NMR-SCOPE-B. (Graveron-Demilly, D., 2014. Quantification in magnetic resonance spectroscopy based on semi-parametric approaches. Magnetic Resonance Materials in Physics, Biology and Medicine. doi:10.1007/s10334-013-0393-4)
2. SpectrIm_: a preclinical tool for quantification of MRSI data_
3. FitAid_: a JAVA tool for quantification of 2D-NMR data (Chong, D.G.Q., Kreis, R., Bolliger, C.S. et al. Two-dimensional linear-combination model fitting of magnetic resonance spectra to define the macromolecule baseline using FiTAID, a Fitting Tool for Arrays of Interrelated Datasets. Magn Reson Mater Phy 24, 147–164 (2011). https://doi.org/10.1007/s10334-011-0246-y)_
