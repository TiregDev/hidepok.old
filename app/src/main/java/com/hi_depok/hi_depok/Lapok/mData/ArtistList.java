package com.hi_depok.hi_depok.Lapok.mData;

import com.hi_depok.hi_depok.R;

import java.util.ArrayList;

/**
 * Created by Azmi Muhammad on 3/17/2017.
 */

public class ArtistList {

    public static ArrayList<Artist> getArtists(){
        ArrayList<Artist> artists = new ArrayList<>();
        Artist artist = null;

        //ADD DATA TO LIST
        artist = new Artist();
        artist.setName("Emma Roberts");
        artist.setImage(R.drawable.emma_roberts);
        artist.setWaktu("Feb 8, 2017, at 17.00 WIB");
        artist.setKejadian(R.drawable.kemacetan);
        artist.setJudul("Macet di Gerbatama UI Bikin Bete...");
        artist.setComment_imgbtn(R.drawable.comment);
        artist.setLike_imgbtn(R.drawable.like);
        artist.setShare_imgbtn(R.drawable.share);
        artist.setJml_com("0");
        artist.setJml_like("0");
        artist.setJml_share("0");
        artists.add(artist);

        artist = new Artist();
        artist.setName("Lea Seydoux");
        artist.setImage(R.drawable.lea_seydoux);
        artist.setWaktu("Feb 8, 2017, at 17.00 WIB");
        artist.setKejadian(R.drawable.kebakaran);
        artist.setJudul("Tolong!!! Kebakaran nih di Detos");
        artist.setComment_imgbtn(R.drawable.comment);
        artist.setLike_imgbtn(R.drawable.like);
        artist.setShare_imgbtn(R.drawable.share);
        artist.setJml_com("0");
        artist.setJml_like("0");
        artist.setJml_share("0");
        artists.add(artist);

        artist = new Artist();
        artist.setName("Margot Robbie");
        artist.setImage(R.drawable.margot_robbie);
        artist.setWaktu("Feb 8, 2017, at 17.00 WIB");
        artist.setKejadian(R.drawable.banjir);
        artist.setJudul("Duh mana banjir, ujan, becek, gaada ojek...");
        artist.setComment_imgbtn(R.drawable.comment);
        artist.setLike_imgbtn(R.drawable.like);
        artist.setShare_imgbtn(R.drawable.share);
        artist.setJml_com("0");
        artist.setJml_like("0");
        artist.setJml_share("0");
        artists.add(artist);

        artist = new Artist();
        artist.setName("Oksana Neveselaya");
        artist.setImage(R.drawable.oksana_neveselaya);
        artist.setWaktu("Feb 8, 2017, at 17.00 WIB");
        artist.setKejadian(R.drawable.perampokan);
        artist.setJudul("Huhu kasian abangnya dirampok :'( ...");
        artist.setComment_imgbtn(R.drawable.comment);
        artist.setLike_imgbtn(R.drawable.like);
        artist.setShare_imgbtn(R.drawable.share);
        artist.setJml_com("0");
        artist.setJml_like("0");
        artist.setJml_share("0");
        artists.add(artist);

        artist = new Artist();
        artist.setName("Taissa Farmiga");
        artist.setImage(R.drawable.taissa_farmiga);
        artist.setWaktu("Feb 8, 2017, at 17.00 WIB");
        artist.setKejadian(R.drawable.geng_motor);
        artist.setJudul("Ihh apaan banget sih geng motor Depok :P");
        artist.setComment_imgbtn(R.drawable.comment);
        artist.setLike_imgbtn(R.drawable.like);
        artist.setShare_imgbtn(R.drawable.share);
        artist.setJml_com("0");
        artist.setJml_like("0");
        artist.setJml_share("0");
        artists.add(artist);

        return artists;
    }
}
