/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views.mediaAdmin;

import aims.FormatNumber;
import controller.Media.*;
import java.awt.Component;
import javax.swing.JButton;
import javax.swing.JLabel;
import model.Media.*;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author Toshiba T75
 */
public class MediaAddFrame extends javax.swing.JFrame {
    
    private Media media;
    private int old_id;
    
    /**
     * Creates new form MediaAddFrame
     */
    
    private boolean flag;
    
    public MediaAddFrame() {
        
        flag = true;
        initComponents();
    }
    
    public MediaAddFrame(Media md, boolean isEdit) {
        flag = false;
        initComponents();
        if (isEdit) {
            big_label.setText("Edit Media");
            categories.setEnabled(false);
        } else {
            big_label.setText("View Media");
            media_name.setEditable(false);
            media_value.setEditable(false);
            media_value.setEditable(false);
            media_price.setEditable(false);
            categories.setEditable(false);
            categories.setEnabled(false);
            media_image_path.setEnabled(false);
            confirmButton.setVisible(false);
        }
        
        media = md;
        media.setId(md.getId());
        old_id = md.getId();
        media_name.setText(md.getTitle());
        media_value.setText(md.getValue() + "");
        media_price.setText(md.getPrice() + "");
        categories.setEditable(false);
        int category_id = md.getCategoryId();
        categories.setSelectedIndex(category_id-1);
        String[] splitArr = md.getImagePath().split("/");
        String path = splitArr[splitArr.length-1];
//        media_image_path.setText(md.getImagePath());
        media_image_path.setText(path);
        switch (category_id) {
            case 1:
                Book book = (Book) md;
                // if click show button
                if (!isEdit) {
                    media_quantity.setEditable(false);
                    media_input_day.setEditable(false);
                    media_barcode.setEditable(false);
                    media_weight.setEditable(false);
                    media_width.setEditable(false);
                    media_height.setEditable(false);
                    media_depth.setEditable(false);
                    media_description.setEditable(false);
                    author_name.setEditable(false);
                    languages.setEditable(false);
                    languages.setEnabled(false);
                    publisher.setEditable(false);
                    publication_date.setEditable(false);
                    page_number.setEditable(false);
                    book_genre.setEditable(false);
                    cover_types.setEnabled(false);
                }
                media_quantity.setText(book.getQuantity() + "");
                media_input_day.setText(book.getInputDay().split(" ")[0]);
                media_barcode.setText(book.getBarcode());
                media_weight.setText(book.getWeight() + "");
                media_width.setText(book.getWidth() + "");
                media_height.setText(book.getHeight()+"");
                media_depth.setText(book.getDepth()+"");
                media_description.setText(book.getDescription());
                author_name.setText(book.getAuthor());
                languages.setSelectedIndex(book.getLanguageId()-1);
                publisher.setText(book.getPublisher());
                publication_date.setText(book.getPublication_date().split(" ")[0]);
                page_number.setText(book.getPageNumber()+"");
                book_genre.setText(book.getGenre());
                cover_types.setSelectedIndex(book.getCoverTypeID()-1);
                setVisiblePanels(category_id);
                
                break;
            case 2:
                DVD dvd = (DVD) md;
                if (!isEdit) {
                    media_quantity.setEditable(false);
                    media_input_day.setEditable(false);
                    media_barcode.setEditable(false);
                    media_weight.setEditable(false);
                    media_width.setEditable(false);
                    media_height.setEditable(false);
                    media_depth.setEditable(false);
                    media_description.setEditable(false);
                    writer_name.setEditable(false);
                    studio_name.setEditable(false);
                    publication_date_dvd.setEditable(false);
                    dvd_runtime.setEditable(false);
                    dvd_languages.setEditable(false);
                    dvd_languages.setEnabled(false);
                    dvd_types.setEnabled(false);
                    dvd_subtitle.setEditable(false);
                }
                media_quantity.setText(dvd.getQuantity() + "");
                media_input_day.setText(dvd.getInputDay().split(" ")[0]);
                media_barcode.setText(dvd.getBarcode());
                media_weight.setText(dvd.getWeight() + "");
                media_width.setText(dvd.getWidth() + "");
                media_height.setText(dvd.getHeight()+"");
                media_depth.setText(dvd.getDepth()+"");
                media_description.setText(dvd.getDescription());
                writer_name.setText(dvd.getWriterName());
                studio_name.setText(dvd.getStudioName());
                publication_date_dvd.setText(dvd.getPublicationDate().split(" ")[0]);
                dvd_runtime.setText(dvd.getRunTime()+"");
                dvd_languages.setSelectedIndex(dvd.getLanguageId()-1);
                dvd_types.setSelectedIndex(dvd.getDvdTypeId()-1);
                dvd_subtitle.setText(dvd.getSubtitle());
                setVisiblePanels(category_id);
                break;
            case 3:
                CD cd = (CD) md;
                if (!isEdit) {
                    media_quantity.setEditable(false);
                    media_input_day.setEditable(false);
                    media_barcode.setEditable(false);
                    media_weight.setEditable(false);
                    media_width.setEditable(false);
                    media_height.setEditable(false);
                    media_depth.setEditable(false);
                    media_description.setEditable(false);
                    artist_name.setEditable(false);
                    cd_genre.setEditable(false);
                    record_name.setEditable(false);
                    cd_publication_date.setEditable(false);
                    tracks_list.setEditable(false);
                }
                media_quantity.setText(cd.getQuantity() + "");
                media_input_day.setText(cd.getInputDay().split(" ")[0]);
                media_barcode.setText(cd.getBarcode());
                media_weight.setText(cd.getWeight() + "");
                media_width.setText(cd.getWidth() + "");
                media_height.setText(cd.getHeight()+"");
                media_depth.setText(cd.getDepth()+"");
                media_description.setText(cd.getDescription());
                artist_name.setText(cd.getArtistName());
                cd_genre.setText(cd.getGenre());
                record_name.setText(cd.getRecordLabelName());
                cd_publication_date.setText(cd.getPublicationDate().split(" ")[0]);
                tracks_list.setText(cd.getTrackList());
                setVisiblePanels(category_id);
                break;
            case 4:
                LP lp = (LP) md;
                if (!isEdit) {
                    media_quantity.setEditable(false);
                    media_input_day.setEditable(false);
                    media_barcode.setEditable(false);
                    media_weight.setEditable(false);
                    media_width.setEditable(false);
                    media_height.setEditable(false);
                    media_depth.setEditable(false);
                    media_description.setEditable(false);
                    lp_artist_name.setEditable(false);
                    lp_genre.setEditable(false);
                    lp_record.setEditable(false);
                    lp_publication_date.setEditable(false);
                    lp_tracks_list.setEditable(false);
                }
                media_quantity.setText(lp.getQuantity() + "");
                media_input_day.setText(lp.getInputDay().split(" ")[0]);
                media_barcode.setText(lp.getBarcode());
                media_weight.setText(lp.getWeight() + "");
                media_width.setText(lp.getWidth() + "");
                media_height.setText(lp.getHeight()+"");
                media_depth.setText(lp.getDepth()+"");
                media_description.setText(lp.getDescription());
                lp_artist_name.setText(lp.getArtistName());
                lp_genre.setText(lp.getGenre());
                lp_record.setText(lp.getRecordLabelName());
                lp_publication_date.setText(lp.getPublicationDate().split(" ")[0]);
                lp_tracks_list.setText(lp.getTrackList());
                
                setVisiblePanels(category_id);
                break;
        }
       
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        left_panel = new javax.swing.JPanel();
        currency_label = new javax.swing.JLabel();
        currency_label1 = new javax.swing.JLabel();
        category_label = new javax.swing.JLabel();
        name_label = new javax.swing.JLabel();
        media_value = new javax.swing.JFormattedTextField();
        big_label = new javax.swing.JLabel();
        media_name = new javax.swing.JTextField();
        price_label = new javax.swing.JLabel();
        media_price = new javax.swing.JFormattedTextField();
        value_label = new javax.swing.JLabel();
        categories = new javax.swing.JComboBox<>();
        quantity_label = new javax.swing.JLabel();
        input_day_label = new javax.swing.JLabel();
        media_input_day = new javax.swing.JFormattedTextField();
        barcode_label = new javax.swing.JLabel();
        media_barcode = new javax.swing.JTextField();
        weight_label = new javax.swing.JLabel();
        media_description_label = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        media_description = new javax.swing.JTextArea();
        width_label = new javax.swing.JLabel();
        media_quantity = new javax.swing.JFormattedTextField();
        media_width = new javax.swing.JFormattedTextField();
        height_label = new javax.swing.JLabel();
        media_height = new javax.swing.JFormattedTextField();
        depth_label = new javax.swing.JLabel();
        media_depth = new javax.swing.JFormattedTextField();
        media_weight = new javax.swing.JFormattedTextField();
        image_path_label = new javax.swing.JLabel();
        media_image_path = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        detail_panel = new javax.swing.JPanel();
        detail_label = new javax.swing.JLabel();
        add_panel = new javax.swing.JPanel();
        confirmButton = new javax.swing.JButton();
        backButton = new javax.swing.JButton();
        cards = new javax.swing.JPanel();
        book_panel = new javax.swing.JPanel();
        author_label = new javax.swing.JLabel();
        author_name = new javax.swing.JTextField();
        language_label = new javax.swing.JLabel();
        languages = new javax.swing.JComboBox<>();
        publisher_label = new javax.swing.JLabel();
        publisher = new javax.swing.JTextField();
        publication_label = new javax.swing.JLabel();
        publication_date = new javax.swing.JFormattedTextField();
        page_label = new javax.swing.JLabel();
        page_number = new javax.swing.JFormattedTextField();
        cover_label = new javax.swing.JLabel();
        cover_types = new javax.swing.JComboBox<>();
        category_label1 = new javax.swing.JLabel();
        genre_label = new javax.swing.JLabel();
        book_genre = new javax.swing.JTextField();
        dvd_panel = new javax.swing.JPanel();
        dvd_label = new javax.swing.JLabel();
        director_label = new javax.swing.JLabel();
        writer_name = new javax.swing.JTextField();
        dvd_studio_label = new javax.swing.JLabel();
        studio_name = new javax.swing.JTextField();
        publication_dvd_label = new javax.swing.JLabel();
        publication_date_dvd = new javax.swing.JFormattedTextField();
        dvd_runtime_label = new javax.swing.JLabel();
        dvd_runtime = new javax.swing.JTextField();
        dvd_language_label = new javax.swing.JLabel();
        dvd_languages = new javax.swing.JComboBox<>();
        dvd_type_label = new javax.swing.JLabel();
        dvd_types = new javax.swing.JComboBox<>();
        dvd_subtitle_label = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        dvd_subtitle = new javax.swing.JTextArea();
        cd_panel = new javax.swing.JPanel();
        cd_label = new javax.swing.JLabel();
        artist_label = new javax.swing.JLabel();
        artist_name = new javax.swing.JTextField();
        cd_genre_label = new javax.swing.JLabel();
        cd_genre = new javax.swing.JTextField();
        record_label = new javax.swing.JLabel();
        record_name = new javax.swing.JTextField();
        cd_publication_label = new javax.swing.JLabel();
        cd_publication_date = new javax.swing.JFormattedTextField();
        jLabel1 = new javax.swing.JLabel();
        tracks_list = new javax.swing.JTextField();
        lp_panel = new javax.swing.JPanel();
        lp_label = new javax.swing.JLabel();
        lp_artist_label = new javax.swing.JLabel();
        lp_artist_name = new javax.swing.JTextField();
        lp_genre_label = new javax.swing.JLabel();
        lp_genre = new javax.swing.JTextField();
        lp_record_label = new javax.swing.JLabel();
        lp_record = new javax.swing.JTextField();
        lp_publication_label = new javax.swing.JLabel();
        lp_publication_date = new javax.swing.JFormattedTextField();
        lp_tracks_label = new javax.swing.JLabel();
        lp_tracks_list = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        currency_label.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        currency_label.setText("VND");

        currency_label1.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        currency_label1.setText("VND");

        category_label.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        category_label.setText("Category: ");

        name_label.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        name_label.setText("Media name: ");

        media_value.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("###0"))));

        big_label.setFont(new java.awt.Font("Tahoma", 3, 24)); // NOI18N
        big_label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        big_label.setText("Add New Media");

        media_name.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                media_nameActionPerformed(evt);
            }
        });

        price_label.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        price_label.setText("Price: ");

        media_price.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("###0"))));

        value_label.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        value_label.setText("Value: ");

        categories.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Book", "DVD", "CD", "LP" }));
        categories.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                categoriesActionPerformed(evt);
            }
        });

        quantity_label.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        quantity_label.setText("Quantity: ");

        input_day_label.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        input_day_label.setText("Input day: ");

        media_input_day.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(new java.text.SimpleDateFormat("yyyy-MM-dd"))));

        barcode_label.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        barcode_label.setText("Barcode: ");

        weight_label.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        weight_label.setText("Weight: ");

        media_description_label.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        media_description_label.setText("Description: ");

        media_description.setColumns(20);
        media_description.setRows(5);
        jScrollPane2.setViewportView(media_description);

        width_label.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        width_label.setText("Width: ");

        media_quantity.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(java.text.NumberFormat.getIntegerInstance())));

        height_label.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        height_label.setText("Height:");

        media_height.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter()));

        depth_label.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        depth_label.setText("Depth: ");

        media_weight.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(java.text.NumberFormat.getIntegerInstance())));

        image_path_label.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        image_path_label.setText("Image path:");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel2.setText("cm");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel3.setText("cm");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel4.setText("cm");

        javax.swing.GroupLayout left_panelLayout = new javax.swing.GroupLayout(left_panel);
        left_panel.setLayout(left_panelLayout);
        left_panelLayout.setHorizontalGroup(
            left_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(left_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(left_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(left_panelLayout.createSequentialGroup()
                        .addGroup(left_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(big_label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(left_panelLayout.createSequentialGroup()
                                .addGroup(left_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(left_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addGroup(left_panelLayout.createSequentialGroup()
                                            .addComponent(name_label)
                                            .addGap(18, 18, 18)
                                            .addComponent(media_name, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(media_price, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(left_panelLayout.createSequentialGroup()
                                            .addComponent(value_label)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(media_value, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(left_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(media_weight, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, left_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(media_barcode, javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addComponent(media_input_day, javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addComponent(media_quantity, javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addComponent(categories, javax.swing.GroupLayout.Alignment.TRAILING, 0, 255, Short.MAX_VALUE))))
                                    .addComponent(price_label))
                                .addGap(18, 18, 18)
                                .addGroup(left_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(currency_label1, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(currency_label, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 210, Short.MAX_VALUE)))
                        .addGap(7, 7, 7))
                    .addGroup(left_panelLayout.createSequentialGroup()
                        .addGroup(left_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(category_label)
                            .addComponent(quantity_label)
                            .addComponent(input_day_label)
                            .addComponent(barcode_label)
                            .addComponent(weight_label)
                            .addGroup(left_panelLayout.createSequentialGroup()
                                .addGroup(left_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(media_description_label)
                                    .addComponent(width_label, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addGap(18, 18, 18)
                                .addGroup(left_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(left_panelLayout.createSequentialGroup()
                                        .addComponent(media_width, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel2)
                                        .addGap(18, 18, 18)
                                        .addComponent(height_label)
                                        .addGap(18, 18, 18)
                                        .addComponent(media_height, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel3)
                                        .addGap(18, 18, 18)
                                        .addComponent(depth_label)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(media_depth, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(left_panelLayout.createSequentialGroup()
                                .addComponent(image_path_label)
                                .addGap(18, 18, 18)
                                .addComponent(media_image_path)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        left_panelLayout.setVerticalGroup(
            left_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(left_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(big_label)
                .addGap(83, 83, 83)
                .addGroup(left_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(name_label)
                    .addComponent(media_name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(left_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(media_value, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(currency_label1)
                    .addComponent(value_label))
                .addGap(21, 21, 21)
                .addGroup(left_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(price_label)
                    .addGroup(left_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(media_price, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(currency_label)))
                .addGap(18, 18, 18)
                .addGroup(left_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(category_label)
                    .addComponent(categories, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(left_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(quantity_label)
                    .addComponent(media_quantity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(left_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(input_day_label)
                    .addComponent(media_input_day, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(left_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(barcode_label)
                    .addComponent(media_barcode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(left_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(weight_label)
                    .addComponent(media_weight, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(left_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(width_label)
                    .addComponent(media_width, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(height_label)
                    .addComponent(media_height, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(depth_label)
                    .addComponent(media_depth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(left_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(media_description_label))
                .addGap(10, 10, 10)
                .addGroup(left_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(image_path_label)
                    .addComponent(media_image_path, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24))
        );

        detail_label.setFont(new java.awt.Font("Tahoma", 3, 24)); // NOI18N
        detail_label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        detail_label.setText("Detail Properties");

        javax.swing.GroupLayout detail_panelLayout = new javax.swing.GroupLayout(detail_panel);
        detail_panel.setLayout(detail_panelLayout);
        detail_panelLayout.setHorizontalGroup(
            detail_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(detail_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(detail_label, javax.swing.GroupLayout.DEFAULT_SIZE, 915, Short.MAX_VALUE)
                .addContainerGap())
        );
        detail_panelLayout.setVerticalGroup(
            detail_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(detail_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(detail_label)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        confirmButton.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        confirmButton.setText("Confirm");
        confirmButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                confirmButtonActionPerformed(evt);
            }
        });

        backButton.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        backButton.setText("Back to Home");
        backButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout add_panelLayout = new javax.swing.GroupLayout(add_panel);
        add_panel.setLayout(add_panelLayout);
        add_panelLayout.setHorizontalGroup(
            add_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(add_panelLayout.createSequentialGroup()
                .addGap(391, 391, 391)
                .addComponent(confirmButton, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(80, 80, 80)
                .addComponent(backButton, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        add_panelLayout.setVerticalGroup(
            add_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(add_panelLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(add_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(confirmButton, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(backButton, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        cards.setLayout(new java.awt.CardLayout());

        author_label.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        author_label.setText("Author name: ");

        author_name.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                author_nameActionPerformed(evt);
            }
        });

        language_label.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        language_label.setText("Language: ");

        languages.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Vietnamese", "English", "Japanese" }));

        publisher_label.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        publisher_label.setText("Publisher: ");

        publication_label.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        publication_label.setText("Publication date: ");

        publication_date.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(new java.text.SimpleDateFormat("yyyy-MM-dd"))));

        page_label.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        page_label.setText("Pages: ");

        page_number.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter()));

        cover_label.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        cover_label.setText("Cover type: ");

        cover_types.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Paperback", "Hardcover" }));
        cover_types.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cover_typesActionPerformed(evt);
            }
        });

        category_label1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        category_label1.setText("Book Details");

        genre_label.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        genre_label.setText("Genre: ");

        javax.swing.GroupLayout book_panelLayout = new javax.swing.GroupLayout(book_panel);
        book_panel.setLayout(book_panelLayout);
        book_panelLayout.setHorizontalGroup(
            book_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(book_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(book_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(category_label1)
                    .addGroup(book_panelLayout.createSequentialGroup()
                        .addGroup(book_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cover_label)
                            .addComponent(page_label)
                            .addComponent(publication_label)
                            .addComponent(publisher_label)
                            .addComponent(language_label)
                            .addComponent(author_label)
                            .addComponent(genre_label))
                        .addGap(43, 43, 43)
                        .addGroup(book_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(author_name, javax.swing.GroupLayout.DEFAULT_SIZE, 372, Short.MAX_VALUE)
                            .addComponent(languages, 0, 372, Short.MAX_VALUE)
                            .addComponent(publisher, javax.swing.GroupLayout.DEFAULT_SIZE, 372, Short.MAX_VALUE)
                            .addComponent(publication_date, javax.swing.GroupLayout.DEFAULT_SIZE, 372, Short.MAX_VALUE)
                            .addComponent(page_number, javax.swing.GroupLayout.DEFAULT_SIZE, 372, Short.MAX_VALUE)
                            .addComponent(cover_types, 0, 372, Short.MAX_VALUE)
                            .addComponent(book_genre))))
                .addContainerGap(292, Short.MAX_VALUE))
        );
        book_panelLayout.setVerticalGroup(
            book_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(book_panelLayout.createSequentialGroup()
                .addComponent(category_label1)
                .addGap(40, 40, 40)
                .addGroup(book_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(author_label)
                    .addComponent(author_name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(book_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(language_label)
                    .addComponent(languages, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(book_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(publisher_label)
                    .addComponent(publisher, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(book_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(publication_label)
                    .addComponent(publication_date, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(book_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(page_label)
                    .addComponent(page_number, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(book_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cover_label)
                    .addComponent(cover_types, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(book_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(genre_label)
                    .addComponent(book_genre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(196, Short.MAX_VALUE))
        );

        cards.add(book_panel, "card2");

        dvd_label.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        dvd_label.setText("DVD Details");

        director_label.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        director_label.setText("Director: ");

        dvd_studio_label.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        dvd_studio_label.setText("Studio: ");

        publication_dvd_label.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        publication_dvd_label.setText("Publication date: ");

        publication_date_dvd.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(new java.text.SimpleDateFormat("yyyy-MM-dd"))));
        publication_date_dvd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                publication_date_dvdActionPerformed(evt);
            }
        });

        dvd_runtime_label.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        dvd_runtime_label.setText("Runtime: ");

        dvd_language_label.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        dvd_language_label.setText("Language: ");

        dvd_languages.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Vietnamese", "English", "Japanese" }));

        dvd_type_label.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        dvd_type_label.setText("DVD Type: ");

        dvd_types.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Blu-ray", "HD-DVD" }));

        dvd_subtitle_label.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        dvd_subtitle_label.setText("Subtitle: ");

        dvd_subtitle.setColumns(20);
        dvd_subtitle.setRows(5);
        jScrollPane1.setViewportView(dvd_subtitle);

        javax.swing.GroupLayout dvd_panelLayout = new javax.swing.GroupLayout(dvd_panel);
        dvd_panel.setLayout(dvd_panelLayout);
        dvd_panelLayout.setHorizontalGroup(
            dvd_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dvd_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(dvd_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(dvd_label)
                    .addGroup(dvd_panelLayout.createSequentialGroup()
                        .addGroup(dvd_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(director_label)
                            .addComponent(dvd_studio_label)
                            .addComponent(publication_dvd_label)
                            .addComponent(dvd_runtime_label)
                            .addComponent(dvd_language_label)
                            .addComponent(dvd_type_label)
                            .addComponent(dvd_subtitle_label))
                        .addGap(64, 64, 64)
                        .addGroup(dvd_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(writer_name, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(studio_name, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(publication_date_dvd, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(dvd_runtime)
                            .addComponent(dvd_languages, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(dvd_types, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 287, Short.MAX_VALUE))))
                .addContainerGap(356, Short.MAX_VALUE))
        );
        dvd_panelLayout.setVerticalGroup(
            dvd_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dvd_panelLayout.createSequentialGroup()
                .addComponent(dvd_label)
                .addGap(18, 18, 18)
                .addGroup(dvd_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(director_label)
                    .addComponent(writer_name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(dvd_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(dvd_studio_label)
                    .addComponent(studio_name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(dvd_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(publication_dvd_label)
                    .addComponent(publication_date_dvd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(dvd_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(dvd_runtime_label)
                    .addComponent(dvd_runtime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(dvd_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(dvd_language_label)
                    .addComponent(dvd_languages, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(dvd_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(dvd_type_label)
                    .addComponent(dvd_types, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(dvd_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(dvd_subtitle_label)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 156, Short.MAX_VALUE))
        );

        cards.add(dvd_panel, "card3");

        cd_label.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        cd_label.setText("CD Details");

        artist_label.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        artist_label.setText("Artist name: ");

        cd_genre_label.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        cd_genre_label.setText("Genre: ");

        record_label.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        record_label.setText("Record label: ");

        cd_publication_label.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        cd_publication_label.setText("Publication date: ");

        cd_publication_date.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(new java.text.SimpleDateFormat("yyyy-MM-dd"))));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel1.setText("Tracks: ");

        javax.swing.GroupLayout cd_panelLayout = new javax.swing.GroupLayout(cd_panel);
        cd_panel.setLayout(cd_panelLayout);
        cd_panelLayout.setHorizontalGroup(
            cd_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cd_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(cd_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cd_label)
                    .addGroup(cd_panelLayout.createSequentialGroup()
                        .addGroup(cd_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(artist_label)
                            .addComponent(cd_genre_label)
                            .addComponent(record_label)
                            .addComponent(cd_publication_label)
                            .addComponent(jLabel1))
                        .addGap(30, 30, 30)
                        .addGroup(cd_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(artist_name)
                            .addComponent(cd_genre)
                            .addComponent(record_name)
                            .addComponent(cd_publication_date, javax.swing.GroupLayout.DEFAULT_SIZE, 312, Short.MAX_VALUE)
                            .addComponent(tracks_list))))
                .addContainerGap(365, Short.MAX_VALUE))
        );
        cd_panelLayout.setVerticalGroup(
            cd_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cd_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cd_label)
                .addGap(18, 18, 18)
                .addGroup(cd_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(artist_label)
                    .addComponent(artist_name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(cd_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cd_genre_label)
                    .addComponent(cd_genre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(cd_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(record_label)
                    .addComponent(record_name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(cd_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cd_publication_label)
                    .addComponent(cd_publication_date, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(cd_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(tracks_list, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(292, Short.MAX_VALUE))
        );

        cards.add(cd_panel, "card4");

        lp_panel.setAutoscrolls(true);

        lp_label.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lp_label.setText("LP Details:");

        lp_artist_label.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        lp_artist_label.setText("Artist name: ");

        lp_genre_label.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        lp_genre_label.setText("Genre: ");

        lp_record_label.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        lp_record_label.setText("Record label: ");

        lp_publication_label.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        lp_publication_label.setText("Publication date: ");

        lp_publication_date.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(new java.text.SimpleDateFormat("yyyy-MM-dd"))));

        lp_tracks_label.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        lp_tracks_label.setText("Tracks: ");

        javax.swing.GroupLayout lp_panelLayout = new javax.swing.GroupLayout(lp_panel);
        lp_panel.setLayout(lp_panelLayout);
        lp_panelLayout.setHorizontalGroup(
            lp_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(lp_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(lp_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lp_label)
                    .addGroup(lp_panelLayout.createSequentialGroup()
                        .addGroup(lp_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lp_artist_label)
                            .addComponent(lp_genre_label)
                            .addComponent(lp_record_label)
                            .addComponent(lp_publication_label)
                            .addComponent(lp_tracks_label))
                        .addGap(28, 28, 28)
                        .addGroup(lp_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lp_artist_name)
                            .addComponent(lp_genre)
                            .addComponent(lp_record)
                            .addComponent(lp_publication_date)
                            .addComponent(lp_tracks_list, javax.swing.GroupLayout.DEFAULT_SIZE, 314, Short.MAX_VALUE))))
                .addContainerGap(365, Short.MAX_VALUE))
        );
        lp_panelLayout.setVerticalGroup(
            lp_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(lp_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lp_label)
                .addGap(18, 18, 18)
                .addGroup(lp_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lp_artist_label)
                    .addComponent(lp_artist_name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(lp_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lp_genre_label)
                    .addComponent(lp_genre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(lp_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lp_record_label)
                    .addComponent(lp_record, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(lp_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lp_publication_label)
                    .addComponent(lp_publication_date, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(lp_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lp_tracks_label)
                    .addComponent(lp_tracks_list, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(292, Short.MAX_VALUE))
        );

        cards.add(lp_panel, "card5");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(add_panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(left_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(detail_panel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cards, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 832, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(detail_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cards, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                    .addComponent(left_panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(add_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45))
        );

        left_panel.getAccessibleContext().setAccessibleName("");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void media_nameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_media_nameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_media_nameActionPerformed

    private void setVisiblePanels(int category_id) {
        switch(category_id) {
            case 1:
                book_panel.setVisible(true);
                dvd_panel.setVisible(false);
                cd_panel.setVisible(false);
                lp_panel.setVisible(false);
                break;
            case 2:
                book_panel.setVisible(false);
                dvd_panel.setVisible(true);
                cd_panel.setVisible(false);
                lp_panel.setVisible(false);
                break;
            case 3:
                book_panel.setVisible(false);
                dvd_panel.setVisible(false);
                cd_panel.setVisible(true);
                lp_panel.setVisible(false);
                break;
            case 4:
                book_panel.setVisible(false);
                dvd_panel.setVisible(false);
                cd_panel.setVisible(false);
                lp_panel.setVisible(true);
                break;
        }
    }
    
    private void categoriesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_categoriesActionPerformed
        // TODO add your handling code here:
        int category_id = categories.getSelectedIndex() + 1;
        setVisiblePanels(category_id);
    }//GEN-LAST:event_categoriesActionPerformed

    private void author_nameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_author_nameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_author_nameActionPerformed

    private void cover_typesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cover_typesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cover_typesActionPerformed

    private void publication_date_dvdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_publication_date_dvdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_publication_date_dvdActionPerformed

    private void confirmButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_confirmButtonActionPerformed
        // TODO add your handling code here:
        
        if (media_name.getText().length() == 0 || media_value.getText().length() == 0 || media_price.getText().length() == 0 
                || media_quantity.getText().length() == 0 || media_input_day.getText().length() == 0 || media_barcode.getText().length() == 0
                || media_image_path.getText().length() == 0) {
            JOptionPane.showMessageDialog(null, "Please enter the media name, value, price, quantity, input day, barcode and image path!", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            
            
            String title = media_name.getText();
            int value = Integer.parseInt(media_value.getText());
            int price = Integer.parseInt(media_price.getText());
            int category_id = categories.getSelectedIndex() + 1;
            String category = String.valueOf(categories.getSelectedItem());
            int quantity = Integer.parseInt(media_quantity.getText());
            String barcode = media_barcode.getText();
            String description = media_description.getText();
            String input_day = media_input_day.getText();
            String image_path = "";
            if (media_image_path.getText().contains("src/views/productImages/")) {
                image_path = media_image_path.getText();
            } else {
                image_path = "src/views/productImages/" + media_image_path.getText();
            }
            
            int width = media_width.getText().length() == 0 ? 0 : Integer.parseInt(media_width.getText());
            int height = media_height.getText().length() == 0 ? 0 : Integer.parseInt(media_height.getText());
            int depth = media_depth.getText().length() == 0 ? 0 : Integer.parseInt(media_depth.getText());
            int weight = media_weight.getText().length() == 0 ? 0 : Integer.parseInt(media_weight.getText());
            
            int min_price = (int)(value * 0.3);
            int max_price = (int)(value * 1.5);
            
            if (value < 0 || price < 0) {
                JOptionPane.showMessageDialog(null, "Value and price must greater than 0!", "Error", JOptionPane.ERROR_MESSAGE);
            } else if (price < min_price || price > max_price) {
                JOptionPane.showMessageDialog(null, "Price must in the range of 30% and 150% of value!", "Error", JOptionPane.ERROR_MESSAGE);
            } else if (weight == 0) {
                JOptionPane.showMessageDialog(null, "Weight must be greater than 0");
            } else if (width == 0 || height == 0 || depth == 0) {
                JOptionPane.showMessageDialog(null, "Dimensions of product must be greater than 0");
            } 
            else {
                switch (category_id) {
                    case 1:
                        String author = author_name.getText();
                        int language_id = languages.getSelectedIndex() + 1;
                        String publisher_name = publisher.getText();
                        String publication_day = publication_date.getText();
                        int pages = 0;
                        try {
                            pages = Integer.parseInt(page_number.getText());
                        } catch (Exception e) {
                        }
                        
                        int cover_type_id = cover_types.getSelectedIndex() + 1;
                        String genre = book_genre.getText();
                        int book_id = Media.getMaxID() + 1;
                        if (author.length() == 0 || publisher_name.length() == 0 || publication_day.length() == 0 || page_number.getText().length() == 0 || genre.length() == 0) {
                            JOptionPane.showMessageDialog(null, "Please enter book details");
                            return;
                        } else {
                            media = new Book(title, value, price, 0, category_id, image_path, barcode, description, quantity, input_day, width, height, depth, weight, author, cover_type_id, publisher_name, publication_day, pages, language_id, genre);
                        }
                        break;
                    case 2:
                        String director = writer_name.getText();
                        String studio = studio_name.getText();
                        String dvd_publication_date = publication_date_dvd.getText();
                        int runtime = 0;
                        try {
                            runtime = Integer.parseInt(dvd_runtime.getText());
                        } catch (Exception e) {
                            
                        }
                        int dvd_language_id = dvd_languages.getSelectedIndex() + 1;
                        int dvd_type_id = dvd_types.getSelectedIndex() + 1;
                        String subtitle = dvd_subtitle.getText();
                        int dvd_id = Media.getMaxID() + 1;
                        if (director.length() == 0 || studio.length() == 0 || dvd_publication_date.length() == 0 || dvd_runtime.getText().length() == 0 || subtitle.length() == 0) {
                            JOptionPane.showMessageDialog(null, "Please enter DVD details");
                            return;
                        } else {
                            media = new DVD(title, value, price, 0, category_id, image_path, barcode, description, quantity, input_day, width, height, depth, weight, dvd_type_id, director, runtime, studio, dvd_language_id, subtitle, dvd_publication_date);
                        }
                        break;
                    case 3:
                        String artist = artist_name.getText();
                        String genre_cd = cd_genre.getText();
                        String record = record_name.getText();
                        String cd_publication_day = cd_publication_date.getText();
                        String track_list = tracks_list.getText();
                        int cd_id = Media.getMaxID() + 1;
                        if (artist.length() == 0 || genre_cd.length() == 0 || record.length() == 0 || track_list.length() == 0) {
                            JOptionPane.showMessageDialog(null, "Please enter CD details");
                            return;
                        } else {
                            media = new CD(title, value, price, 0, category_id, image_path, barcode, description, quantity, input_day, width, height, depth, weight, artist, record, cd_publication_day, genre_cd, track_list);
                        }
                        break;
                    case 4:
                        String lp_artist = lp_artist_name.getText();
                        String genre_lp = lp_genre.getText();
                        String lp_record_name = lp_record.getText();
                        String lp_publication_day = lp_publication_date.getText();
                        String lp_tracks = lp_tracks_list.getText();
                        int lp_id = Media.getMaxID() + 1;
                        if (lp_artist.length() == 0 || genre_lp.length() == 0 || lp_record_name.length() == 0 || lp_publication_day.length() == 0 || lp_tracks.length() == 0) {
                            JOptionPane.showMessageDialog(null, "Please enter LP details");
                            return;
                            
                        } else {
                            media = new LP(title, value, price, 0, category_id, image_path, barcode, description, quantity, input_day, width, height, depth, weight, lp_artist, lp_record_name, lp_publication_day, genre_lp, lp_tracks);
                        }
                        break;
                }
                if (flag) {
                    int confirmAdd = JOptionPane.showConfirmDialog(null, "Are you sure?");
                    if (confirmAdd == 0) {
                        MediaController.insert(media);
//                        this.setVisible(false);
                    }
                } else {
                    int confirmUpdate = JOptionPane.showConfirmDialog(null, "Are you sure?");
                    if (confirmUpdate == 0) {
                        media.setId(old_id);
                        MediaController.update(media);
                        System.out.println(media.getId());
                        System.out.println(media.getTitle());
//                        this.setVisible(false);
                    }
                }
            }
        }
        
    }//GEN-LAST:event_confirmButtonActionPerformed
    public void AddFrame(Media md, boolean isEdit) {
        flag = false;
        initComponents();
        if (isEdit) {
            big_label.setText("Edit Media");
            categories.setEnabled(false);
        } else {
            big_label.setText("View Media");
            media_name.setEditable(false);
            media_value.setEditable(false);
            media_value.setEditable(false);
            media_price.setEditable(false);
            categories.setEditable(false);
            categories.setEnabled(false);
            confirmButton.setVisible(false);
        }
        
        media = md;
        media.setId(md.getId());
        media_name.setText(md.getTitle());
        media_value.setText(md.getValue() + "");
        media_price.setText(md.getPrice() + "");
        categories.setEditable(false);
        int category_id = md.getCategoryId();
        switch (category_id) {
            case 1:
                Book book = (Book) md;
                if (!isEdit) {
                    media_quantity.setEditable(false);
                    media_input_day.setEditable(false);
                    media_barcode.setEditable(false);
                    media_weight.setEditable(false);
                    media_width.setEditable(false);
                    media_height.setEditable(false);
                    media_depth.setEditable(false);
                    media_description.setEditable(false);
                    author_name.setEditable(false);
                    languages.setEditable(false);
                    languages.setEnabled(false);
                    publisher.setEditable(false);
                    publication_date.setEditable(false);
                    page_number.setEditable(false);
                    book_genre.setEditable(false);
                    cover_types.setEnabled(false);
                }
                media_quantity.setText(book.getQuantity() + "");
                media_input_day.setText(book.getInputDay().split(" ")[0]);
                media_barcode.setText(book.getBarcode());
                media_weight.setText(book.getWeight() + "");
                media_width.setText(book.getWidth() + "");
                media_height.setText(book.getHeight()+"");
                media_depth.setText(book.getDepth()+"");
                media_description.setText(book.getDescription());
                author_name.setText(book.getAuthor());
                languages.setSelectedIndex(book.getLanguageId()-1);
                publisher.setText(book.getPublisher());
                publication_date.setText(book.getPublication_date().split(" ")[0]);
                page_number.setText(book.getPageNumber()+"");
                book_genre.setText(book.getGenre());
                cover_types.setSelectedIndex(book.getCoverTypeID()-1);
                setVisiblePanels(category_id);
                
                break;
            case 2:
                DVD dvd = (DVD) md;
                if (!isEdit) {
                    media_quantity.setEditable(false);
                    media_input_day.setEditable(false);
                    media_barcode.setEditable(false);
                    media_weight.setEditable(false);
                    media_width.setEditable(false);
                    media_height.setEditable(false);
                    media_depth.setEditable(false);
                    media_description.setEditable(false);
                    writer_name.setEditable(false);
                    studio_name.setEditable(false);
                    publication_date_dvd.setEditable(false);
                    dvd_runtime.setEditable(false);
                    dvd_languages.setEditable(false);
                    dvd_languages.setEnabled(false);
                    dvd_types.setEnabled(false);
                    dvd_subtitle.setEditable(false);
                }
                media_quantity.setText(dvd.getQuantity() + "");
                media_input_day.setText(dvd.getInputDay().split(" ")[0]);
                media_barcode.setText(dvd.getBarcode());
                media_weight.setText(dvd.getWeight() + "");
                media_width.setText(dvd.getWidth() + "");
                media_height.setText(dvd.getHeight()+"");
                media_depth.setText(dvd.getDepth()+"");
                media_description.setText(dvd.getDescription());
                writer_name.setText(dvd.getWriterName());
                studio_name.setText(dvd.getStudioName());
                publication_date_dvd.setText(dvd.getPublicationDate().split(" ")[0]);
                dvd_runtime.setText(dvd.getRunTime()+"");
                dvd_languages.setSelectedIndex(dvd.getLanguageId()-1);
                dvd_types.setSelectedIndex(dvd.getDvdTypeId()-1);
                dvd_subtitle.setText(dvd.getSubtitle());
                setVisiblePanels(category_id);
                break;
            case 3:
                CD cd = (CD) md;
                if (!isEdit) {
                    media_quantity.setEditable(false);
                    media_input_day.setEditable(false);
                    media_barcode.setEditable(false);
                    media_weight.setEditable(false);
                    media_width.setEditable(false);
                    media_height.setEditable(false);
                    media_depth.setEditable(false);
                    media_description.setEditable(false);
                    artist_name.setEditable(false);
                    cd_genre.setEditable(false);
                    record_name.setEditable(false);
                    cd_publication_date.setEditable(false);
                    tracks_list.setEditable(false);
                }
                media_quantity.setText(cd.getQuantity() + "");
                media_input_day.setText(cd.getInputDay().split(" ")[0]);
                media_barcode.setText(cd.getBarcode());
                media_weight.setText(cd.getWeight() + "");
                media_width.setText(cd.getWidth() + "");
                media_height.setText(cd.getHeight()+"");
                media_depth.setText(cd.getDepth()+"");
                media_description.setText(cd.getDescription());
                artist_name.setText(cd.getArtistName());
                cd_genre.setText(cd.getGenre());
                record_name.setText(cd.getRecordLabelName());
                cd_publication_date.setText(cd.getPublicationDate().split(" ")[0]);
                tracks_list.setText(cd.getTrackList());
                setVisiblePanels(category_id);
                break;
            case 4:
                LP lp = (LP) md;
                if (!isEdit) {
                    media_quantity.setEditable(false);
                    media_input_day.setEditable(false);
                    media_barcode.setEditable(false);
                    media_weight.setEditable(false);
                    media_width.setEditable(false);
                    media_height.setEditable(false);
                    media_depth.setEditable(false);
                    media_description.setEditable(false);
                    lp_artist_name.setEditable(false);
                    lp_genre.setEditable(false);
                    lp_record.setEditable(false);
                    lp_publication_date.setEditable(false);
                    lp_tracks_list.setEditable(false);
                }
                media_quantity.setText(lp.getQuantity() + "");
                media_input_day.setText(lp.getInputDay().split(" ")[0]);
                media_barcode.setText(lp.getBarcode());
                media_weight.setText(lp.getWeight() + "");
                media_width.setText(lp.getWidth() + "");
                media_height.setText(lp.getHeight()+"");
                media_depth.setText(lp.getDepth()+"");
                media_description.setText(lp.getDescription());
                lp_artist_name.setText(lp.getArtistName());
                lp_genre.setText(lp.getGenre());
                lp_record.setText(lp.getRecordLabelName());
                lp_publication_date.setText(lp.getPublicationDate().split(" ")[0]);
                lp_tracks_list.setText(lp.getTrackList());
                
                setVisiblePanels(category_id);
                break;
        }
       
        
    }

    public JButton getBackButton() {
        return backButton;
    }
    
    private void backButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backButtonActionPerformed
        // TODO add your handling code here:
        
        this.dispose();

    }//GEN-LAST:event_backButtonActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MediaAddFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MediaAddFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MediaAddFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MediaAddFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                MediaAddFrame frame = new MediaAddFrame();
                frame.setVisible(true);
                
            }
        });
    }
    
    public JLabel getImagePathLabel() {
        return image_path_label;
    }
    
    public JTextField getImagePathTextField() {
        return media_image_path;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel add_panel;
    private javax.swing.JLabel artist_label;
    private javax.swing.JTextField artist_name;
    private javax.swing.JLabel author_label;
    private javax.swing.JTextField author_name;
    private javax.swing.JButton backButton;
    private javax.swing.JLabel barcode_label;
    private javax.swing.JLabel big_label;
    private javax.swing.JTextField book_genre;
    private javax.swing.JPanel book_panel;
    private javax.swing.JPanel cards;
    private javax.swing.JComboBox<String> categories;
    private javax.swing.JLabel category_label;
    private javax.swing.JLabel category_label1;
    private javax.swing.JTextField cd_genre;
    private javax.swing.JLabel cd_genre_label;
    private javax.swing.JLabel cd_label;
    private javax.swing.JPanel cd_panel;
    private javax.swing.JFormattedTextField cd_publication_date;
    private javax.swing.JLabel cd_publication_label;
    private javax.swing.JButton confirmButton;
    private javax.swing.JLabel cover_label;
    private javax.swing.JComboBox<String> cover_types;
    private javax.swing.JLabel currency_label;
    private javax.swing.JLabel currency_label1;
    private javax.swing.JLabel depth_label;
    private javax.swing.JLabel detail_label;
    private javax.swing.JPanel detail_panel;
    private javax.swing.JLabel director_label;
    private javax.swing.JLabel dvd_label;
    private javax.swing.JLabel dvd_language_label;
    private javax.swing.JComboBox<String> dvd_languages;
    private javax.swing.JPanel dvd_panel;
    private javax.swing.JTextField dvd_runtime;
    private javax.swing.JLabel dvd_runtime_label;
    private javax.swing.JLabel dvd_studio_label;
    private javax.swing.JTextArea dvd_subtitle;
    private javax.swing.JLabel dvd_subtitle_label;
    private javax.swing.JLabel dvd_type_label;
    private javax.swing.JComboBox<String> dvd_types;
    private javax.swing.JLabel genre_label;
    private javax.swing.JLabel height_label;
    private javax.swing.JLabel image_path_label;
    private javax.swing.JLabel input_day_label;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel language_label;
    private javax.swing.JComboBox<String> languages;
    private javax.swing.JPanel left_panel;
    private javax.swing.JLabel lp_artist_label;
    private javax.swing.JTextField lp_artist_name;
    private javax.swing.JTextField lp_genre;
    private javax.swing.JLabel lp_genre_label;
    private javax.swing.JLabel lp_label;
    private javax.swing.JPanel lp_panel;
    private javax.swing.JFormattedTextField lp_publication_date;
    private javax.swing.JLabel lp_publication_label;
    private javax.swing.JTextField lp_record;
    private javax.swing.JLabel lp_record_label;
    private javax.swing.JLabel lp_tracks_label;
    private javax.swing.JTextField lp_tracks_list;
    private javax.swing.JTextField media_barcode;
    private javax.swing.JFormattedTextField media_depth;
    private javax.swing.JTextArea media_description;
    private javax.swing.JLabel media_description_label;
    private javax.swing.JFormattedTextField media_height;
    private javax.swing.JTextField media_image_path;
    private javax.swing.JFormattedTextField media_input_day;
    private javax.swing.JTextField media_name;
    private javax.swing.JFormattedTextField media_price;
    private javax.swing.JFormattedTextField media_quantity;
    private javax.swing.JFormattedTextField media_value;
    private javax.swing.JFormattedTextField media_weight;
    private javax.swing.JFormattedTextField media_width;
    private javax.swing.JLabel name_label;
    private javax.swing.JLabel page_label;
    private javax.swing.JFormattedTextField page_number;
    private javax.swing.JLabel price_label;
    private javax.swing.JFormattedTextField publication_date;
    private javax.swing.JFormattedTextField publication_date_dvd;
    private javax.swing.JLabel publication_dvd_label;
    private javax.swing.JLabel publication_label;
    private javax.swing.JTextField publisher;
    private javax.swing.JLabel publisher_label;
    private javax.swing.JLabel quantity_label;
    private javax.swing.JLabel record_label;
    private javax.swing.JTextField record_name;
    private javax.swing.JTextField studio_name;
    private javax.swing.JTextField tracks_list;
    private javax.swing.JLabel value_label;
    private javax.swing.JLabel weight_label;
    private javax.swing.JLabel width_label;
    private javax.swing.JTextField writer_name;
    // End of variables declaration//GEN-END:variables
}
