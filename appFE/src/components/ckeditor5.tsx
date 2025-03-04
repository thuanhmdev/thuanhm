"use client";
import { useState, useEffect, useRef } from "react";
import { CKEditor } from "@ckeditor/ckeditor5-react";

import {
	ClassicEditor,
	AccessibilityHelp,
	Alignment,
	Autoformat,
	AutoImage,
	Autosave,
	Base64UploadAdapter,
	BlockQuote,
	Bold,
	Code,
	CodeBlock,
	Essentials,
	FontBackgroundColor,
	FontColor,
	FontFamily,
	FontSize,
	FullPage,
	GeneralHtmlSupport,
	Heading,
	HorizontalLine,
	HtmlComment,
	HtmlEmbed,
	ImageBlock,
	ImageCaption,
	ImageInline,
	ImageInsert,
	ImageInsertViaUrl,
	ImageResize,
	ImageStyle,
	ImageTextAlternative,
	ImageToolbar,
	ImageUpload,
	Indent,
	IndentBlock,
	Italic,
	Link,
	LinkImage,
	List,
	ListProperties,
	MediaEmbed,
	Mention,
	Paragraph,
	PasteFromOffice,
	RemoveFormat,
	SelectAll,
	ShowBlocks,
	SourceEditing,
	SpecialCharacters,
	SpecialCharactersArrows,
	SpecialCharactersCurrency,
	SpecialCharactersEssentials,
	SpecialCharactersLatin,
	SpecialCharactersMathematical,
	SpecialCharactersText,
	Style,
	Subscript,
	Superscript,
	Table,
	TableCaption,
	TableCellProperties,
	TableColumnResize,
	TableProperties,
	TableToolbar,
	TextTransformation,
	TodoList,
	Underline,
	Undo,
	EditorConfig,
	EventInfo,
} from "ckeditor5";

import "ckeditor5/ckeditor5.css";
import { useFormContext } from "react-hook-form";

export default function CKEditor5() {
	const { setValue, getValues } = useFormContext();

	const editorContainerRef = useRef(null);
	const editorRef = useRef(null);
	const [isLayoutReady, setIsLayoutReady] = useState(false);

	useEffect(() => {
		setIsLayoutReady(true);

		return () => setIsLayoutReady(false);
	}, []);

	const editorConfig = {
		toolbar: {
			items: [
				"undo",
				"redo",
				"|",
				"sourceEditing",
				"showBlocks",
				"|",
				"heading",
				"style",
				"|",
				"fontSize",
				"fontFamily",
				"fontColor",
				"fontBackgroundColor",
				"|",
				"bold",
				"italic",
				"underline",
				"subscript",
				"superscript",
				"code",
				"removeFormat",
				"|",
				"specialCharacters",
				"horizontalLine",
				"link",
				"insertImage",
				"insertImageViaUrl",
				"mediaEmbed",
				"insertTable",
				"blockQuote",
				"codeBlock",
				"htmlEmbed",
				"|",
				"alignment",
				"|",
				"bulletedList",
				"numberedList",
				"todoList",
				"outdent",
				"indent",
			],
			shouldNotGroupWhenFull: false,
		},
		plugins: [
			AccessibilityHelp,
			Alignment,
			Autoformat,
			AutoImage,
			Autosave,
			Base64UploadAdapter,
			BlockQuote,
			Bold,
			Code,
			CodeBlock,
			Essentials,
			FontBackgroundColor,
			FontColor,
			FontFamily,
			FontSize,
			FullPage,
			GeneralHtmlSupport,
			Heading,
			HorizontalLine,
			HtmlComment,
			HtmlEmbed,
			ImageBlock,
			ImageCaption,
			ImageInline,
			ImageInsert,
			ImageInsertViaUrl,
			ImageResize,
			ImageStyle,
			ImageTextAlternative,
			ImageToolbar,
			ImageUpload,
			Indent,
			IndentBlock,
			Italic,
			Link,
			LinkImage,
			List,
			ListProperties,
			MediaEmbed,
			Mention,
			Paragraph,
			PasteFromOffice,
			RemoveFormat,
			SelectAll,
			ShowBlocks,
			SourceEditing,
			SpecialCharacters,
			SpecialCharactersArrows,
			SpecialCharactersCurrency,
			SpecialCharactersEssentials,
			SpecialCharactersLatin,
			SpecialCharactersMathematical,
			SpecialCharactersText,
			Style,
			Subscript,
			Superscript,
			Table,
			TableCaption,
			TableCellProperties,
			TableColumnResize,
			TableProperties,
			TableToolbar,
			TextTransformation,
			TodoList,
			Underline,
			Undo,
		],
		fontFamily: {
			supportAllValues: true,
		},
		fontSize: {
			options: [10, 12, 14, "default", 18, 20, 22],
			supportAllValues: true,
		},
		heading: {
			options: [
				{
					model: "paragraph",
					title: "Paragraph",
					class: "ck-heading_paragraph",
				},
				{
					model: "heading1",
					view: "h1",
					title: "Heading 1",
					class: "ck-heading_heading1",
				},
				{
					model: "heading2",
					view: "h2",
					title: "Heading 2",
					class: "ck-heading_heading2",
				},
				{
					model: "heading3",
					view: "h3",
					title: "Heading 3",
					class: "ck-heading_heading3",
				},
				{
					model: "heading4",
					view: "h4",
					title: "Heading 4",
					class: "ck-heading_heading4",
				},
				{
					model: "heading5",
					view: "h5",
					title: "Heading 5",
					class: "ck-heading_heading5",
				},
				{
					model: "heading6",
					view: "h6",
					title: "Heading 6",
					class: "ck-heading_heading6",
				},
			],
		},
		htmlSupport: {
			allow: [
				{
					name: /^.*$/,
					styles: true,
					attributes: true,
					classes: true,
				},
			],
		},
		image: {
			toolbar: [
				"toggleImageCaption",
				"imageTextAlternative",
				"|",
				"imageStyle:inline",
				"imageStyle:wrapText",
				"imageStyle:breakText",
				"|",
				"resizeImage",
			],
		},
		initialData:
			'',
		link: {
			addTargetToExternalLinks: true,
			defaultProtocol: "https://",
			decorators: {
				toggleDownloadable: {
					mode: "manual",
					label: "Downloadable",
					attributes: {
						download: "file",
					},
				},
			},
		},
		list: {
			properties: {
				styles: true,
				startIndex: true,
				reversed: true,
			},
		},
		mention: {
			feeds: [
				{
					marker: "@",
					feed: [
						/* See: https://ckeditor.com/docs/ckeditor5/latest/features/mentions.html */
					],
				},
			],
		},
		menuBar: {
			isVisible: true,
		},
		placeholder: "Type or paste your content here!",
		style: {
			definitions: [
				{
					name: "Article category",
					element: "h3",
					classes: ["category"],
				},
				{
					name: "Title",
					element: "h2",
					classes: ["document-title"],
				},
				{
					name: "Subtitle",
					element: "h3",
					classes: ["document-subtitle"],
				},
				{
					name: "Info box",
					element: "p",
					classes: ["info-box"],
				},
				{
					name: "Side quote",
					element: "blockquote",
					classes: ["side-quote"],
				},
				{
					name: "Marker",
					element: "span",
					classes: ["marker"],
				},
				{
					name: "Spoiler",
					element: "span",
					classes: ["spoiler"],
				},
				{
					name: "Code (dark)",
					element: "pre",
					classes: ["fancy-code", "fancy-code-dark"],
				},
				{
					name: "Code (bright)",
					element: "pre",
					classes: ["fancy-code", "fancy-code-bright"],
				},
			],
		},
		table: {
			contentToolbar: [
				"tableColumn",
				"tableRow",
				"mergeTableCells",
				"tableProperties",
				"tableCellProperties",
			],
		},
	};

	return (
		<div className="max-w-[100%]">
			<div className="main-container">
				<div
					className="editor-container editor-container_classic-editor editor-container_include-style"
					ref={editorContainerRef}
				>
					<div className="editor-container__editor">
						<div ref={editorRef}>
							{isLayoutReady && (
								<CKEditor
									editor={ClassicEditor}
									data={getValues("content")}
									onChange={(event: EventInfo, editor: ClassicEditor) => {
										setValue("content", editor.data.get())
									}}
									config={editorConfig as EditorConfig}
								/>
							)}
						</div>
					</div>
				</div>
			</div>
		</div>
	);
}
