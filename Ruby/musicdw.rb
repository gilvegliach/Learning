#!/usr/bin/ruby
#mtv link to italian top hit
#http://www.mtv.it/music/classifiche/chart_list.asp?id_chart=1

require 'net/http'
require 'thread'

HOST = 'www.mtv.it'
URL   =  '/music/classifiche/chart_list.asp?id_chart=1'
SONG_PATTERN = 
       Regexp.new(/<span class="titolo-lancio"\s*>(.*?)<\/span>\s*<br>\s*&quot;(.*?)&quot;\s*<\/div>/mi)

ED2K_HOST = 'emugle.com'
ED2K_PATTERN =
        Regexp.new(/ed2k:\/\/\|file\|(.*?)mp3\|(\w*?)\|(\w*?)\|\//mi)
        
#'QUERY' will be sustituted with the real query string
SEARCH      = '/search.php?q=QUERY&t=Audio&Submit=Search&f=1'
ED2K_APP = 'wine /root/.wine/drive_c/Program Files/eMule'
#ED2K_APP = 'C:\\Programmi\\AdunanzA\\eMule_AdnzA.exe'
CONN_ERR  = 'Error: is the page changed?'


def get_songs
  connection = Net::HTTP.new(HOST)
  begin
    response, data = connection.get(URL)
  rescue => err 
    p err
    []
  end
  if response.code == "200"
      data.scan SONG_PATTERN   # [["artist1", "song1"], ..., ["artistN","songN"]]
  else
    puts CONN_ERR
    []
  end
end

#songs is an array like [["artist1", "song1"], ..., ["artistN","songN"]]
def search_songs(songs)
  threads = []   # one thread for each search for speeding up
  songs_with_links = [] # songs + ed2k links
  mutex = Mutex.new # for sync the threads
  songs.each do |artist_song| # artist_song is a pair ["artist", "song"]
    song_string = artist_song.join(" ");   # "artist1 song1", ..., "artistN songN"
    threads << Thread.new do
      connection = Net::HTTP.new(ED2K_HOST);
      begin 
        query=URI.escape(song_string, Regexp.new("[^#{URI::PATTERN::UNRESERVED}]"))   #encode the url eg " " -> %20
        response, data = connection.get(SEARCH.sub(/QUERY/, query))
      rescue => err
        p err
        Thread.exit
      end
      if response.code == "200"
        match=ED2K_PATTERN.match(data)
        ed2k_link = "ed2k://|file|#{artist_song[0]}-#{artist_song[1]}.mp3|#$2|#$3|/" if match
        mutex.synchronize do
          if ed2k_link
            songs_with_links.unshift([ artist_song[0],  artist_song[1], ed2k_link])   # put in front
          else
            songs_with_links.push([ artist_song[0],  artist_song[1], ed2k_link]) # append nil element
          end
        end
        # The links should be like
        # ed2k://|file|Mika-Relax, Take It Easy.mp3|6021898|97A9E28279397192382ABF46CEE3D8DC|/
      else
        puts CONN_ERR
      end
    end #Thread
  end   # song_strings#each
  
  threads.each{ |t| t.join if t}
  songs_with_links
end

def emule_download
  search_songs(get_songs).each do |song|
    system(ED2K_APP + " \"" +song[2] + "\"") if song[2] 
    puts song[2] if song[2]
  end
end
    
#p search_songs(get_songs)
emule_download
  